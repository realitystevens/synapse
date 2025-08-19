import * as SQLite from 'expo-sqlite';
import NetInfo from '@react-native-community/netinfo';
import { http } from '../core/http';

const db = SQLite.openDatabase('synapse.db');

export function initDb() {
  db.transaction(tx => {
    tx.executeSql('CREATE TABLE IF NOT EXISTS completion_outbox (id INTEGER PRIMARY KEY AUTOINCREMENT, habitId INTEGER, payload TEXT, created_at INTEGER)');
  });
}

export function enqueueCompletion(habitId: number, payload: object) {
  db.transaction(tx => tx.executeSql('INSERT INTO completion_outbox (habitId, payload, created_at) VALUES (?, ?, ?)', [habitId, JSON.stringify(payload), Date.now()]));
}

export function startOutboxPump() {
  NetInfo.addEventListener(state => {
    if (state.isConnected) flushOutbox();
  });
}

async function flushOutbox() {
  db.transaction(tx => {
    tx.executeSql('SELECT id, habitId, payload FROM completion_outbox ORDER BY created_at ASC', [], async (_, { rows }) => {
      for (let i = 0; i < rows.length; i++) {
        const r = rows.item(i);
        try {
          await http.post('/api/habits/completions/', { habit: r.habitId, metadata: JSON.parse(r.payload) });
          db.transaction(txx => txx.executeSql('DELETE FROM completion_outbox WHERE id = ?', [r.id]));
        } catch (e) { /* keep for retry */ }
      }
    });
  });
}
