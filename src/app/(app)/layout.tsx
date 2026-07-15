import type { ReactNode } from "react";

import { ProtectedGuard } from "@/components/auth-guard";
import { AppShell } from "@/components/app-shell";

export default function ProtectedLayout({ children }: { children: ReactNode }) {
  return (
    <ProtectedGuard>
      <AppShell>{children}</AppShell>
    </ProtectedGuard>
  );
}
