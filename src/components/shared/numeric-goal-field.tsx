import type { InputHTMLAttributes } from "react";

import { cn } from "@/lib/utils";

export function NumericGoalField({
  label,
  value,
  onChange,
  className,
  ...props
}: Omit<InputHTMLAttributes<HTMLInputElement>, "type" | "value" | "onChange"> & {
  label: string;
  value: number;
  onChange: (value: number) => void;
  className?: string;
}) {
  return (
    <label className={cn("rounded-[1.75rem] border border-white/10 bg-white/5 p-5", className)}>
      <span className="text-sm text-slate-400">{label}</span>
      <input
        type="number"
        min={1}
        value={value}
        onChange={(event) => onChange(Number(event.target.value))}
        className="mt-4 h-14 w-full rounded-2xl border border-white/10 bg-[#0c1730] px-4 text-2xl font-semibold text-white outline-none focus:border-cyan-400/35"
        {...props}
      />
    </label>
  );
}
