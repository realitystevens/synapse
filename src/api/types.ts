export type Me = { 
  id: number; 
  username: string; 
  email?: string; 
  timezone?: string; 
  onesignal_player_id?: string | null; 
  city?: string; 
  country?: string 
};

export type Group = { 
  id: number; 
  name: string; 
  member_count: number 
};

export type Habit = { 
  id: number; 
  group: number; 
  owner?: number; 
  name: string; 
  type: string; 
  frequency_per_week: number 
};

export type Completion = { 
  id: number; 
  habit: number; 
  user: number; 
  completed_at: string; 
  metadata?: Record<string, any> 
};

export type ImpactTotals = { 
  trees?: number; 
  meals?: number; 
  water?: number 
};
