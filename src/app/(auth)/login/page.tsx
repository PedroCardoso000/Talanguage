"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";
import { FormEvent, useState } from "react";

import { ApiClientError } from "@/api/http-client";
import { AuthShell } from "@/components/auth-shell";
import { Input } from "@/components/ui";
import { loginUser } from "@/features/auth/actions/auth-actions";
import { useAppStore } from "@/store/use-app-store";

export default function LoginPage() {
  const router = useRouter();
  const setUser = useAppStore((state) => state.setUser);
  const [form, setForm] = useState({ email: "", password: "" });
  const [submitting, setSubmitting] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const [errorDetails, setErrorDetails] = useState<string[]>([]);

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setSubmitting(true);
    setErrorMessage(null);
    setErrorDetails([]);

    try {
      const user = await loginUser(form);
      setUser(user);
      router.push("/dashboard");
    } catch (error) {
      if (error instanceof ApiClientError) {
        setErrorMessage(error.message);
        setErrorDetails(error.details?.map((detail) => detail.message) ?? []);
      } else {
        setErrorMessage("Nao foi possivel entrar agora.");
      }
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <AuthShell title="Entrar" description="Acesse sua conta para continuar seu treino.">
      <form className="space-y-5" onSubmit={handleSubmit}>
        <div className="space-y-2">
          <label className="text-sm text-slate-300">E-mail</label>
          <input
            required
            type="email"
            value={form.email}
            onChange={(event) => setForm((current) => ({ ...current, email: event.target.value }))}
            className="h-14 w-full rounded-2xl border border-white/10 bg-white/5 px-4 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/40"
            placeholder="seu@email.com"
          />
        </div>
        <Input
          label="Senha"
          required
          type="password"
          value={form.password}
          onChange={(event) => setForm((current) => ({ ...current, password: event.target.value }))}
          className="h-14 rounded-2xl border border-white/10 bg-white/5 px-4 text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/40"
          placeholder="********"
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
          {submitting ? "Entrando..." : "Entrar"}
        </button>
      </form>
      <div className="mt-6 flex items-center justify-between text-sm text-slate-400">
        <span>Sem conta ainda?</span>
        <Link href="/register" className="font-medium text-cyan-300">
          Criar conta
        </Link>
      </div>
    </AuthShell>
  );
}
