"use client";

import { useMemo, useState } from "react";
import { Trash2 } from "lucide-react";

import { MetricValueCard } from "@/components/shared/metric-value-card";
import { PageShell } from "@/components/page-shell";
import { Button, Input, Select } from "@/components/ui";
import {
  flashcardLanguageOptions,
  useReviewSession,
} from "@/features/review/hooks/use-review-session";
import type { FlashcardLanguage } from "@/features/review/contracts";

type CreateFlashcardFormState = {
  front: string;
  back: string;
  language: FlashcardLanguage;
  tags: string;
};

const INITIAL_FORM: CreateFlashcardFormState = {
  front: "",
  back: "",
  language: "ENGLISH",
  tags: "",
};

export default function ReviewPage() {
  const {
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
  } = useReviewSession();

  const [isAnswerVisible, setIsAnswerVisible] = useState(false);
  const [form, setForm] = useState<CreateFlashcardFormState>(INITIAL_FORM);
  const canCreate = form.front.trim().length > 0 && form.back.trim().length > 0;

  const selectedCardPosition = useMemo(() => {
    if (!currentCard || cards.length === 0) {
      return 0;
    }

    return currentIndex % cards.length;
  }, [cards.length, currentCard, currentIndex]);

  async function handleCreateFlashcard() {
    const created = await createFlashcard({
      front: form.front,
      back: form.back,
      language: form.language,
      tags: form.tags
        .split(",")
        .map((tag) => tag.trim())
        .filter(Boolean),
    });
    if (created) {
      setForm(INITIAL_FORM);
    }
  }

  async function handleDeleteFlashcard(id: string) {
    const deleted = await deleteFlashcard(id);
    if (deleted) {
      setIsAnswerVisible(false);
    }
  }

  async function handleAnswer(knew: boolean) {
    await answer(knew);
    setIsAnswerVisible(false);
  }

  return (
    <PageShell
      title="Modulo Revisar"
      description="Revisao e onde o aprendizado para de parecer progresso e comeca a virar retencao."
    >
      <div className="grid gap-6 xl:grid-cols-[1.45fr_1fr]">
        <div className="space-y-6">
          <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
            <div className="flex items-center justify-between gap-3">
              <p className="text-sm uppercase tracking-[0.2em] text-cyan-200/70">Fila de revisao</p>
              <span className="rounded-full border border-white/10 bg-white/5 px-3 py-1 text-sm text-slate-300">
                {currentCard ? `${selectedCardPosition + 1} / ${cards.length}` : `${cards.length} cards`}
              </span>
            </div>

            {isLoading ? (
              <div className="mt-6 rounded-[2rem] border border-white/10 bg-white/5 p-6 text-slate-300">
                Carregando flashcards...
              </div>
            ) : currentCard ? (
              <>
                <div className="mt-6 rounded-[2rem] border border-cyan-400/20 bg-[linear-gradient(180deg,rgba(15,31,59,0.95),rgba(9,16,31,0.92))] p-8">
                  <div className="flex items-start justify-between gap-4">
                    <div>
                      <p className="text-sm uppercase tracking-[0.2em] text-cyan-200/70">
                        {(currentCard.tags?.[0] ?? "revisao").toUpperCase()}
                      </p>
                      <h2 className="mt-4 text-4xl font-semibold text-white">{currentCard.front}</h2>
                    </div>
                    <Button
                      size="icon"
                      variant="danger"
                      loading={deletingCardId === currentCard.id}
                      onClick={() => void handleDeleteFlashcard(currentCard.id)}
                      aria-label="Excluir flashcard atual"
                    >
                      {deletingCardId === currentCard.id ? null : <Trash2 className="h-4 w-4" />}
                    </Button>
                  </div>

                  {isAnswerVisible ? (
                    <p className="mt-5 text-lg leading-8 text-slate-200">{currentCard.back}</p>
                  ) : (
                    <p className="mt-5 rounded-[1.5rem] border border-dashed border-white/15 bg-white/5 p-4 text-sm leading-7 text-slate-300">
                      Revele a resposta antes de marcar acerto ou erro. Sem isso, revisao vira teatro.
                    </p>
                  )}

                  <p className="mt-4 rounded-[1.5rem] border border-white/10 bg-white/5 p-4 text-sm leading-7 text-slate-300">
                    Proxima revisao prevista:{" "}
                    {currentCard.nextReviewAt
                      ? new Date(currentCard.nextReviewAt).toLocaleDateString("pt-BR")
                      : "a definir"}
                  </p>
                </div>

                {!isAnswerVisible ? (
                  <Button className="mt-6 w-full sm:w-auto" onClick={() => setIsAnswerVisible(true)}>
                    Mostrar resposta
                  </Button>
                ) : (
                  <div className="mt-6 flex flex-col gap-3 sm:flex-row">
                    <Button
                      loading={isReviewing}
                      onClick={() => void handleAnswer(true)}
                      className="flex-1"
                    >
                      Eu sabia
                    </Button>
                    <Button
                      loading={isReviewing}
                      onClick={() => void handleAnswer(false)}
                      className="flex-1"
                      variant="danger"
                    >
                      Errei
                    </Button>
                  </div>
                )}
              </>
            ) : (
              <div className="mt-6 rounded-[2rem] border border-dashed border-white/15 bg-white/5 p-6 text-slate-300">
                Nenhum flashcard disponivel. Crie o primeiro agora para sair do zero.
              </div>
            )}

            {errorMessage ? <p className="mt-4 text-sm text-rose-200">{errorMessage}</p> : null}
          </div>

          <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
            <div className="flex items-center justify-between gap-3">
              <div>
                <p className="text-sm uppercase tracking-[0.2em] text-cyan-200/70">Seus flashcards</p>
                <h3 className="mt-2 text-xl font-semibold text-white">Lista real, sem fixture escondida</h3>
              </div>
              <span className="rounded-full border border-white/10 bg-white/5 px-3 py-1 text-sm text-slate-300">
                {cards.length}
              </span>
            </div>

            <div className="mt-5 space-y-3">
              {cards.length === 0 ? (
                <div className="rounded-[1.5rem] border border-dashed border-white/15 bg-white/5 p-4 text-sm text-slate-300">
                  Sua fila ainda esta vazia.
                </div>
              ) : (
                cards.map((card, index) => (
                  <div
                    key={card.id}
                    className={`rounded-[1.5rem] border p-4 ${
                      card.id === currentCard?.id
                        ? "border-cyan-400/35 bg-cyan-400/10"
                        : "border-white/10 bg-white/5"
                    }`}
                  >
                    <div className="flex items-start justify-between gap-4">
                      <div>
                        <p className="text-sm text-white">{card.front}</p>
                        <p className="mt-1 text-sm text-slate-400">
                          {card.tags?.[0] ?? "sem tag"} {" - "}
                          {card.nextReviewAt
                            ? new Date(card.nextReviewAt).toLocaleDateString("pt-BR")
                            : "sem data"}
                        </p>
                        <p className="mt-2 text-xs uppercase tracking-[0.2em] text-slate-500">
                          Card {index + 1}
                        </p>
                      </div>
                      <Button
                        size="icon"
                        variant="ghost"
                        loading={deletingCardId === card.id}
                        onClick={() => void handleDeleteFlashcard(card.id)}
                        aria-label={`Excluir ${card.front}`}
                      >
                        {deletingCardId === card.id ? null : <Trash2 className="h-4 w-4" />}
                      </Button>
                    </div>
                  </div>
                ))
              )}
            </div>
          </div>
        </div>

        <div className="space-y-5">
          <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
            <p className="text-sm uppercase tracking-[0.2em] text-cyan-200/70">Novo flashcard</p>
            <h3 className="mt-2 text-xl font-semibold text-white">Adicionar palavra ou expressao</h3>
            <div className="mt-5 space-y-4">
              <Input
                label="Frente"
                placeholder="Ex.: Follow up"
                value={form.front}
                onChange={(event) => setForm((current) => ({ ...current, front: event.target.value }))}
              />
              <Input
                label="Verso"
                placeholder="Ex.: Dar continuidade"
                value={form.back}
                onChange={(event) => setForm((current) => ({ ...current, back: event.target.value }))}
              />
              <Select
                label="Idioma"
                value={form.language}
                options={flashcardLanguageOptions}
                onChange={(event) =>
                  setForm((current) => ({ ...current, language: event.target.value as FlashcardLanguage }))
                }
              />
              <Input
                label="Tags"
                placeholder="greetings, trabalho, escrita"
                helperText="Separe por virgula. Isso ajuda a organizar sem complicar."
                value={form.tags}
                onChange={(event) => setForm((current) => ({ ...current, tags: event.target.value }))}
              />
              <Button
                loading={isCreating}
                disabled={!canCreate}
                className="w-full"
                onClick={() => void handleCreateFlashcard()}
              >
                Criar flashcard
              </Button>
            </div>
          </div>

          <MetricValueCard label="Palavras revisadas" value={`${stats.reviewedCount}`} className="rounded-[1.75rem] p-5" />
          <MetricValueCard label="Acertos" value={`${stats.correctCount}`} className="rounded-[1.75rem] p-5" />
          <MetricValueCard label="Erros" value={`${stats.wrongCount}`} className="rounded-[1.75rem] p-5" />
          <MetricValueCard label="Taxa de acerto" value={`${stats.overallProgress}%`} className="rounded-[1.75rem] p-5" />
        </div>
      </div>
    </PageShell>
  );
}
