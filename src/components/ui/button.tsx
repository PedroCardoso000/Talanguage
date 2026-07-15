import type { ButtonHTMLAttributes, ReactNode } from "react";

import { cn } from "@/lib/utils";

type ButtonVariant = "primary" | "secondary" | "ghost" | "danger";
type ButtonSize = "sm" | "md" | "lg" | "icon";

const variantClasses: Record<ButtonVariant, string> = {
  primary:
    "bg-[linear-gradient(135deg,#5FFBF1,#35A7FF)] text-slate-950 hover:brightness-105 disabled:hover:brightness-100",
  secondary:
    "border border-cyan-400/30 bg-cyan-400/10 text-cyan-200 hover:bg-cyan-400/15",
  ghost:
    "border border-white/10 bg-white/5 text-slate-200 hover:bg-white/10",
  danger:
    "border border-rose-400/30 bg-rose-400/10 text-rose-200 hover:bg-rose-400/15",
};

const sizeClasses: Record<ButtonSize, string> = {
  sm: "h-10 px-4 text-sm",
  md: "h-12 px-5 text-sm",
  lg: "h-14 px-6 text-base",
  icon: "h-11 w-11 p-0",
};

export type ButtonProps = ButtonHTMLAttributes<HTMLButtonElement> & {
  variant?: ButtonVariant;
  size?: ButtonSize;
  loading?: boolean;
  leadingIcon?: ReactNode;
  trailingIcon?: ReactNode;
};

export function Button({
  className,
  variant = "primary",
  size = "md",
  loading = false,
  leadingIcon,
  trailingIcon,
  children,
  disabled,
  type = "button",
  ...props
}: ButtonProps) {
  return (
    <button
      type={type}
      className={cn(
        "inline-flex items-center justify-center gap-2 rounded-2xl font-semibold outline-none focus-visible:ring-2 focus-visible:ring-cyan-300/70 focus-visible:ring-offset-2 focus-visible:ring-offset-[#050b1b] disabled:cursor-not-allowed disabled:opacity-60",
        variantClasses[variant],
        sizeClasses[size],
        className,
      )}
      disabled={disabled || loading}
      aria-busy={loading}
      {...props}
    >
      {loading ? <span className="h-4 w-4 animate-spin rounded-full border-2 border-current border-t-transparent" /> : leadingIcon}
      {children}
      {!loading && trailingIcon}
    </button>
  );
}
