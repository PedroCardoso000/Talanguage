import type { ReactNode } from "react";

import { PublicGuard } from "@/components/auth-guard";

export default function AuthLayout({ children }: { children: ReactNode }) {
  return <PublicGuard>{children}</PublicGuard>;
}
