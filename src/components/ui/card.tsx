import type { HTMLAttributes, ReactNode } from "react";

import { cn } from "@/lib/utils";

type CardProps = HTMLAttributes<HTMLDivElement>;

export function Card({ className, ...props }: CardProps) {
  return (
    <div
      className={cn("rounded-[1.75rem] border border-white/10 bg-white/5 p-5 backdrop-blur", className)}
      {...props}
    />
  );
}

export function CardHeader({
  className,
  ...props
}: HTMLAttributes<HTMLDivElement>) {
  return <div className={cn("mb-4 space-y-2", className)} {...props} />;
}

export function CardTitle({
  className,
  ...props
}: HTMLAttributes<HTMLHeadingElement>) {
  return <h3 className={cn("text-xl font-semibold text-white", className)} {...props} />;
}

export function CardDescription({
  className,
  ...props
}: HTMLAttributes<HTMLParagraphElement>) {
  return <p className={cn("text-sm leading-6 text-slate-300", className)} {...props} />;
}

export function CardContent({
  className,
  ...props
}: HTMLAttributes<HTMLDivElement>) {
  return <div className={cn("space-y-4", className)} {...props} />;
}

export function CardFooter({
  className,
  ...props
}: HTMLAttributes<HTMLDivElement>) {
  return <div className={cn("mt-5 flex items-center gap-3", className)} {...props} />;
}

export function CardAccent({
  className,
  children,
  ...props
}: HTMLAttributes<HTMLDivElement> & { children?: ReactNode }) {
  return (
    <div
      className={cn("rounded-[1.5rem] border border-cyan-400/20 bg-cyan-400/8 p-4", className)}
      {...props}
    >
      {children}
    </div>
  );
}
