"use client";

import { FormEvent } from "react";

import { PageShell } from "@/components/page-shell";
import { NumericGoalField } from "@/components/shared/numeric-goal-field";
import { useGoalsSettings } from "@/features/goals/hooks/use-goals-settings";

export default function GoalsPage() {
  const { form, isLoading, isSaving, errorMessage, successMessage, updateField, submit } =
    useGoalsSettings();

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    await submit();
  }

  if (isLoading) {
    return (
      <PageShell
        title="Metas"
        description="Sem meta clara, voce confunde atividade com avanco. Ajuste os numeros e pare de improvisar."
      >
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6 text-slate-300">
          Carregando configuracoes de metas...
        </div>
      </PageShell>
    );
  }

  return (
    <PageShell
      title="Metas"
      description="Sem meta clara, voce confunde atividade com avanco. Ajuste os numeros e pare de improvisar."
    >
      <form onSubmit={handleSubmit} className="grid gap-6 xl:grid-cols-[1.2fr_0.8fr]">
        <div className="grid gap-5 sm:grid-cols-2">
          <NumericGoalField
            label="Minutos por dia"
            value={form.dailyMinutes}
            onChange={(value) => updateField("dailyMinutes", value)}
          />
          <NumericGoalField
            label="Minutos por semana"
            value={form.weeklyMinutes}
            onChange={(value) => updateField("weeklyMinutes", value)}
          />
          <NumericGoalField
            label="Palavras revisadas por dia"
            value={form.wordsPerDay}
            onChange={(value) => updateField("wordsPerDay", value)}
          />
          <NumericGoalField
            label="Desafios por semana"
            value={form.challengesPerWeek}
            onChange={(value) => updateField("challengesPerWeek", value)}
          />
        </div>
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
          <h2 className="text-2xl font-semibold text-white">Resumo da meta</h2>
          <p className="mt-4 text-sm leading-7 text-slate-300">
            Sua meta atual exige {form.dailyMinutes} minutos por dia, {form.wordsPerDay} palavras revisadas e{" "}
            {form.challengesPerWeek} desafios por semana.
          </p>
          <button
            disabled={isSaving}
            className="mt-6 h-12 w-full rounded-2xl bg-[linear-gradient(135deg,#5FFBF1,#35A7FF)] font-semibold text-slate-950 disabled:cursor-not-allowed disabled:opacity-70"
          >
            {isSaving ? "Salvando..." : "Salvar metas"}
          </button>
          {errorMessage ? <p className="mt-4 text-sm text-rose-200">{errorMessage}</p> : null}
          {successMessage ? <p className="mt-4 text-sm text-cyan-200">{successMessage}</p> : null}
        </div>
      </form>
    </PageShell>
  );
}
