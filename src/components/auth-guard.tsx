"use client";

import { useRouter } from "next/navigation";
import type { ReactNode } from "react";
import { useEffect, useState } from "react";

import { getAuthenticatedUser } from "@/features/auth/actions/auth-actions";
import { clearAccessToken, getStoredAccessToken } from "@/features/auth/lib/auth-storage";
import { useAppStore } from "@/store/use-app-store";
import { useStoreHydrated } from "@/store/use-store-hydrated";

export function ProtectedGuard({ children }: { children: ReactNode }) {
  const router = useRouter();
  const user = useAppStore((state) => state.user);
  const setUser = useAppStore((state) => state.setUser);
  const clearUser = useAppStore((state) => state.clearUser);
  const hydrated = useStoreHydrated();
  const [isCheckingSession, setIsCheckingSession] = useState(true);
  const accessToken = hydrated ? getStoredAccessToken() : null;

  useEffect(() => {
    if (!hydrated) {
      return;
    }

    if (!accessToken) {
      clearUser();
      router.replace("/login");
      return;
    }

    let cancelled = false;

    getAuthenticatedUser()
      .then((authenticatedUser) => {
        if (cancelled) {
          return;
        }

        setUser(authenticatedUser);
        setIsCheckingSession(false);
      })
      .catch(() => {
        clearAccessToken();
        clearUser();

        if (!cancelled) {
          setIsCheckingSession(false);
          router.replace("/login");
        }
      });

    return () => {
      cancelled = true;
    };
  }, [accessToken, clearUser, hydrated, router, setUser]);

  if (!hydrated || !accessToken || isCheckingSession || !user) {
    return <div className="flex min-h-screen items-center justify-center text-slate-300">Carregando Tala...</div>;
  }

  return <>{children}</>;
}

export function PublicGuard({ children }: { children: ReactNode }) {
  const router = useRouter();
  const setUser = useAppStore((state) => state.setUser);
  const clearUser = useAppStore((state) => state.clearUser);
  const hydrated = useStoreHydrated();
  const [isCheckingSession, setIsCheckingSession] = useState(true);
  const accessToken = hydrated ? getStoredAccessToken() : null;

  useEffect(() => {
    if (!hydrated) {
      return;
    }

    if (!accessToken) {
      clearUser();
      return;
    }

    let cancelled = false;

    getAuthenticatedUser()
      .then((authenticatedUser) => {
        if (cancelled) {
          return;
        }

        setUser(authenticatedUser);
        setIsCheckingSession(false);
        router.replace("/dashboard");
      })
      .catch(() => {
        clearAccessToken();
        clearUser();

        if (!cancelled) {
          setIsCheckingSession(false);
        }
      });

    return () => {
      cancelled = true;
    };
  }, [accessToken, clearUser, hydrated, router, setUser]);

  if (!hydrated) {
    return <div className="flex min-h-screen items-center justify-center text-slate-300">Carregando Tala...</div>;
  }

  if (!accessToken) {
    return <>{children}</>;
  }

  if (isCheckingSession) {
    return <div className="flex min-h-screen items-center justify-center text-slate-300">Carregando Tala...</div>;
  }

  return <>{children}</>;
}
