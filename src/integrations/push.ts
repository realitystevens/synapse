import OneSignal from 'react-native-onesignal';
import { Env } from '../core/env';
import { getMe, patchMe } from '../api/me';

export function initPush() {
  OneSignal.initialize(Env.ONESIGNAL_APP_ID);
  OneSignal.Notifications.requestPermission(true);

  OneSignal.User.addSubscriptionObserver(async event => {
    const id = event.to.isPushDisabled ? null : OneSignal.User.pushSubscription.getPushSubscriptionId();
    try {
      const me = await getMe();
      if (me.onesignal_player_id !== id) {
        await patchMe({ onesignal_player_id: id ?? undefined });
      }
    } catch {}
  });

  OneSignal.Notifications.addEventListener('click', event => {
    const data = event.notification.additionalData || {};
    // route using React Navigation: e.g., to a Habit, Group, or Impact screen
    // NavigationRef.navigate(data.route ?? 'Home', data.params ?? {})
  });
}
