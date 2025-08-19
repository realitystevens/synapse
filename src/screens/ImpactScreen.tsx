import React from 'react';
import { View, Text } from 'react-native';
import { useQuery } from '@tanstack/react-query';
import { impactMe } from '../api/impact';

export default function ImpactScreen() {
  const { data } = useQuery({ 
    queryKey: ['impact-me'], 
    queryFn: impactMe 
  });
  
  return (
    <View style={{ padding: 24 }}>
      <Text style={{ fontSize: 22, fontWeight: '700' }}>Your Impact</Text>
      <Text>🌳 Trees: {data?.trees ?? 0}</Text>
      <Text>🍽 Meals: {data?.meals ?? 0}</Text>
      <Text>💧 Water: {data?.water ?? 0}</Text>
    </View>
  );
}
