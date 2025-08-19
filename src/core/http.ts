import axios from 'axios';
import { Env } from './env';
import { tokenStore } from '../state/tokenStore';

export const http = axios.create({ baseURL: Env.BASE_URL });

http.interceptors.request.use(async config => {
  const access = tokenStore.getState().accessToken;
  if (access) config.headers.Authorization = `Bearer ${access}`;
  config.headers['Content-Type'] = 'application/json';
  return config;
});

http.interceptors.response.use(
  r => r,
  async error => {
    const original = error.config;
    if (error.response?.status === 401 && !original._retry) {
      original._retry = true;
      const refresh = tokenStore.getState().refreshToken;
      if (refresh) {
        try {
          const { data } = await axios.post(`${Env.BASE_URL}/api/auth/token/refresh/`, { refresh });
          tokenStore.getState().setTokens({ access: data.access, refresh });
          original.headers.Authorization = `Bearer ${data.access}`;
          return http(original);
        } catch {}
    }
    }
    return Promise.reject(error);
  }
);
