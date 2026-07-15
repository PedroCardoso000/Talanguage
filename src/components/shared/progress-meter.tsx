import { ProgressBar } from "@/components/ui";
import { cn } from "@/lib/utils";

export function ProgressMeter({
  label,
  value,
  valueLabel,
  color = "from-cyan-300 to-sky-500",
  className,
}: {
  label: string;
  value: number;
  valueLabel?: string;
  color?: string;
  className?: string;
}) {
  return (
    <div className={className}>
      <div className="mb-2 flex items-center justify-between">
        <p className="text-sm text-slate-300">{label}</p>
        <p className="text-xl font-semibold text-white">
          {valueLabel ?? value}
        </p>
      </div>
      <ProgressBar
        value={value}
        className={cn("h-3", className)}
        tone="primary"
      />
      <div
        className={cn(
          "pointer-events-none -mt-3 h-3 rounded-full bg-gradient-to-r",
          color,
        )}
        style={{ width: `${Math.max(0, Math.min(value, 100))}%` }}
      />
    </div>
  );
}
