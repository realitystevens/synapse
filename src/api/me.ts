import { http } from '../core/http';
import type { Me } from './types';

export async function getMe(): Promise<Me> {
  const { data } = await http.get('/api/accounts/me/');
  return Array.isArray(data) ? data[0] : data;
}

export async function patchMe(patch: Partial<Me>): Promise<Me> {
  const { data } = await http.patch('/api/accounts/me/1/', patch); // adjust id if your DRF returns single resource id
  return data;
}
