import * as SQLite from 'expo-sqlite';
import NetInfo, { NetInfoState } from '@react-native-community/netinfo';
import { http } from '../core/http';

const db = SQLite.openDatabaseSync('synapse.db');

export function initDb() {
  db.execSync(`
    CREATE TABLE IF NOT EXISTS completion_outbox (
      id INTEGER PRIMARY KEY AUTOINCREMENT, 
      habitId INTEGER, 
      payload TEXT, 
      created_at INTEGER
    )
  `);
}

export function enqueueCompletion(habitId: number, payload: object) {
  db.runSync(
    'INSERT INTO completion_outbox (habitId, payload, created_at) VALUES (?, ?, ?)', 
    [habitId, JSON.stringify(payload), Date.now()]
  );
}

export function startOutboxPump() {
  NetInfo.addEventListener((state: NetInfoState) => {
    if (state.isConnected) flushOutbox();
  });
}

async function flushOutbox() {
  const rows = db.getAllSync('SELECT id, habitId, payload FROM completion_outbox ORDER BY created_at ASC') as Array<{ id: number; habitId: number; payload: string }>;
  
  for (const row of rows) {
    try {
      await http.post('/api/habits/completions/', { 
        habit: row.habitId, 
        metadata: JSON.parse(row.payload as string) 
      });
      db.runSync('DELETE FROM completion_outbox WHERE id = ?', [row.id]);
    } catch (e) { 
      /* keep for retry */ 
    }
  }
}
