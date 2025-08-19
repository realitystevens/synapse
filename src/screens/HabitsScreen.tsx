import React from 'react';
import { View, Text, FlatList, TouchableOpacity, RefreshControl } from 'react-native';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { listHabits, createCompletion } from '../api/habits';
import { enqueueCompletion } from '../offline/db';

export default function HabitsScreen({ navigation }: any) {
  const qc = useQueryClient();
  const { data: habits = [], isFetching, refetch } = useQuery({ 
    queryKey: ['habits'], 
    queryFn: listHabits 
  });

  const complete = useMutation({
    mutationFn: async (habitId: number) => {
      try { 
        return await createCompletion(habitId, { source: 'manual' }); 
      }
      catch (e) { 
        enqueueCompletion(habitId, { source: 'manual' }); 
        throw e; 
      }
    },
    onSuccess: () => qc.invalidateQueries({ queryKey: ['impact-me'] })
  });

  return (
    <View style={{ flex: 1 }}>
      <FlatList
        data={habits}
        keyExtractor={(h: any) => String(h.id)}
        refreshControl={<RefreshControl refreshing={isFetching} onRefresh={refetch} />}
        renderItem={({ item }) => (
          <TouchableOpacity 
            style={{ padding: 16, borderBottomWidth: 1 }} 
            onPress={() => complete.mutate(item.id)}
          >
            <Text style={{ fontSize: 18 }}>{item.name}</Text>
            <Text style={{ opacity: 0.6 }}>{item.type} • {item.frequency_per_week}/week</Text>
          </TouchableOpacity>
        )}
      />
    </View>
  );
}
