import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";
import type {
  CommunityInterestResponse,
  ListPracticeGroupsQuery,
  ListPracticePartnersQuery,
  PracticeGroup,
  PracticePartner,
  RegisterCommunityInterestRequest,
} from "@/features/community/contracts";

const COMMUNITY_BASE_PATH = "/api/community";

function buildQuery(params: Record<string, string | undefined>) {
  const search = new URLSearchParams();

  Object.entries(params).forEach(([key, value]) => {
    if (value) {
      search.set(key, value);
    }
  });

  return search.size > 0 ? `?${search.toString()}` : "";
}

export const communityApi = {
  listGroups(query?: ListPracticeGroupsQuery): Promise<PracticeGroup[]> {
    return httpClient.get<PracticeGroup[]>(
      `${COMMUNITY_BASE_PATH}/groups${buildQuery({
        language: query?.language,
        level: query?.level,
      })}`,
      { headers: getAuthorizationHeaders() },
    );
  },

  listPartners(query?: ListPracticePartnersQuery): Promise<PracticePartner[]> {
    return httpClient.get<PracticePartner[]>(
      `${COMMUNITY_BASE_PATH}/partners${buildQuery({
        language: query?.language,
      })}`,
      { headers: getAuthorizationHeaders() },
    );
  },

  registerInterest(
    payload: RegisterCommunityInterestRequest,
  ): Promise<CommunityInterestResponse> {
    return httpClient.post<CommunityInterestResponse, RegisterCommunityInterestRequest>(
      `${COMMUNITY_BASE_PATH}/interests`,
      payload,
      { headers: getAuthorizationHeaders() },
    );
  },
};
