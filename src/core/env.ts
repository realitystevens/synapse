import Constants from 'expo-constants';

export const Env = {
  BASE_URL: Constants.expoConfig?.extra?.BASE_URL as string,
  ONESIGNAL_APP_ID: Constants.expoConfig?.extra?.ONESIGNAL_APP_ID as string,
  RC_API_KEY: Constants.expoConfig?.extra?.RC_API_KEY as string,
};
