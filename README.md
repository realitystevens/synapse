# Synapse+ Mobile App

A production-grade React Native application built with TypeScript, targeting iOS and Android platforms.

## Features

- **Authentication**: JWT-based auth with automatic token refresh
- **State Management**: React Query + Jotai for efficient state management
- **Offline Support**: SQLite outbox pattern with automatic sync
- **Push Notifications**: OneSignal integration
- **Billing**: RevenueCat for subscription management
- **Real-time Updates**: WebSocket support for live data
- **Deep Linking**: Expo linking for navigation

## Architecture

### Directory Structure

```
src/
├── api/           # API layer (REST endpoints)
├── app/           # App configuration and navigation
├── core/          # Core utilities (http, env)
├── integrations/  # Third-party service integrations
├── offline/       # Offline data management
├── realtime/      # WebSocket connections
├── screens/       # React Native screens
└── state/         # State management
```

### Key Technologies

- **React Native** - Mobile framework
- **TypeScript** - Type safety
- **Expo** - Development platform
- **React Query** - Server state management
- **Jotai** - Client state management
- **React Navigation** - Navigation
- **Axios** - HTTP client
- **SQLite** - Local database

## Getting Started

1. Install dependencies:

   ```bash
   npm install
   ```

2. Configure environment variables in `app.config.ts`:

   - `BASE_URL`: Backend API Endpoint URL
   - `ONESIGNAL_APP_ID`: OneSignal app ID
   - `REVENUECAT_API_KEY`: RevenueCat public key

3. Start the development server:

   ```bash
   npm start
   ```

4. Run on device/simulator:
   ```bash
   npm run ios    # iOS
   npm run android # Android
   ```

## API Integration

The app is designed to work with a Django/DRF backend with the following endpoints:

- `POST /api/auth/token/` - Login
- `POST /api/auth/token/refresh/` - Token refresh
- `GET/PATCH /api/accounts/me/` - User profile
- `GET /api/habits/` - Habits list
- `POST /api/habits/completions/` - Create completion
- `GET /api/impact/dashboard/me/` - Impact data

## Offline Support

The app includes an outbox pattern for offline operation:

- Failed API calls are queued in SQLite
- Automatic retry when connection is restored
- Optimistic UI updates for better UX

## Push Notifications

OneSignal integration provides:

- Cross-platform push notifications
- Deep linking support
- User targeting and segmentation

## Scripts

- `npm start` - Start Expo development server
- `npm run ios` - Run on iOS simulator
- `npm run android` - Run on Android emulator
- `npm run lint` - Run ESLint
- `npm run typecheck` - Run TypeScript compiler


## License

Copyright © 2025 Synapse Plus. All rights reserved.

This software is proprietary and confidential. Unauthorized copying, distribution, or use of this code is strictly prohibited. Access is restricted to authorized staff under NDA.
