import type {
  CreateWritingSubmissionRequest,
  CreateWritingSubmissionResponse,
  GetWritingChallengesQuery,
  RecentWritingSubmission,
  WritingChallenge,
} from "@/features/writing/contracts";
import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";

const WRITING_BASE_PATH = "/api/writing";

export const writingApi = {
  getChallenges(query?: GetWritingChallengesQuery): Promise<WritingChallenge[]> {
    const params = new URLSearchParams();

    if (query?.language) {
      params.set("language", query.language);
    }

    if (query?.level) {
      params.set("level", query.level);
    }

    const suffix = params.size > 0 ? `?${params.toString()}` : "";
    return httpClient.get<WritingChallenge[]>(`${WRITING_BASE_PATH}/challenges${suffix}`);
  },

  getCurrentChallenge(): Promise<WritingChallenge> {
    return httpClient.get<WritingChallenge>(`${WRITING_BASE_PATH}/challenges/current`);
  },

  submit(payload: CreateWritingSubmissionRequest): Promise<CreateWritingSubmissionResponse> {
    return httpClient.post<CreateWritingSubmissionResponse, CreateWritingSubmissionRequest>(
      `${WRITING_BASE_PATH}/submissions`,
      payload,
      { headers: getAuthorizationHeaders() },
    );
  },

  getRecentSubmissions(): Promise<RecentWritingSubmission[]> {
    return httpClient.get<RecentWritingSubmission[]>(`${WRITING_BASE_PATH}/submissions/recent`, {
      headers: getAuthorizationHeaders(),
    });
  },
};
