import { httpClient } from "@/api/http-client";
import type {
  AuthSessionResponse,
  CurrentSessionResponse,
  LoginRequest,
  RegisterRequest,
} from "@/features/auth/contracts";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";

const AUTH_BASE_PATH = "/api/auth";

export const authApi = {
  register(payload: RegisterRequest): Promise<AuthSessionResponse> {
    return httpClient.post<AuthSessionResponse, RegisterRequest>(`${AUTH_BASE_PATH}/register`, payload);
  },

  login(payload: LoginRequest): Promise<AuthSessionResponse> {
    return httpClient.post<AuthSessionResponse, LoginRequest>(`${AUTH_BASE_PATH}/login`, payload);
  },

  me(): Promise<CurrentSessionResponse> {
    return httpClient.get<CurrentSessionResponse>(`${AUTH_BASE_PATH}/me`, {
      headers: getAuthorizationHeaders(),
    });
  },

  logout(): Promise<void> {
    return httpClient.post<void>(`${AUTH_BASE_PATH}/logout`, undefined, {
      headers: getAuthorizationHeaders(),
    });
  },
};
