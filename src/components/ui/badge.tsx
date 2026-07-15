import type { HTMLAttributes } from "react";

import { cn } from "@/lib/utils";

type BadgeVariant = "default" | "muted" | "success" | "danger" | "outline";

const badgeVariants: Record<BadgeVariant, string> = {
  default: "border-cyan-400/20 bg-cyan-400/10 text-cyan-200",
  muted: "border-white/10 bg-white/5 text-slate-300",
  success: "border-emerald-400/20 bg-emerald-400/10 text-emerald-200",
  danger: "border-rose-400/20 bg-rose-400/10 text-rose-200",
  outline: "border-white/15 bg-transparent text-white",
};

export type BadgeProps = HTMLAttributes<HTMLSpanElement> & {
  variant?: BadgeVariant;
};

export function Badge({
  className,
  variant = "default",
  ...props
}: BadgeProps) {
  return (
    <span
      className={cn(
        "inline-flex items-center rounded-full border px-3 py-1 text-sm font-medium",
        badgeVariants[variant],
        className,
      )}
      {...props}
    />
  );
}
