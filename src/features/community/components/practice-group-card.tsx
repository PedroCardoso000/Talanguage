import { InterestButton } from "@/features/community/components/interest-button";
import type { PracticeGroup } from "@/features/community/contracts";

export function PracticeGroupCard({
  group,
  loading,
  registered,
  onInterest,
}: {
  group: PracticeGroup;
  loading: boolean;
  registered: boolean;
  onInterest: () => void;
}) {
  return (
    <div className="rounded-[1.75rem] border border-white/10 bg-white/5 p-5">
      <div className="flex flex-wrap items-center gap-3">
        <span className="rounded-full border border-cyan-400/25 bg-cyan-400/10 px-3 py-1 text-xs uppercase tracking-[0.16em] text-cyan-200">
          {group.language}
        </span>
        <span className="rounded-full border border-white/10 bg-white/5 px-3 py-1 text-xs text-slate-300">
          {group.level}
        </span>
      </div>
      <h3 className="mt-4 text-2xl font-semibold text-white">{group.title}</h3>
      <p className="mt-3 text-sm leading-7 text-slate-300">{group.description}</p>
      <p className="mt-4 text-sm text-slate-400">{group.memberCount} membros ativos</p>
      <div className="mt-5">
        <InterestButton
          label="Quero participar"
          loading={loading}
          registered={registered}
          onClick={onInterest}
        />
      </div>
    </div>
  );
}
