"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";
import { FormEvent, useState } from "react";

import { ApiClientError } from "@/api/http-client";
import { AuthShell } from "@/components/auth-shell";
import { Input } from "@/components/ui";
import { registerUser } from "@/features/auth/actions/auth-actions";
import { LANGUAGE_OPTIONS } from "@/lib/profile-options";
import { useAppStore } from "@/store/use-app-store";
import { LanguageOption } from "@/types";

export default function RegisterPage() {
  const router = useRouter();
  const setUser = useAppStore((state) => state.setUser);
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    targetLanguage: "Inglês" as LanguageOption,
  });
  const [submitting, setSubmitting] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const [errorDetails, setErrorDetails] = useState<string[]>([]);

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setSubmitting(true);
    setErrorMessage(null);
    setErrorDetails([]);

    try {
      const user = await registerUser(form);
      setUser(user);
      router.push("/dashboard");
    } catch (error) {
      if (error instanceof ApiClientError) {
        setErrorMessage(error.message);
        setErrorDetails(error.details?.map((detail) => detail.message) ?? []);
      } else {
        setErrorMessage("Nao foi possivel criar a conta agora.");
      }
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <AuthShell title="Criar conta" description="Comece sua rotina com metas claras e treino consistente.">
      <form className="space-y-5" onSubmit={handleSubmit}>
        <div className="space-y-2">
          <label className="text-sm text-slate-300">Nome</label>
          <input
            required
            value={form.name}
            onChange={(event) => setForm((current) => ({ ...current, name: event.target.value }))}
            className="h-14 w-full rounded-2xl border border-white/10 bg-white/5 px-4 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/40"
            placeholder="Seu nome"
          />
        </div>
        <div className="grid gap-5 sm:grid-cols-2">
          <div className="space-y-2">
            <label className="text-sm text-slate-300">E-mail</label>
            <input
              required
              type="email"
              value={form.email}
              onChange={(event) => setForm((current) => ({ ...current, email: event.target.value }))}
              className="h-14 w-full rounded-2xl border border-white/10 bg-white/5 px-4 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/40"
              placeholder="voce@email.com"
            />
          </div>
          <div className="space-y-2">
            <label className="text-sm text-slate-300">Idioma</label>
            <select
              value={form.targetLanguage}
              onChange={(event) =>
                setForm((current) => ({
                  ...current,
                  targetLanguage: event.target.value as LanguageOption,
                }))
              }
              className="h-14 w-full rounded-2xl border border-white/10 bg-[#0e1830] px-4 text-white outline-none focus:border-cyan-400/40"
            >
              {LANGUAGE_OPTIONS.map((option) => (
                <option key={option.value} value={option.label}>
                  {option.label}
                </option>
              ))}
            </select>
          </div>
        </div>
        <Input
          label="Senha"
          required
          type="password"
          value={form.password}
          onChange={(event) => setForm((current) => ({ ...current, password: event.target.value }))}
          className="h-14 rounded-2xl border border-white/10 bg-white/5 px-4 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/40"
          placeholder="Crie uma senha"
        />
        {errorMessage ? (
          <div className="rounded-2xl border border-rose-400/20 bg-rose-400/10 px-4 py-3 text-sm text-rose-100">
            <p>{errorMessage}</p>
            {errorDetails.length > 0 ? (
              <ul className="mt-2 space-y-1 text-rose-50/90">
                {errorDetails.map((detail) => (
                  <li key={detail}>{detail}</li>
                ))}
              </ul>
            ) : null}
          </div>
        ) : null}
        <button
          disabled={submitting}
          className="h-14 w-full rounded-2xl bg-[linear-gradient(135deg,#5FFBF1,#35A7FF)] text-lg font-semibold text-slate-950 disabled:cursor-not-allowed disabled:opacity-70"
        >
          {submitting ? "Criando conta..." : "Criar conta"}
        </button>
      </form>
      <div className="mt-6 flex items-center justify-between text-sm text-slate-400">
        <span>Ja tem conta?</span>
        <Link href="/login" className="font-medium text-cyan-300">
          Fazer login
        </Link>
      </div>
    </AuthShell>
  );
}
