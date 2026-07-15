import type { FlashcardLanguage } from "@/features/review/contracts/requests";

export type FlashcardListItem = {
  id: string;
  front: string;
  back: string;
  language: FlashcardLanguage;
  nextReviewAt?: string;
  tags?: string[];
};

export type CreateFlashcardResponse = {
  id: string;
  front: string;
  back: string;
  language: FlashcardLanguage;
  nextReviewAt: string;
};

export type ReviewFlashcardResponse = {
  id: string;
  nextReviewAt: string;
  reviewCount: number;
};

export type FlashcardStatsResponse = {
  reviewedCount: number;
  correctCount: number;
  wrongCount: number;
  overallProgress: number;
};
