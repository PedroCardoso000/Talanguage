import type { HTMLAttributes } from "react";

import { clamp, cn } from "@/lib/utils";

export type ProgressBarProps = HTMLAttributes<HTMLDivElement> & {
  value: number;
  max?: number;
  tone?: "primary" | "success" | "warning";
  showValue?: boolean;
};

const toneClasses = {
  primary: "bg-[linear-gradient(90deg,#5FFBF1,#35A7FF)]",
  success: "bg-[linear-gradient(90deg,#7FFF8E,#4DBD5F)]",
  warning: "bg-[linear-gradient(90deg,#FFE07A,#F89B29)]",
};

export function ProgressBar({
  className,
  value,
  max = 100,
  tone = "primary",
  showValue = false,
  ...props
}: ProgressBarProps) {
  const percentage = clamp((value / max) * 100, 0, 100);

  return (
    <div className="space-y-2">
      <div
        className={cn("h-3 overflow-hidden rounded-full bg-white/5", className)}
        role="progressbar"
        aria-valuenow={Math.round(percentage)}
        aria-valuemin={0}
        aria-valuemax={100}
        {...props}
      >
        <div className={cn("h-full rounded-full", toneClasses[tone])} style={{ width: `${percentage}%` }} />
      </div>
      {showValue && <p className="text-sm text-slate-300">{Math.round(percentage)}%</p>}
    </div>
  );
}
