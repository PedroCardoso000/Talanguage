"use client";

import { FormEvent } from "react";

import { PageShell } from "@/components/page-shell";
import { useProfileSettings } from "@/features/profile/hooks/use-profile-settings";
import { LANGUAGE_OPTIONS, LEVEL_OPTIONS } from "@/lib/profile-options";

export default function ProfilePage() {
  const { form, isLoading, isSaving, errorMessage, successMessage, updateField, submit } = useProfileSettings();

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    await submit();
  }

  if (isLoading) {
    return (
      <PageShell
        title="Perfil"
        description="Se o seu perfil esta raso, seu treino tambem fica. Defina contexto suficiente para o sistema parar de adivinhar."
      >
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6 text-slate-300">
          Carregando informacoes do perfil...
        </div>
      </PageShell>
    );
  }

  return (
    <PageShell
      title="Perfil"
      description="Ajuste seus dados base. Sem isso, idioma, nivel e objetivo continuam desalinhados com o treino real."
    >
      <form onSubmit={handleSubmit} className="grid gap-6 xl:grid-cols-[1.2fr_0.8fr]">
        <div className="space-y-5 rounded-[2rem] border border-white/10 bg-white/5 p-6">
          <div className="grid gap-5 sm:grid-cols-2">
            <div className="space-y-2">
              <label className="text-sm text-slate-300">Nome</label>
              <input
                required
                value={form.name}
                onChange={(event) => updateField("name", event.target.value)}
                className="h-14 w-full rounded-2xl border border-white/10 bg-white/5 px-4 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/40"
                placeholder="Seu nome"
              />
            </div>
            <div className="space-y-2">
              <label className="text-sm text-slate-300">E-mail</label>
              <input
                readOnly
                value={form.email}
                className="h-14 w-full rounded-2xl border border-white/10 bg-white/5 px-4 text-slate-400 outline-none"
              />
            </div>
          </div>
          <div className="grid gap-5 sm:grid-cols-2">
            <div className="space-y-2">
              <label className="text-sm text-slate-300">Idioma alvo</label>
              <select
                value={form.targetLanguage}
                onChange={(event) => updateField("targetLanguage", event.target.value as typeof form.targetLanguage)}
                className="h-14 w-full rounded-2xl border border-white/10 bg-[#0e1830] px-4 text-white outline-none focus:border-cyan-400/40"
              >
                {LANGUAGE_OPTIONS.map((option) => (
                  <option key={option.value} value={option.label}>
                    {option.label}
                  </option>
                ))}
              </select>
            </div>
            <div className="space-y-2">
              <label className="text-sm text-slate-300">Nivel atual</label>
              <select
                value={form.currentLevel ?? ""}
                onChange={(event) =>
                  updateField("currentLevel", event.target.value ? (event.target.value as NonNullable<typeof form.currentLevel>) : null)
                }
                className="h-14 w-full rounded-2xl border border-white/10 bg-[#0e1830] px-4 text-white outline-none focus:border-cyan-400/40"
              >
                <option value="">Selecione</option>
                {LEVEL_OPTIONS.map((option) => (
                  <option key={option.value} value={option.label}>
                    {option.label}
                  </option>
                ))}
              </select>
            </div>
          </div>
          <div className="space-y-2">
            <label className="text-sm text-slate-300">Objetivo de estudo</label>
            <textarea
              value={form.studyGoal}
              onChange={(event) => updateField("studyGoal", event.target.value)}
              className="min-h-32 w-full rounded-2xl border border-white/10 bg-white/5 px-4 py-4 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/40"
              placeholder="Ex.: Quero conduzir reunioes em ingles sem depender de roteiro."
            />
          </div>
          <div className="space-y-2">
            <label className="text-sm text-slate-300">Avatar URL</label>
            <input
              value={form.avatarUrl}
              onChange={(event) => updateField("avatarUrl", event.target.value)}
              className="h-14 w-full rounded-2xl border border-white/10 bg-white/5 px-4 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/40"
              placeholder="https://..."
            />
          </div>
        </div>

        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
          <div className="flex items-center gap-4">
            <div className="flex h-16 w-16 items-center justify-center overflow-hidden rounded-3xl bg-[linear-gradient(135deg,#5FFBF1,#2F7CFF)] text-xl font-semibold text-slate-950">
              {form.avatarUrl ? (
                // eslint-disable-next-line @next/next/no-img-element
                <img src={form.avatarUrl} alt={form.name} className="h-full w-full object-cover" />
              ) : (
                form.name.slice(0, 1).toUpperCase()
              )}
            </div>
            <div>
              <p className="text-lg font-semibold text-white">{form.name || "Seu nome"}</p>
              <p className="text-sm text-slate-400">{form.targetLanguage}</p>
            </div>
          </div>
          <div className="mt-6 space-y-3 text-sm leading-7 text-slate-300">
            <p>Nivel atual: {form.currentLevel ?? "Nao definido"}</p>
            <p>Objetivo: {form.studyGoal.trim() || "Ainda nao definido."}</p>
          </div>
          <button
            disabled={isSaving}
            className="mt-6 h-12 w-full rounded-2xl bg-[linear-gradient(135deg,#5FFBF1,#35A7FF)] font-semibold text-slate-950 disabled:cursor-not-allowed disabled:opacity-70"
          >
            {isSaving ? "Salvando..." : "Salvar perfil"}
          </button>
          {errorMessage ? <p className="mt-4 text-sm text-rose-200">{errorMessage}</p> : null}
          {successMessage ? <p className="mt-4 text-sm text-cyan-200">{successMessage}</p> : null}
        </div>
      </form>
    </PageShell>
  );
}
