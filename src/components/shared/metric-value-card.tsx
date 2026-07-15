import { cn } from "@/lib/utils";

export function MetricValueCard({
  label,
  value,
  className,
  labelClassName,
  valueClassName,
}: {
  label: string;
  value: string | number;
  className?: string;
  labelClassName?: string;
  valueClassName?: string;
}) {
  return (
    <div
      className={cn(
        "rounded-[1.5rem] border border-white/10 bg-white/5 p-4",
        className,
      )}
    >
      <p className={cn("text-sm text-slate-400", labelClassName)}>{label}</p>
      <p className={cn("mt-2 text-3xl font-semibold text-white", valueClassName)}>
        {value}
      </p>
    </div>
  );
}
