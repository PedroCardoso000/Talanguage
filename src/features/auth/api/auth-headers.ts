import { ApiClientError } from "@/api/http-client";
import { getStoredAccessToken } from "@/features/auth/lib/auth-storage";

export function getAuthorizationHeaders() {
  const accessToken = getStoredAccessToken();

  if (!accessToken) {
    throw new ApiClientError("Autenticacao obrigatoria.", { status: 401 });
  }

  return {
    Authorization: `Bearer ${accessToken}`,
  };
}
