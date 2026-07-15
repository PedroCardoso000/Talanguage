import { cn } from "@/lib/utils";

export function Logo({ compact = false }: { compact?: boolean }) {
  return (
    <div className="flex items-center gap-3">
      <div className="flex h-11 w-11 items-center justify-center rounded-2xl bg-[linear-gradient(135deg,#5FFBF1,#278CFF)] text-lg font-black text-slate-950 shadow-[0_0_30px_rgba(86,247,238,0.35)]">
        T
      </div>
      {!compact && (
        <div>
          <p className={cn("font-display text-2xl font-semibold text-white")}>Talanguage</p>
          <p className="text-sm text-slate-400">Prática diária com direção</p>
        </div>
      )}
    </div>
  );
}
