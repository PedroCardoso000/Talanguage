import type { CommunityLanguage } from "@/features/community/contracts";

const options: Array<{ value: CommunityLanguage | "ALL"; label: string }> = [
  { value: "ALL", label: "Todos" },
  { value: "ENGLISH", label: "English" },
  { value: "SPANISH", label: "Spanish" },
];

export function CommunityFilters({
  selectedLanguage,
  onSelect,
}: {
  selectedLanguage: CommunityLanguage | "ALL";
  onSelect: (language: CommunityLanguage | "ALL") => void;
}) {
  return (
    <div className="flex flex-wrap gap-3">
      {options.map((option) => {
        const active = option.value === selectedLanguage;
        return (
          <button
            key={option.value}
            type="button"
            onClick={() => onSelect(option.value)}
            className={`rounded-full border px-4 py-2 text-sm transition ${
              active
                ? "border-cyan-400/40 bg-cyan-400/12 text-cyan-200"
                : "border-white/10 bg-white/5 text-slate-300 hover:border-white/20 hover:text-white"
            }`}
          >
            {option.label}
          </button>
        );
      })}
    </div>
  );
}
