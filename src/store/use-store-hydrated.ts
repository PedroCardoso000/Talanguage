"use client";

import { useSyncExternalStore } from "react";

import { useAppStore } from "@/store/use-app-store";

export function useStoreHydrated() {
  return useSyncExternalStore(
    (callback) => {
      const unsubscribeHydrate = useAppStore.persist.onHydrate(callback);
      const unsubscribeFinish = useAppStore.persist.onFinishHydration(callback);

      return () => {
        unsubscribeHydrate();
        unsubscribeFinish();
      };
    },
    () => useAppStore.persist.hasHydrated(),
    () => true,
  );
}
