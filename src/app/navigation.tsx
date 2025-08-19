import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from '../screens/LoginScreen';
import HabitsScreen from '../screens/HabitsScreen';
import ImpactScreen from '../screens/ImpactScreen';

export type RootStackParamList = {
  Login: undefined
  Habits: undefined
  Impact: undefined
  Completion: { habitId: number }
};

const Stack = createNativeStackNavigator<RootStackParamList>();

export default function AppNavigation() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Login" component={LoginScreen} options={{ headerShown: false }} />
        <Stack.Screen name="Habits" component={HabitsScreen} />
        <Stack.Screen name="Impact" component={ImpactScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
