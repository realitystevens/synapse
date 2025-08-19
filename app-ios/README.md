# iOS Xcode Project

This directory should contain an Xcode project that:

1. Links to the Kotlin/Native framework generated from the `:shared` module
2. Implements native iOS UI using SwiftUI
3. Integrates OneSignal iOS SDK natively
4. Integrates RevenueCat iOS SDK natively
5. Bridges push notifications and billing events back to the shared Kotlin code

## Setup Steps:

1. Create a new iOS project in Xcode
2. Add the shared framework as a dependency
3. Configure OneSignal and RevenueCat iOS SDKs
4. Implement bridge classes to communicate with Kotlin shared code
5. Create SwiftUI views that call into the shared ViewModels

## Framework Integration:

The shared module generates a framework that can be imported into the iOS project:

```swift
import shared
```

You can then use the shared ViewModels and API classes in your Swift code.
