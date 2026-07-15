import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";
import type { ProfileResponse, UpdateProfileRequest } from "@/features/profile/contracts";

const PROFILE_BASE_PATH = "/api/profile";

export const profileApi = {
  getProfile(): Promise<ProfileResponse> {
    return httpClient.get<ProfileResponse>(`${PROFILE_BASE_PATH}/me`, {
      headers: getAuthorizationHeaders(),
    });
  },

  updateProfile(payload: UpdateProfileRequest): Promise<ProfileResponse> {
    return httpClient.put<ProfileResponse, UpdateProfileRequest>(`${PROFILE_BASE_PATH}/me`, payload, {
      headers: getAuthorizationHeaders(),
    });
  },
};
