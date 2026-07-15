import { InterestButton } from "@/features/community/components/interest-button";
import type { PracticePartner } from "@/features/community/contracts";

export function PracticePartnerCard({
  partner,
  loading,
  registered,
  onInterest,
}: {
  partner: PracticePartner;
  loading: boolean;
  registered: boolean;
  onInterest: () => void;
}) {
  return (
    <div className="rounded-[1.75rem] border border-white/10 bg-white/5 p-5">
      <div className="flex items-start justify-between gap-4">
        <div>
          <h3 className="text-2xl font-semibold text-white">{partner.displayName}</h3>
          <p className="mt-2 text-sm text-slate-400">{partner.level}</p>
        </div>
        <div className="rounded-full border border-white/10 bg-white/5 px-3 py-1 text-xs text-cyan-200">
          {partner.languagesPracticed.join(" / ")}
        </div>
      </div>
      <p className="mt-4 text-sm leading-7 text-slate-300">{partner.availabilityNote}</p>
      <div className="mt-5">
        <InterestButton
          label="Quero conectar"
          loading={loading}
          registered={registered}
          onClick={onInterest}
        />
      </div>
    </div>
  );
}
