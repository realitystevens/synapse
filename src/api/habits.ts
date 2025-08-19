import { http } from '../core/http';
import type { Group, Habit, Completion } from './types';

export const listGroups = async (): Promise<Group[]> => (await http.get('/api/habits/groups/')).data;

export const listHabits = async (): Promise<Habit[]> => (await http.get('/api/habits/')).data;

export const createCompletion = async (habitId: number, metadata: Record<string, any> = {}): Promise<Completion> =>
  (await http.post('/api/habits/completions/', { habit: habitId, metadata })).data;
