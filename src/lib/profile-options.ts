import type { LanguageLevelOption, LanguageOption } from "@/types";

export const DEFAULT_TARGET_LANGUAGE: LanguageOption = "Ingles";

export const LANGUAGE_OPTIONS: Array<{
  label: LanguageOption;
  value: "ENGLISH" | "SPANISH" | "FRENCH";
}> = [
  { label: "Ingles", value: "ENGLISH" },
  { label: "Espanhol", value: "SPANISH" },
  { label: "Frances", value: "FRENCH" },
];

export const LEVEL_OPTIONS: Array<{
  label: LanguageLevelOption;
  value: "BEGINNER" | "INTERMEDIATE" | "ADVANCED";
}> = [
  { label: "Basico", value: "BEGINNER" },
  { label: "Intermediario", value: "INTERMEDIATE" },
  { label: "Avancado", value: "ADVANCED" },
];

export function toLanguageOption(value: string | null | undefined): LanguageOption {
  return LANGUAGE_OPTIONS.find((option) => option.value === value)?.label ?? DEFAULT_TARGET_LANGUAGE;
}

export function toLanguageValue(value: LanguageOption): "ENGLISH" | "SPANISH" | "FRENCH" {
  return LANGUAGE_OPTIONS.find((option) => option.label === value)?.value ?? "ENGLISH";
}

export function toLevelOption(value: string | null | undefined): LanguageLevelOption | null {
  return LEVEL_OPTIONS.find((option) => option.value === value)?.label ?? null;
}

export function toLevelValue(value: LanguageLevelOption | null): "BEGINNER" | "INTERMEDIATE" | "ADVANCED" | null {
  if (!value) {
    return null;
  }

  return LEVEL_OPTIONS.find((option) => option.label === value)?.value ?? null;
}
