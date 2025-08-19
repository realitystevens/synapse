import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import { login } from '../api/auth';
import { tokenStore } from '../state/tokenStore';
import { initBilling } from '../integrations/billing';
import { initPush } from '../integrations/push';

export default function LoginScreen({ navigation }: any) {
  const [username, setUser] = useState('');
  const [password, setPass] = useState('');

  const onLogin = async () => {
    try {
      const tokens = await login(username, password);
      tokenStore.getState().setTokens(tokens);
      await initBilling(username);
      initPush();
      navigation.replace('Habits');
    } catch (e: any) {
      Alert.alert('Login failed', e?.message ?? 'Try again');
    }
  };

  return (
    <View style={{ padding: 24, flex: 1, justifyContent: 'center' }}>
      <Text style={{ fontSize: 24, fontWeight: '600' }}>Sign in</Text>
      <TextInput 
        placeholder="Username" 
        value={username} 
        onChangeText={setUser} 
        style={{ borderWidth: 1, padding: 12, marginTop: 12 }} 
      />
      <TextInput 
        placeholder="Password" 
        value={password} 
        onChangeText={setPass} 
        secureTextEntry 
        style={{ borderWidth: 1, padding: 12, marginTop: 12 }} 
      />
      <Button title="Login" onPress={onLogin} />
    </View>
  );
}
