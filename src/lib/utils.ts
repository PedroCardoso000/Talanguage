import { clsx } from "clsx";

export function cn(...inputs: Array<string | false | null | undefined>) {
  return clsx(inputs);
}

export function getTodayKey() {
  return new Date().toISOString().slice(0, 10);
}

export function getWeekIndex() {
  const day = new Date().getDay();
  return day === 0 ? 6 : day - 1;
}

export function clamp(value: number, min: number, max: number) {
  return Math.min(Math.max(value, min), max);
}
