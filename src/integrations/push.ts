import OneSignal from 'onesignal-expo-plugin';
import { Env } from '../core/env';
import { getMe, patchMe } from '../api/me';
// Use the imported Me type from '../api/types' for compatibility
import type { Me } from '../api/types';

export function initPush() {
  OneSignal.initialize(Env.ONESIGNAL_APP_ID);
  OneSignal.Notifications.requestPermission(true);

  interface SubscriptionEvent {
    to: {
      isPushDisabled: boolean;
    };
  }

  interface PatchMePayload {
    onesignal_player_id?: string | null;
  }

  OneSignal.User.addSubscriptionObserver(async (event: SubscriptionEvent) => {
    const id: string | null = event.to.isPushDisabled ? null : OneSignal.User.pushSubscription.getPushSubscriptionId();
    try {
      const me: Me = await getMe();
      if (me.onesignal_player_id !== id) {
        await patchMe({ onesignal_player_id: id ?? undefined } as PatchMePayload);
      }
    } catch {}
  });

  interface NotificationAdditionalData {
    route?: string;
    params?: Record<string, unknown>;
    [key: string]: unknown;
  }

  interface Notification {
    additionalData?: NotificationAdditionalData;
    [key: string]: unknown;
  }

  interface NotificationClickEvent {
    notification: Notification;
    [key: string]: unknown;
  }

  OneSignal.Notifications.addEventListener('click', (event: NotificationClickEvent) => {
    const data: NotificationAdditionalData = event.notification.additionalData || {};
    // route using React Navigation: e.g., to a Habit, Group, or Impact screen
    // NavigationRef.navigate(data.route ?? 'Home', data.params ?? {})
  });
}
