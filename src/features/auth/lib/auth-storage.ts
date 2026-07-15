import type { LanguageOption } from "@/types";

const ACCESS_TOKEN_STORAGE_KEY = "tala-auth-access-token";
const LANGUAGE_PREFERENCES_STORAGE_KEY = "tala-auth-language-preferences";

type LanguagePreferences = Record<string, LanguageOption>;

function isBrowser() {
  return typeof window !== "undefined";
}

function normalizeEmail(email: string) {
  return email.trim().toLowerCase();
}

function readLanguagePreferences(): LanguagePreferences {
  if (!isBrowser()) {
    return {};
  }

  const rawValue = window.localStorage.getItem(LANGUAGE_PREFERENCES_STORAGE_KEY);

  if (!rawValue) {
    return {};
  }

  try {
    return JSON.parse(rawValue) as LanguagePreferences;
  } catch {
    return {};
  }
}

function writeLanguagePreferences(preferences: LanguagePreferences) {
  if (!isBrowser()) {
    return;
  }

  window.localStorage.setItem(LANGUAGE_PREFERENCES_STORAGE_KEY, JSON.stringify(preferences));
}

export function getStoredAccessToken() {
  if (!isBrowser()) {
    return null;
  }

  return window.localStorage.getItem(ACCESS_TOKEN_STORAGE_KEY);
}

export function saveAccessToken(accessToken: string) {
  if (!isBrowser()) {
    return;
  }

  window.localStorage.setItem(ACCESS_TOKEN_STORAGE_KEY, accessToken);
}

export function clearAccessToken() {
  if (!isBrowser()) {
    return;
  }

  window.localStorage.removeItem(ACCESS_TOKEN_STORAGE_KEY);
}

export function savePreferredLanguage(email: string, targetLanguage: LanguageOption) {
  const preferences = readLanguagePreferences();
  preferences[normalizeEmail(email)] = targetLanguage;
  writeLanguagePreferences(preferences);
}

export function getPreferredLanguage(email: string) {
  return readLanguagePreferences()[normalizeEmail(email)];
}
