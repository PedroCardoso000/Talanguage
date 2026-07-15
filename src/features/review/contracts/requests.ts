export type FlashcardLanguage = "ENGLISH" | "SPANISH" | "FRENCH";
export type FlashcardReviewRating = "AGAIN" | "HARD" | "GOOD" | "EASY";

export type CreateFlashcardRequest = {
  front: string;
  back: string;
  language: FlashcardLanguage;
  tags: string[];
};

export type ReviewFlashcardRequest = {
  rating: FlashcardReviewRating;
};
