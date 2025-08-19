import { http } from '../core/http';
import type { ImpactTotals } from './types';

export const impactMe = async (): Promise<ImpactTotals> => (await http.get('/api/impact/dashboard/me/')).data;

export const impactGroup = async (id: number): Promise<ImpactTotals> => (await http.get(`/api/impact/dashboard/group/${id}/`)).data;

export const impactGlobal = async (): Promise<ImpactTotals> => (await http.get('/api/impact/dashboard/global/')).data;
