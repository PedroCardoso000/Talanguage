"use client";

import { useEffect, useMemo, useState } from "react";

import { reviewApi } from "@/features/review/api/review-api";
import type {
  CreateFlashcardRequest,
  FlashcardLanguage,
  FlashcardListItem,
  FlashcardStatsResponse,
} from "@/features/review/contracts";
import { useAppStore } from "@/store/use-app-store";

type ReviewSessionState = {
  cards: FlashcardListItem[];
  currentCard: FlashcardListItem | null;
  currentIndex: number;
  stats: FlashcardStatsResponse;
  isLoading: boolean;
  isReviewing: boolean;
  isCreating: boolean;
  deletingCardId: string | null;
  errorMessage: string | null;
  createFlashcard: (payload: CreateFlashcardRequest) => Promise<boolean>;
  deleteFlashcard: (id: string) => Promise<boolean>;
  answer: (knew: boolean) => Promise<void>;
  reload: () => Promise<void>;
};

const DEFAULT_STATS: FlashcardStatsResponse = {
  reviewedCount: 0,
  correctCount: 0,
  wrongCount: 0,
  overallProgress: 0,
};

export function useReviewSession(): ReviewSessionState {
  const recordFlashcardResult = useAppStore((state) => state.recordFlashcardResult);
  const [cards, setCards] = useState<FlashcardListItem[]>([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [stats, setStats] = useState<FlashcardStatsResponse>(DEFAULT_STATS);
  const [isLoading, setIsLoading] = useState(true);
  const [isReviewing, setIsReviewing] = useState(false);
  const [isCreating, setIsCreating] = useState(false);
  const [deletingCardId, setDeletingCardId] = useState<string | null>(null);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  async function reload(options?: { keepLoading?: boolean }) {
    if (options?.keepLoading !== false) {
      setIsLoading(true);
    }

    const [response, reviewStats] = await Promise.all([
      reviewApi.listFlashcards(),
      reviewApi.getStats(),
    ]);

    setCards(response);
    setStats(reviewStats);
    setCurrentIndex((value) => {
      if (response.length === 0) {
        return 0;
      }

      return value % response.length;
    });
    setErrorMessage(null);

    if (options?.keepLoading !== false) {
      setIsLoading(false);
    }
  }

  useEffect(() => {
    let cancelled = false;

    Promise.all([reviewApi.listFlashcards(), reviewApi.getStats()])
      .then(([response, reviewStats]) => {
        if (cancelled) {
          return;
        }

        setCards(response);
        setStats(reviewStats);
        setCurrentIndex(response.length === 0 ? 0 : 0);
        setErrorMessage(null);
        setIsLoading(false);
      })
      .catch((error) => {
        if (cancelled) {
          return;
        }

        setErrorMessage(error instanceof Error ? error.message : "Nao foi possivel carregar os flashcards.");
        setIsLoading(false);
      });

    return () => {
      cancelled = true;
    };
  }, []);

  const currentCard = useMemo(() => {
    if (cards.length === 0) {
      return null;
    }

    return cards[currentIndex % cards.length];
  }, [cards, currentIndex]);

  async function createFlashcard(payload: CreateFlashcardRequest) {
    setIsCreating(true);
    setErrorMessage(null);

    try {
      await reviewApi.createFlashcard(payload);
      await reload({ keepLoading: false });
      setCurrentIndex(0);
      return true;
    } catch (error) {
      setErrorMessage(error instanceof Error ? error.message : "Nao foi possivel criar o flashcard.");
      return false;
    } finally {
      setIsCreating(false);
    }
  }

  async function deleteFlashcard(id: string) {
    setDeletingCardId(id);
    setErrorMessage(null);

    try {
      await reviewApi.deleteFlashcard(id);
      setCards((previous) => {
        const nextCards = previous.filter((card) => card.id !== id);
        setCurrentIndex((value) => {
          if (nextCards.length === 0) {
            return 0;
          }

          return Math.min(value, nextCards.length - 1);
        });
        return nextCards;
      });
      const latestStats = await reviewApi.getStats();
      setStats(latestStats);
      return true;
    } catch (error) {
      setErrorMessage(error instanceof Error ? error.message : "Nao foi possivel excluir o flashcard.");
      return false;
    } finally {
      setDeletingCardId(null);
    }
  }

  async function answer(knew: boolean) {
    if (!currentCard) {
      return;
    }

    setIsReviewing(true);
    setErrorMessage(null);

    try {
      await reviewApi.reviewFlashcard(currentCard.id, {
        rating: knew ? "GOOD" : "AGAIN",
      });
      await reload({ keepLoading: false });
      recordFlashcardResult(knew);
      setCurrentIndex((value) => value + 1);
    } catch (error) {
      setErrorMessage(error instanceof Error ? error.message : "Nao foi possivel registrar a revisao.");
    } finally {
      setIsReviewing(false);
    }
  }

  return {
    cards,
    currentCard,
    currentIndex,
    stats,
    isLoading,
    isReviewing,
    isCreating,
    deletingCardId,
    errorMessage,
    createFlashcard,
    deleteFlashcard,
    answer,
    reload,
  };
}

export const flashcardLanguageOptions: Array<{ value: FlashcardLanguage; label: string }> = [
  { value: "ENGLISH", label: "Ingles" },
  { value: "SPANISH", label: "Espanhol" },
  { value: "FRENCH", label: "Frances" },
];
