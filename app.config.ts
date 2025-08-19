export default {
  name: "SynapsePlus",
  slug: "synapseplus",
  scheme: "synapseplus",
  extra: {
    BASE_URL: "https://api.yourdomain.com", // Django base
    ONESIGNAL_APP_ID: "YOUR_ONESIGNAL_APP_ID",
    RC_API_KEY: "YOUR_REVENUECAT_PUBLIC_KEY"
  },
  plugins: [
    ["react-native-onesignal"],
    ["react-native-purchases", { "iosUserDefaultsSuiteName": "group.com.your.bundle" }]
  ]
};
