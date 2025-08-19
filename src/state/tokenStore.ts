import { MMKV } from 'react-native-mmkv';
import { atom, getDefaultStore } from 'jotai';

const storage = new MMKV();

const accessAtom = atom<string | null>(storage.getString('access') ?? null);
const refreshAtom = atom<string | null>(storage.getString('refresh') ?? null);

export const tokenStore = {
  getState: () => ({
    accessToken: getDefaultStore().get(accessAtom),
    refreshToken: getDefaultStore().get(refreshAtom),
    setTokens: ({ access, refresh }: { access: string; refresh: string }) => {
      storage.set('access', access); storage.set('refresh', refresh);
      getDefaultStore().set(accessAtom, access); getDefaultStore().set(refreshAtom, refresh);
    },
    clear: () => { storage.delete('access'); storage.delete('refresh'); getDefaultStore().set(accessAtom, null); getDefaultStore().set(refreshAtom, null); }
  })
};
