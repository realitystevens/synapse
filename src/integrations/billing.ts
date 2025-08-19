import Purchases from 'react-native-purchases';
import { Env } from '../core/env';

export async function initBilling(userId?: string) {
  Purchases.configure({ apiKey: Env.RC_API_KEY, appUserID: userId });
}

export async function purchase(productId: string) {
  const offerings = await Purchases.getOfferings();
  const pkg = offerings.current?.availablePackages.find(p => p.identifier === productId) || offerings.current?.lifetime;
  if (!pkg) throw new Error('Product not found');
  return Purchases.purchasePackage(pkg);
}

export async function restore() {
  await Purchases.restorePurchases();
}
