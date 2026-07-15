import type { ReactNode } from "react";

import { cn } from "@/lib/utils";

type FeedbackPanelVariant = "default" | "accent";

const variantClasses: Record<FeedbackPanelVariant, string> = {
  default: "border-white/10 bg-white/5 text-slate-300",
  accent: "border-cyan-400/20 bg-cyan-400/8 text-slate-200",
};

export function FeedbackPanel({
  title,
  children,
  variant = "default",
  className,
  bodyClassName,
}: {
  title?: ReactNode;
  children: ReactNode;
  variant?: FeedbackPanelVariant;
  className?: string;
  bodyClassName?: string;
}) {
  const hasTitle = title !== undefined && title !== null;

  return (
    <div
      className={cn(
        "rounded-[1.5rem] border p-4",
        variantClasses[variant],
        className,
      )}
    >
      {hasTitle ? (
        <p className="text-sm uppercase tracking-[0.18em] text-cyan-200/70">{title}</p>
      ) : null}
      <div className={cn("text-sm leading-7", hasTitle ? "mt-3" : undefined, bodyClassName)}>
        {children}
      </div>
    </div>
  );
}
