"use client";

import { useRouter } from "next/navigation";
import { useEffect } from "react";

import { useAppStore } from "@/store/use-app-store";
import { useStoreHydrated } from "@/store/use-store-hydrated";

export default function HomePage() {
  const router = useRouter();
  const user = useAppStore((state) => state.user);
  const hydrated = useStoreHydrated();

  useEffect(() => {
    if (!hydrated) {
      return;
    }

    router.replace(user ? "/dashboard" : "/login");
  }, [hydrated, router, user]);

  return <div className="flex min-h-screen items-center justify-center bg-[#050b1b] text-slate-300">Inicializando Tala...</div>;
}
