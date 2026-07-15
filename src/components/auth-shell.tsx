import type { ReactNode } from "react";

import { Logo } from "@/components/logo";
import {
  authMarketingBadge,
  authMarketingDescription,
  authMarketingFooter,
  authMarketingHeadline,
  authMarketingHighlights,
} from "@/features/auth/mocks/marketing";

export function AuthShell({
  title,
  description,
  children,
}: {
  title: string;
  description: string;
  children: ReactNode;
}) {
  return (
    <div className="relative min-h-screen overflow-hidden bg-[#050b1b] text-white">
      <div className="absolute inset-0 bg-[radial-gradient(circle_at_top_left,rgba(66,209,255,0.18),transparent_30%),radial-gradient(circle_at_bottom_center,rgba(35,118,255,0.2),transparent_28%),linear-gradient(180deg,#040817,#071127_65%,#06101f)]" />
      <div className="absolute inset-x-0 bottom-[-10%] h-[36rem] rounded-full bg-[radial-gradient(circle,rgba(85,249,241,0.16),transparent_55%)] blur-3xl" />
      <div className="relative mx-auto grid min-h-screen max-w-7xl gap-12 px-6 py-8 lg:grid-cols-[1.1fr_0.9fr] lg:px-10">
        <section className="flex flex-col justify-between py-6">
          <Logo />
          <div className="max-w-2xl space-y-2">
            <span className="inline-flex rounded-full border border-cyan-400/25 bg-cyan-400/10 px-4 py-2 text-sm text-cyan-200">
              {authMarketingBadge}
            </span>
            <div className="space-y-5">
              <h1 className="font-display text-5xl font-semibold leading-tight text-white sm:text-6xl">
                Pratique <span className="text-cyan-300">{authMarketingHeadline.accent}</span> com consistência e feedback acionável.
              </h1>
              <p className="max-w-xl text-lg leading-8 text-slate-300">
                {authMarketingDescription}
              </p>
            </div>
            <div className="grid gap-4 md:grid-cols-3">
              {authMarketingHighlights.map(([label, text]) => (
                <div key={label} className="rounded-3xl border border-white/10 bg-white/5 p-5 backdrop-blur">
                  <h2 className="mb-2 text-lg font-semibold">{label}</h2>
                  <p className="text-sm leading-6 text-slate-300">{text}</p>
                </div>
              ))}
            </div>
          </div>
        </section>
        <section className="flex items-center justify-center lg:justify-end">
          <div className="w-full max-w-xl rounded-[2rem] border border-cyan-500/15 bg-[linear-gradient(180deg,rgba(9,17,38,0.92),rgba(8,14,31,0.96))] p-8 shadow-[0_20px_80px_rgba(0,0,0,0.45)] backdrop-blur-2xl sm:p-10">
            <div className="mb-8 space-y-2">
              <h2 className="font-display text-4xl font-semibold">{title}</h2>
              <p className="text-base text-slate-300">{description}</p>
            </div>
            {children}
          </div>
        </section>
      </div>
    </div>
  );
}
