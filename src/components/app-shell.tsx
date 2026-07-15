import type { ReactNode } from "react";

import { Sidebar } from "@/components/sidebar";
import { Topbar } from "@/components/topbar";

export function AppShell({ children }: { children: ReactNode }) {
  return (
    <div className="min-h-screen bg-[#050b1b] text-white">
      <div className="absolute inset-0 bg-[radial-gradient(circle_at_top_left,rgba(63,198,255,0.12),transparent_25%),radial-gradient(circle_at_bottom_right,rgba(0,103,255,0.16),transparent_35%),linear-gradient(180deg,#050b1b,#071122_40%,#06111f)]" />
      <div className="relative flex min-h-screen">
        <Sidebar />
        <div className="flex min-h-screen min-w-0 flex-1 flex-col">
          <Topbar />
          <main className="flex-1 px-4 py-5 sm:px-6">{children}</main>
        </div>
      </div>
    </div>
  );
}
