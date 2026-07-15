"use client";

import { Eye, EyeOff } from "lucide-react";
import { useState } from "react";
import type { InputHTMLAttributes } from "react";

import { cn } from "@/lib/utils";

export type InputProps = InputHTMLAttributes<HTMLInputElement> & {
  label?: string;
  helperText?: string;
  errorMessage?: string;
};

export function Input({
  className,
  id,
  label,
  helperText,
  errorMessage,
  disabled,
  type = "text",
  ...props
}: InputProps) {
  const [showPassword, setShowPassword] = useState(false);
  const inputId = id ?? props.name;
  const descriptionId = helperText && inputId ? `${inputId}-helper` : undefined;
  const errorId = errorMessage && inputId ? `${inputId}-error` : undefined;
  const isPasswordInput = type === "password";
  const resolvedType = isPasswordInput && showPassword ? "text" : type;

  return (
    <label className="block space-y-2">
      {label && <span className="text-sm text-slate-300">{label}</span>}
      <div className="relative">
        <input
          id={inputId}
          type={resolvedType}
          disabled={disabled}
          aria-invalid={Boolean(errorMessage)}
          aria-describedby={errorId ?? descriptionId}
          className={cn(
            "h-12 w-full rounded-2xl border bg-[#0c1730] px-4 text-sm text-white outline-none placeholder:text-slate-500 focus-visible:ring-2 focus-visible:ring-cyan-300/60 disabled:cursor-not-allowed disabled:opacity-60",
            isPasswordInput && "pr-11",
            errorMessage
              ? "border-rose-400/40 focus:border-rose-300"
              : "border-white/10 focus:border-cyan-400/35",
            className,
          )}
          {...props}
        />
        {isPasswordInput && (
          <button
            type="button"
            onClick={() => setShowPassword((current) => !current)}
            disabled={disabled}
            aria-label={showPassword ? "Ocultar senha" : "Mostrar senha"}
            className="absolute inset-y-0 right-3 flex items-center text-slate-400 transition hover:text-white focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-cyan-300/60 disabled:cursor-not-allowed disabled:opacity-50"
          >
            {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
          </button>
        )}
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
