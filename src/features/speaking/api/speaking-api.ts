import type {
  FinishSpeakingSessionResponse,
  RecentSpeakingSession,
  SpeakingTopic,
  StartSpeakingSessionRequest,
  StartSpeakingSessionResponse,
  SubmitSpeakingResponseRequest,
  SubmitSpeakingResponseResponse,
} from "@/features/speaking/contracts";
import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";

const SPEAKING_BASE_PATH = "/api/speaking";

export const speakingApi = {
  getTopics(): Promise<SpeakingTopic[]> {
    return httpClient.get<SpeakingTopic[]>(`${SPEAKING_BASE_PATH}/topics`, {
      headers: getAuthorizationHeaders(),
    });
  },

  startSession(payload: StartSpeakingSessionRequest): Promise<StartSpeakingSessionResponse> {
    return httpClient.post<StartSpeakingSessionResponse, StartSpeakingSessionRequest>(
      `${SPEAKING_BASE_PATH}/sessions`,
      payload,
      { headers: getAuthorizationHeaders() },
    );
  },

  submitResponse(
    sessionId: string,
    payload: SubmitSpeakingResponseRequest,
  ): Promise<SubmitSpeakingResponseResponse> {
    return httpClient.post<SubmitSpeakingResponseResponse, SubmitSpeakingResponseRequest>(
      `${SPEAKING_BASE_PATH}/sessions/${sessionId}/responses`,
      payload,
      { headers: getAuthorizationHeaders() },
    );
  },

  finishSession(sessionId: string): Promise<FinishSpeakingSessionResponse> {
    return httpClient.post<FinishSpeakingSessionResponse>(
      `${SPEAKING_BASE_PATH}/sessions/${sessionId}/finish`,
      undefined,
      { headers: getAuthorizationHeaders() },
    );
  },

  getRecentSessions(): Promise<RecentSpeakingSession[]> {
    return httpClient.get<RecentSpeakingSession[]>(`${SPEAKING_BASE_PATH}/sessions/recent`, {
      headers: getAuthorizationHeaders(),
    });
  },
};
