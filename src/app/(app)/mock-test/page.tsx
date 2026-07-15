"use client";

import { PageShell } from "@/components/page-shell";
import { Button } from "@/components/ui";
import { cn } from "@/lib/utils";
import { useMockTestSession } from "@/features/mock-test/hooks/use-mock-test-session";

export default function MockTestPage() {
  const {
    currentTest,
    questions,
    answers,
    result,
    isLoading,
    isSubmitting,
    errorMessage,
    canSubmit,
    selectAnswer,
    submit,
  } = useMockTestSession();

  if (isLoading) {
    return (
      <PageShell
        title="Simulado"
        description="Diagnostico rapido com correcao real no backend. Sem placar inventado no browser."
      >
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6 text-slate-300">
          Carregando perguntas do simulado...
        </div>
      </PageShell>
    );
  }

  if (errorMessage && questions.length === 0) {
    return (
      <PageShell
        title="Simulado"
        description="Diagnostico rapido com correcao real no backend. Sem placar inventado no browser."
      >
        <div className="rounded-[2rem] border border-rose-400/20 bg-rose-400/8 p-6 text-rose-100">
          {errorMessage}
        </div>
      </PageShell>
    );
  }

  if (questions.length === 0) {
    return (
      <PageShell
        title="Simulado"
        description="Diagnostico rapido com correcao real no backend. Sem placar inventado no browser."
      >
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6 text-slate-300">
          Nenhuma pergunta disponivel no momento.
        </div>
      </PageShell>
    );
  }

  return (
    <PageShell
      title="Simulado"
      description="Diagnostico rapido com correcao real no backend. Sem placar inventado no browser."
      action={
        <Button
          onClick={() => void submit()}
          disabled={!canSubmit || isSubmitting || !!result}
          loading={isSubmitting}
        >
          {isSubmitting ? "Corrigindo..." : "Finalizar simulado"}
        </Button>
      }
    >
      {currentTest ? (
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-5 text-slate-300">
          <p className="text-sm uppercase tracking-[0.18em] text-cyan-200/70">Prova atual</p>
          <h2 className="mt-2 text-2xl font-semibold text-white">{currentTest.title}</h2>
          <p className="mt-2 text-sm text-slate-400">
            Responda todas as {questions.length} perguntas antes de finalizar.
          </p>
        </div>
      ) : null}

      <div className="space-y-5">
        {questions.map((question, index) => {
          const questionResult = result?.questions.find((item) => item.questionId === question.id);

          return (
            <div key={question.id} className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
              <p className="text-sm uppercase tracking-[0.18em] text-cyan-200/70">Pergunta {index + 1}</p>
              <h2 className="mt-3 text-2xl font-semibold text-white">{question.question}</h2>
              <div className="mt-5 grid gap-3">
                {question.options.map((option) => {
                  const isSelected = answers[question.id] === option;
                  const isCorrectOption = questionResult?.correctOption === option;
                  const isWrongSelection = Boolean(questionResult && isSelected && !questionResult.isCorrect);

                  return (
                    <button
                      key={option}
                      onClick={() => selectAnswer(question.id, option)}
                      disabled={Boolean(result)}
                      className={cn(
                        "rounded-[1.5rem] border border-white/10 bg-white/5 px-4 py-4 text-left text-sm leading-7 text-slate-300 transition hover:border-cyan-400/25 hover:bg-cyan-400/8 disabled:cursor-default disabled:hover:border-white/10 disabled:hover:bg-white/5",
                        isSelected && !result && "border-cyan-400/40 bg-cyan-400/10 text-white",
                        isCorrectOption && result && "border-emerald-400/35 bg-emerald-400/10 text-emerald-100",
                        isWrongSelection && "border-rose-400/35 bg-rose-400/10 text-rose-100",
                      )}
                    >
                      {option}
                    </button>
                  );
                })}
              </div>
              {questionResult ? (
                <p className="mt-4 text-sm leading-7 text-slate-300">
                  <span className="font-semibold text-white">Explicacao:</span> {questionResult.explanation}
                </p>
              ) : null}
            </div>
          );
        })}
      </div>

      {errorMessage ? (
        <div className="rounded-[2rem] border border-rose-400/20 bg-rose-400/8 p-6 text-rose-100">
          {errorMessage}
        </div>
      ) : null}

      {result ? (
        <div className="rounded-[2rem] border border-cyan-400/20 bg-cyan-400/8 p-6">
          <p className="text-sm uppercase tracking-[0.18em] text-cyan-200/70">Resultado final</p>
          <h2 className="mt-3 text-4xl font-semibold text-white">
            {result.score} / {result.totalQuestions}
          </h2>
          <p className="mt-4 text-base leading-8 text-slate-200">{result.recommendation}</p>
          <p className="mt-3 text-sm text-slate-400">
            Tentativa registrada em {new Date(result.completedAt).toLocaleString("pt-BR")}.
          </p>
        </div>
      ) : null}
    </PageShell>
  );
}
