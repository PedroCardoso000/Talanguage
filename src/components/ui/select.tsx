import type { SelectHTMLAttributes } from "react";

import { ChevronDown } from "lucide-react";

import { cn } from "@/lib/utils";

export type SelectOption = {
  value: string;
  label: string;
  disabled?: boolean;
};

export type SelectProps = Omit<SelectHTMLAttributes<HTMLSelectElement>, "children"> & {
  label?: string;
  helperText?: string;
  errorMessage?: string;
  options: SelectOption[];
  placeholder?: string;
};

export function Select({
  className,
  id,
  label,
  helperText,
  errorMessage,
  options,
  placeholder,
  disabled,
  ...props
}: SelectProps) {
  const selectId = id ?? props.name;
  const descriptionId = helperText && selectId ? `${selectId}-helper` : undefined;
  const errorId = errorMessage && selectId ? `${selectId}-error` : undefined;

  return (
    <label className="block space-y-2">
      {label && <span className="text-sm text-slate-300">{label}</span>}
      <div className="relative">
        <select
          id={selectId}
          disabled={disabled}
          aria-invalid={Boolean(errorMessage)}
          aria-describedby={errorId ?? descriptionId}
          className={cn(
            "h-12 w-full appearance-none rounded-2xl border bg-[#0c1730] px-4 pr-11 text-sm text-white outline-none focus-visible:ring-2 focus-visible:ring-cyan-300/60 disabled:cursor-not-allowed disabled:opacity-60",
            errorMessage
              ? "border-rose-400/40 focus:border-rose-300"
              : "border-white/10 focus:border-cyan-400/35",
            className,
          )}
          {...props}
        >
          {placeholder && (
            <option value="" disabled>
              {placeholder}
            </option>
          )}
          {options.map((option) => (
            <option key={option.value} value={option.value} disabled={option.disabled}>
              {option.label}
            </option>
          ))}
        </select>
        <ChevronDown className="pointer-events-none absolute right-4 top-1/2 h-4 w-4 -translate-y-1/2 text-slate-400" />
      </div>
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
