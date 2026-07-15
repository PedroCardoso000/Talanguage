import { authApi } from "@/features/auth/api/auth-api";
import type { LoginRequest, RegisterRequest } from "@/features/auth/contracts";
import { clearAccessToken, saveAccessToken } from "@/features/auth/lib/auth-storage";
import { toUserProfile } from "@/features/auth/lib/session";
import { toLanguageValue } from "@/lib/profile-options";
import type { LanguageOption, UserProfile } from "@/types";

export type RegisterFormValues = Omit<RegisterRequest, "targetLanguage"> & {
  targetLanguage: LanguageOption;
};

export async function loginUser(payload: LoginRequest): Promise<UserProfile> {
  const session = await authApi.login(payload);
  saveAccessToken(session.accessToken);
  return toUserProfile(session.user);
}

export async function registerUser(payload: RegisterFormValues): Promise<UserProfile> {
  const session = await authApi.register({
    name: payload.name,
    email: payload.email,
    password: payload.password,
    targetLanguage: toLanguageValue(payload.targetLanguage),
  });
  saveAccessToken(session.accessToken);
  return toUserProfile(session.user);
}

export async function getAuthenticatedUser(): Promise<UserProfile> {
  const currentUser = await authApi.me();
  return toUserProfile(currentUser);
}

export async function logoutUser() {
  try {
    await authApi.logout();
  } finally {
    clearAccessToken();
  }
}
