import Link from "next/link";
import { ArrowRight } from "lucide-react";

export function ModuleCard({
  href,
  title,
  description,
  meta,
  accent,
  metaLabel,
}: {
  href: string;
  title: string;
  description: string;
  meta: string;
  accent: string;
  metaLabel?: string;
}) {
  return (
    <Link
      href={href}
      className="group rounded-[1.75rem] border border-white/10 bg-white/5 p-5 transition hover:-translate-y-1 hover:border-cyan-400/30 hover:bg-white/7"
    >
      <div
        className="mb-5 h-14 w-14 rounded-2xl"
        style={{ background: accent, boxShadow: "0 0 40px rgba(95,251,241,0.15)" }}
      />
      <div className="flex items-start justify-between gap-4">
        <div>
          <h3 className="text-2xl font-semibold text-white">{title}</h3>
          <p className="mt-3 text-sm leading-6 text-slate-300">{description}</p>
        </div>
        <ArrowRight className="mt-1 h-5 w-5 text-slate-500 transition group-hover:text-cyan-300" />
      </div>
      <div className="mt-6 h-2 overflow-hidden rounded-full bg-white/5">
        <div className="h-full rounded-full bg-[linear-gradient(90deg,#5FFBF1,#35A7FF)]" style={{ width: meta }} />
      </div>
      <p className="mt-3 text-sm text-cyan-200">{metaLabel ?? `${meta} concluído`}</p>
    </Link>
  );
}
