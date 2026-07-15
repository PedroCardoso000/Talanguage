"use client";

import { FormEvent } from "react";

import { PageShell } from "@/components/page-shell";
import { FeedbackPanel } from "@/components/shared/feedback-panel";
import { Button } from "@/components/ui";
import { useSpeakingPractice } from "@/features/speaking/hooks/use-speaking-practice";

export default function SpeakPage() {
  const {
    currentScenario,
    conversation,
    answer,
    summary,
    latestSession,
    isLoadingTopics,
    isStarting,
    isSubmitting,
    isFinishing,
    canSend,
    canFinish,
    errorMessage,
    successMessage,
    practicedMinutes,
    dailyTarget,
    missingMinutes,
    progressPercent,
    updateAnswer,
    nextScenario,
    sendMessage,
    finishSession,
  } = useSpeakingPractice();

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    await sendMessage();
  }

  return (
    <PageShell
      title="Modulo Falar"
      description="Pratique conversacao em modo texto. O objetivo nao e simular voz; e criar repeticao guiada com feedback honesto."
      action={
        <Button
          onClick={nextScenario}
          disabled={isLoadingTopics || isStarting}
          variant="secondary"
        >
          Trocar cenario
        </Button>
      }
    >
      <div className="grid gap-6 xl:grid-cols-[1.65fr_0.95fr]">
        <div className="space-y-6 rounded-[2rem] border border-white/10 bg-white/5 p-6">
          {currentScenario ? (
            <>
              <div className="flex flex-wrap items-center gap-3">
                <span className="rounded-full border border-cyan-400/20 bg-cyan-400/10 px-4 py-2 text-sm text-cyan-200">
                  {currentScenario.category}
                </span>
                <span className="rounded-full border border-white/10 bg-white/5 px-4 py-2 text-sm text-slate-300">
                  {currentScenario.level}
                </span>
              </div>
              <div>
                <h2 className="text-3xl font-semibold text-white">{currentScenario.title}</h2>
                <p className="mt-4 max-w-3xl text-base leading-7 text-slate-300">
                  {currentScenario.objective}
                </p>
              </div>
              <div className="rounded-[1.75rem] border border-cyan-400/20 bg-cyan-400/8 p-5">
                <p className="text-sm uppercase tracking-[0.2em] text-cyan-200/70">Mentor</p>
                <p className="mt-3 text-lg leading-8 text-white">{currentScenario.mentorMessage}</p>
              </div>
            </>
          ) : (
            <div className="rounded-[1.75rem] border border-white/10 bg-white/5 p-5">
              <p className="text-sm leading-7 text-slate-400">
                {isLoadingTopics ? "Carregando cenarios..." : "Nenhum cenario disponivel no momento."}
              </p>
            </div>
          )}

          <div className="space-y-3 rounded-[1.75rem] border border-white/10 bg-[#0c1730] p-5">
            <p className="text-sm uppercase tracking-[0.2em] text-cyan-200/70">Conversa</p>
            {conversation.length === 0 ? (
              <p className="text-sm leading-7 text-slate-400">
                {isStarting ? "Iniciando sessao..." : "Assim que a sessao iniciar, as mensagens aparecem aqui."}
              </p>
            ) : (
              conversation.map((message, index) => (
                <div
                  key={`${message.role}-${index}`}
                  className={`rounded-[1.25rem] border p-4 text-sm leading-7 ${
                    message.role === "mentor"
                      ? "border-cyan-400/20 bg-cyan-400/8 text-slate-100"
                      : "border-white/10 bg-white/5 text-slate-300"
                  }`}
                >
                  <p className="mb-2 text-xs uppercase tracking-[0.18em] text-cyan-200/70">
                    {message.role === "mentor" ? "Mentor" : "Sua resposta"}
                  </p>
                  <p>{message.content}</p>
                </div>
              ))
            )}
          </div>

          <form onSubmit={handleSubmit} className="space-y-4">
            <textarea
              value={answer}
              onChange={(event) => updateAnswer(event.target.value)}
              disabled={isLoadingTopics || isStarting || isSubmitting || isFinishing || !currentScenario}
              placeholder="Digite como voce responderia nessa conversa..."
              className="min-h-40 w-full rounded-[1.75rem] border border-white/10 bg-[#0c1730] p-5 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/35 disabled:cursor-not-allowed disabled:opacity-60"
            />
            <div className="flex flex-col gap-3 sm:flex-row">
              <Button disabled={!canSend} loading={isSubmitting} type="submit" className="flex-1">
                {isSubmitting ? "Enviando..." : "Enviar mensagem"}
              </Button>
              <Button
                disabled={!canFinish}
                loading={isFinishing}
                variant="ghost"
                className="flex-1"
                onClick={() => void finishSession()}
              >
                {isFinishing ? "Finalizando..." : "Finalizar sessao"}
              </Button>
            </div>
            {errorMessage ? <p className="text-sm text-rose-300">{errorMessage}</p> : null}
            {successMessage ? <p className="text-sm text-cyan-200">{successMessage}</p> : null}
          </form>
        </div>

        <div className="space-y-6">
          <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
            <h3 className="text-xl font-semibold text-white">Resumo da sessao</h3>
            {summary ? (
              <div className="mt-5 space-y-4">
                <FeedbackPanel title="Sessao concluida">
                  <p>Tema praticado: {summary.topicTitle}</p>
                  <p>Total de mensagens: {summary.totalMessages}</p>
                  <p>Tempo aproximado: {summary.approximateDurationMinutes} min</p>
                </FeedbackPanel>
                <FeedbackPanel title="Leitura da pratica">{summary.feedback}</FeedbackPanel>
                <FeedbackPanel title="Proxima pratica" variant="accent">
                  {summary.nextAction}
                </FeedbackPanel>
                {latestSession ? (
                  <FeedbackPanel title="Ultima sessao salva">
                    {latestSession.topicTitle} • {latestSession.totalMessages} mensagens •{" "}
                    {latestSession.approximateDurationMinutes} min
                  </FeedbackPanel>
                ) : null}
              </div>
            ) : (
              <p className="mt-4 text-sm leading-7 text-slate-400">
                Envie mensagens e finalize a sessao para receber um resumo simples e persistido.
              </p>
            )}
          </div>

          <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
            <h3 className="text-xl font-semibold text-white">Meta diaria de fala</h3>
            <p className="mt-4 text-4xl font-semibold text-white">
              {practicedMinutes} / {dailyTarget} min
            </p>
            <div className="mt-5 h-3 rounded-full bg-white/5">
              <div
                className="h-full rounded-full bg-[linear-gradient(90deg,#5FFBF1,#35A7FF)]"
                style={{ width: `${progressPercent}%` }}
              />
            </div>
            <p className="mt-3 text-sm text-slate-300">
              {missingMinutes > 0
                ? `Faltam ${missingMinutes} minutos para fechar o bloco de hoje.`
                : "Meta diaria concluida."}
            </p>
          </div>
        </div>
      </div>
    </PageShell>
  );
}
