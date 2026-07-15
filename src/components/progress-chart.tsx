import { weeklyLabels } from "@/data/mock-content";

export function ProgressChart({
  values,
  title = "Sua evolucao esta semana",
  description = "Minutos praticos registrados por dia.",
  valueSuffix = "m",
}: {
  values: number[];
  title?: string;
  description?: string;
  valueSuffix?: string;
}) {
  const maxValue = Math.max(...values, 1);

  return (
    <div className="rounded-[1.75rem] border border-white/10 bg-white/5 p-5">
      <div className="mb-6 flex items-center justify-between">
        <div>
          <h3 className="text-xl font-semibold text-white">{title}</h3>
          <p className="text-sm text-slate-400">{description}</p>
        </div>
        <span className="rounded-full border border-cyan-400/20 bg-cyan-400/10 px-3 py-1 text-sm text-cyan-200">
          Esta semana
        </span>
      </div>
      <div className="flex h-64 items-end gap-4">
        {values.map((value, index) => (
          <div key={weeklyLabels[index]} className="flex flex-1 flex-col items-center gap-3">
            <div className="flex h-52 w-full items-end">
              <div
                className="w-full rounded-t-2xl bg-[linear-gradient(180deg,rgba(95,251,241,0.9),rgba(53,167,255,0.35))]"
                style={{ height: `${Math.max((value / maxValue) * 100, 6)}%` }}
              />
            </div>
            <div className="text-center">
              <p className="text-sm font-medium text-white">
                {value}
                {valueSuffix}
              </p>
              <p className="text-xs text-slate-400">{weeklyLabels[index]}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
