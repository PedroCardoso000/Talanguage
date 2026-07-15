import type { ReactNode } from "react";

export function PageShell({
  title,
  description,
  action,
  children,
}: {
  title: string;
  description: string;
  action?: ReactNode;
  children: ReactNode;
}) {
  return (
    <div className="space-y-6">
      <div className="flex flex-col gap-4 rounded-[2rem] border border-white/10 bg-white/5 p-6 backdrop-blur sm:flex-row sm:items-end sm:justify-between">
        <div className="space-y-2">
          <p className="text-sm uppercase tracking-[0.24em] text-cyan-200/70">Talanguage</p>
          <h1 className="font-display text-4xl font-semibold text-white">{title}</h1>
          <p className="max-w-2xl text-base leading-7 text-slate-300">{description}</p>
        </div>
        {action}
      </div>
      {children}
    </div>
  );
}
