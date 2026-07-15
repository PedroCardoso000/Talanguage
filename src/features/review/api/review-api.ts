import type {
  CreateFlashcardRequest,
  CreateFlashcardResponse,
  FlashcardListItem,
  FlashcardStatsResponse,
  ReviewFlashcardRequest,
  ReviewFlashcardResponse,
} from "@/features/review/contracts";
import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";

const FLASHCARDS_BASE_PATH = "/api/flashcards";

export const reviewApi = {
  listFlashcards(): Promise<FlashcardListItem[]> {
    return httpClient.get<FlashcardListItem[]>(FLASHCARDS_BASE_PATH, {
      headers: getAuthorizationHeaders(),
    });
  },

  createFlashcard(payload: CreateFlashcardRequest): Promise<CreateFlashcardResponse> {
    return httpClient.post<CreateFlashcardResponse, CreateFlashcardRequest>(FLASHCARDS_BASE_PATH, payload, {
      headers: getAuthorizationHeaders(),
    });
  },

  reviewFlashcard(
    id: string,
    payload: ReviewFlashcardRequest,
  ): Promise<ReviewFlashcardResponse> {
    return httpClient.post<ReviewFlashcardResponse, ReviewFlashcardRequest>(`${FLASHCARDS_BASE_PATH}/${id}/review`, payload, {
      headers: getAuthorizationHeaders(),
    });
  },

  getStats(): Promise<FlashcardStatsResponse> {
    return httpClient.get<FlashcardStatsResponse>(`${FLASHCARDS_BASE_PATH}/stats`, {
      headers: getAuthorizationHeaders(),
    });
  },

  deleteFlashcard(id: string): Promise<void> {
    return httpClient.delete<void>(`${FLASHCARDS_BASE_PATH}/${id}`, {
      headers: getAuthorizationHeaders(),
    });
  },
};
