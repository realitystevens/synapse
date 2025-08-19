import React, { useEffect } from 'react';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import AppNavigation from './src/app/navigation';
import { initDb, startOutboxPump } from './src/offline/db';

const qc = new QueryClient();

export default function App() {
  useEffect(() => { 
    initDb(); 
    startOutboxPump(); 
  }, []);
  
  return (
    <QueryClientProvider client={qc}>
      <AppNavigation />
    </QueryClientProvider>
  );
}
