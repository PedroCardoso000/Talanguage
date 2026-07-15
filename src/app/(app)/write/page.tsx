"use client";

import { FormEvent } from "react";

import { PageShell } from "@/components/page-shell";
import { FeedbackPanel } from "@/components/shared/feedback-panel";
import { Button } from "@/components/ui";
import { useWritingReview } from "@/features/writing/hooks/use-writing-review";

export default function WritePage() {
  const {
    currentChallenge,
    text,
    feedback,
    isLoading,
    isSubmitting,
    isEmpty,
    canSubmit,
    errorMessage,
    successMessage,
    lastSubmission,
    updateText,
    nextChallenge,
    submit,
  } = useWritingReview();

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    await submit();
  }

  return (
    <PageShell
      title="Modulo Escrever"
      description="Escreva com foco em clareza e intencao. Texto ruim melhorado vale mais que texto perfeito que nunca saiu."
      action={
        <Button
          onClick={nextChallenge}
          disabled={isLoading || isEmpty}
          variant="secondary"
        >
          Novo desafio
        </Button>
      }
    >
      <div className="grid gap-6 xl:grid-cols-[1.45fr_1fr]">
        <div className="space-y-6 rounded-[2rem] border border-white/10 bg-white/5 p-6">
          <div className="rounded-[1.75rem] border border-white/10 bg-white/5 p-5">
            <p className="text-sm uppercase tracking-[0.2em] text-cyan-200/70">Desafio atual</p>
            {isLoading ? (
              <p className="mt-4 text-sm leading-7 text-slate-400">Carregando desafio de escrita...</p>
            ) : currentChallenge ? (
              <>
                <h2 className="mt-3 text-3xl font-semibold text-white">{currentChallenge.title}</h2>
                <p className="mt-4 text-base leading-8 text-slate-300">{currentChallenge.prompt}</p>
                <p className="mt-4 text-sm text-cyan-200">Foco: {currentChallenge.focus}</p>
              </>
            ) : (
              <p className="mt-4 text-sm leading-7 text-slate-400">
                Nenhum desafio disponivel no momento.
              </p>
            )}
          </div>

          <form onSubmit={handleSubmit} className="space-y-4">
            <textarea
              value={text}
              onChange={(event) => updateText(event.target.value)}
              disabled={isLoading || isSubmitting || !currentChallenge}
              placeholder="Escreva sua resposta aqui..."
              className="min-h-72 w-full rounded-[1.75rem] border border-white/10 bg-[#0c1730] p-5 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/35 disabled:cursor-not-allowed disabled:opacity-60"
            />
            <Button
              disabled={!canSubmit}
              loading={isSubmitting}
              type="submit"
            >
              {isSubmitting ? "Enviando..." : "Enviar texto"}
            </Button>
            {errorMessage ? <p className="text-sm text-rose-300">{errorMessage}</p> : null}
            {successMessage ? <p className="text-sm text-cyan-200">{successMessage}</p> : null}
          </form>
        </div>

        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
          <h3 className="text-xl font-semibold text-white">Feedback da revisao</h3>
          {feedback ? (
            <div className="mt-5 space-y-4">
              <FeedbackPanel title="Pontos fortes">
                <ul className="space-y-2">
                  {feedback.strengths.map((item) => (
                    <li key={item}>{item}</li>
                  ))}
                </ul>
              </FeedbackPanel>
              <FeedbackPanel title="Pontos de melhoria" variant="accent">
                <ul className="space-y-2">
                  {feedback.improvements.map((item) => (
                    <li key={item}>{item}</li>
                  ))}
                </ul>
              </FeedbackPanel>
              <FeedbackPanel title="Proxima acao">{feedback.nextAction}</FeedbackPanel>
              {lastSubmission ? (
                <FeedbackPanel title="Ultima revisao">
                  Ultimo envio em {new Date(lastSubmission.createdAt).toLocaleDateString("pt-BR")}. A analise
                  anterior continua salva para comparacao.
                </FeedbackPanel>
              ) : null}
            </div>
          ) : isLoading ? (
            <p className="mt-4 text-sm leading-7 text-slate-400">Preparando o fluxo de revisao...</p>
          ) : isEmpty ? (
            <p className="mt-4 text-sm leading-7 text-slate-400">
              Nenhum desafio foi retornado pelo client atual.
            </p>
          ) : (
            <p className="mt-4 text-sm leading-7 text-slate-400">
              O feedback agora precisa ser simples e honesto: o que funcionou, o que precisa melhorar e qual e o proximo passo.
            </p>
          )}
        </div>
      </div>
    </PageShell>
  );
}
