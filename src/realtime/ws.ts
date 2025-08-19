import { tokenStore } from '../state/tokenStore';

let socket: WebSocket | null = null;

export function connectLeaderboardWS() {
  const token = tokenStore.getState().accessToken;
  socket = new WebSocket(`wss://api.yourdomain.com/ws/leaderboard/?token=${token}`);
  
  socket.onmessage = ev => {
    const payload = JSON.parse(ev.data);
    // dispatch to state (e.g., jotai atom or React Query invalidate)
  };
  
  socket.onclose = () => { 
    setTimeout(connectLeaderboardWS, 5000); 
  };
}
