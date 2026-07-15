import type {
  CurrentMockTestResponse,
  MockTestResult,
  MockTestSubmissionRequest,
} from "@/features/mock-test/contracts";
import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";

const MOCK_TESTS_BASE_PATH = "/api/mock-tests";

export const mockTestApi = {
  getCurrent(): Promise<CurrentMockTestResponse> {
    return httpClient.get<CurrentMockTestResponse>(`${MOCK_TESTS_BASE_PATH}/current`, {
      headers: getAuthorizationHeaders(),
    });
  },

  submit(payload: MockTestSubmissionRequest): Promise<MockTestResult> {
    return httpClient.post<MockTestResult, MockTestSubmissionRequest>(`${MOCK_TESTS_BASE_PATH}/attempts`, payload, {
      headers: getAuthorizationHeaders(),
    });
  },

  getAttempt(attemptId: string): Promise<MockTestResult> {
    return httpClient.get<MockTestResult>(`${MOCK_TESTS_BASE_PATH}/attempts/${attemptId}`, {
      headers: getAuthorizationHeaders(),
    });
  },
};
