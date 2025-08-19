import { http } from '../core/http';

export async function login(username: string, password: string) {
  const { data } = await http.post('/api/auth/token/', { username, password });
  return data as { access: string; refresh: string };
}
