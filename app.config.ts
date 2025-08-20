import 'dotenv/config';

export default {
  name: "SynapsePlus",
  slug: "synapseplus",
  scheme: "synapseplus",
  extra: {
    BASE_URL: process.env.BASE_URL,
    ONESIGNAL_APP_ID: process.env.ONESIGNAL_APP_ID,
    REVENUECAT_API_KEY: process.env.REVENUECAT_API_KEY
  },
  plugins: [
    ["onesignal-expo-plugin"],
    ["react-native-purchases", { "iosUserDefaultsSuiteName": "group.com.your.bundle" }]
  ]
};
