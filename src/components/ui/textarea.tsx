import type { TextareaHTMLAttributes } from "react";

import { cn } from "@/lib/utils";

export type TextareaProps = TextareaHTMLAttributes<HTMLTextAreaElement> & {
  label?: string;
  helperText?: string;
  errorMessage?: string;
};

export function Textarea({
  className,
  id,
  label,
  helperText,
  errorMessage,
  disabled,
  rows = 5,
  ...props
}: TextareaProps) {
  const textareaId = id ?? props.name;
  const descriptionId = helperText && textareaId ? `${textareaId}-helper` : undefined;
  const errorId = errorMessage && textareaId ? `${textareaId}-error` : undefined;

  return (
    <label className="block space-y-2">
      {label && <span className="text-sm text-slate-300">{label}</span>}
      <textarea
        id={textareaId}
        rows={rows}
        disabled={disabled}
        aria-invalid={Boolean(errorMessage)}
        aria-describedby={errorId ?? descriptionId}
        className={cn(
          "w-full rounded-[1.75rem] border bg-[#0c1730] px-4 py-4 text-sm text-white outline-none placeholder:text-slate-500 focus-visible:ring-2 focus-visible:ring-cyan-300/60 disabled:cursor-not-allowed disabled:opacity-60",
          errorMessage
            ? "border-rose-400/40 focus:border-rose-300"
            : "border-white/10 focus:border-cyan-400/35",
          className,
        )}
        {...props}
      />
      {helperText && !errorMessage && (
        <span id={descriptionId} className="block text-sm text-slate-400">
          {helperText}
        </span>
      )}
      {errorMessage && (
        <span id={errorId} className="block text-sm text-rose-300">
          {errorMessage}
        </span>
      )}
    </label>
  );
}
