# Synapse+ Kotlin Multiplatform Project

A cross-platform mobile and desktop application built with Kotlin Multiplatform and Compose Multiplatform.

## Architecture

- **Shared Module**: Contains business logic, API clients, ViewModels, and UI components
- **Android App**: Native Android application using Jetpack Compose
- **iOS App**: Native iOS application (requires Xcode project setup)
- **Desktop App**: Desktop application using Compose for Desktop
- **Web App**: Web application using Compose for Web

## Technologies Used

- **Kotlin Multiplatform**: Share code across platforms
- **Compose Multiplatform**: UI framework for all platforms
- **Ktor**: HTTP client for API communication
- **SQLDelight**: Database ORM with multiplatform support
- **Koin**: Dependency injection
- **OneSignal**: Push notifications
- **RevenueCat**: In-app purchases and subscriptions
- **Napier**: Logging

## Project Structure

```
synapse_kmp/
├── shared/                 # Shared business logic and UI
│   ├── src/
│   │   ├── commonMain/     # Platform-agnostic code
│   │   ├── androidMain/    # Android-specific implementations
│   │   ├── iosMain/        # iOS-specific implementations
│   │   ├── desktopMain/    # Desktop-specific implementations
│   │   └── jsMain/         # Web-specific implementations
├── app-android/            # Android application
├── app-ios/                # iOS application (Xcode project needed)
├── app-desktop/            # Desktop application
├── app-web/                # Web application
└── gradle/                 # Gradle configuration
```

## Setup Instructions

### Prerequisites

1. Install Android Studio or IntelliJ IDEA
2. Install Kotlin Multiplatform plugin
3. For iOS development: Install Xcode (macOS only)
4. For web development: Install Node.js (optional)

### Configuration

1. **Update API Keys**: Edit the following files with your actual API keys:

   - `app-android/src/main/java/com/synapseplus/android/SynapseApp.kt`
   - Replace `YOUR_ONESIGNAL_APP_ID` and `YOUR_RC_PUBLIC_API_KEY`

2. **Backend URL**: Update the `baseUrl` in `SynapseApp.kt` to point to your Django backend

### Building and Running

#### Android

```bash
./gradlew :app-android:assembleDebug 
```

#### Desktop

```bash
./gradlew :app-desktop:run
```

#### Web

```bash
./gradlew :app-web:jsBrowserDevelopmentRun
```

#### iOS

1. Generate the iOS framework:
   ```bash
   ./gradlew :shared:linkDebugFrameworkIosArm64
   ```
2. Open the iOS project in Xcode and configure it to use the generated framework
3. Build and run from Xcode

## Features

- **Authentication**: User login and JWT token management
- **Habit Tracking**: Create and track habits with completion logging
- **Impact Dashboard**: View environmental impact metrics
- **Push Notifications**: Cross-platform notification support
- **Offline Support**: SQLDelight database with offline queueing
- **Billing**: In-app purchases and subscription management

## Development Notes

### Backend Integration

This frontend is designed to work with a Django REST Framework backend. Ensure your backend provides the following endpoints:

- `/api/auth/token/` - Authentication
- `/api/accounts/me/` - User profile
- `/api/habits/` - Habit management
- `/api/habits/completions/` - Completion tracking
- `/api/impact/dashboard/` - Impact metrics

### Platform-Specific Implementations

The project uses the expect/actual pattern for platform-specific functionality:

- **PushGateway**: OneSignal integration per platform
- **BillingGateway**: RevenueCat integration per platform
- **BaseViewModel**: Lifecycle management per platform

### Next Steps

1. Implement full iOS Xcode project
2. Add comprehensive error handling
3. Implement offline sync with outbox pattern
4. Add unit tests
5. Set up CI/CD pipeline
6. Add more UI screens and navigation

## License

[Add your license information here]
