"use client";

import Link from "next/link";

import { ModuleCard } from "@/components/module-card";
import { PageShell } from "@/components/page-shell";
import { ProgressChart } from "@/components/progress-chart";
import { StatCard } from "@/components/stat-card";
import { useDashboardReadModel } from "@/features/dashboard/hooks/use-dashboard-read-model";
import { dashboardModules } from "@/features/dashboard/mocks/modules";

export default function DashboardPage() {
  const { data, isLoading, errorMessage } = useDashboardReadModel();

  if (isLoading) {
    return (
      <PageShell
        title="Carregando dashboard"
        description="Organizando sua rotina e seu progresso para a proxima sessao."
      >
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6 text-slate-300">
          Carregando resumo da rotina...
        </div>
      </PageShell>
    );
  }

  if (errorMessage || !data) {
    return (
      <PageShell title="Dashboard" description="O resumo da sua rotina nao foi carregado desta vez.">
        <div className="rounded-[2rem] border border-rose-400/20 bg-rose-400/10 p-6 text-rose-100">
          {errorMessage ?? "Nao foi possivel carregar o dashboard agora."}
        </div>
      </PageShell>
    );
  }

  const isEmptyState = data.weeklyActivityCount <= 0;

  return (
    <PageShell
      title={`Bom dia, ${data.userName}.`}
      description={`Seu idioma foco e ${data.targetLanguage}. A consistencia esta melhorando, mas ainda depende de repetir o basico com intencao todos os dias.`}
      action={
        <Link
          href={data.nextSuggestedAction.route}
          className="inline-flex h-12 items-center justify-center rounded-2xl bg-[linear-gradient(135deg,#5FFBF1,#35A7FF)] px-6 font-semibold text-slate-950"
        >
          Continuar treino
        </Link>
      }
    >
      {isEmptyState ? (
        <div className="rounded-[2rem] border border-cyan-400/15 bg-[linear-gradient(135deg,rgba(95,251,241,0.12),rgba(255,255,255,0.04))] p-6">
          <p className="text-sm uppercase tracking-[0.2em] text-cyan-100/80">Estado inicial</p>
          <h2 className="mt-4 text-3xl font-semibold text-white">Voce ainda nao tem atividade suficiente para fingir progresso.</h2>
          <p className="mt-3 max-w-2xl text-slate-200">
            O dashboard agora parou de inventar metricas. Termine a primeira pratica real para ver sequencia, distribuicao semanal e progresso por modulo.
          </p>
        </div>
      ) : null}

      <div className="grid gap-6 xl:grid-cols-[1.7fr_1fr]">
        <div className="rounded-[2rem] border border-white/10 bg-[linear-gradient(135deg,rgba(95,251,241,0.18),rgba(255,255,255,0.06))] p-6">
          <p className="text-sm uppercase tracking-[0.2em] text-cyan-100/80">Treino recomendado</p>
          <h2 className="mt-4 text-3xl font-semibold text-white">{data.nextSuggestedAction.label}</h2>
          <p className="mt-3 max-w-2xl text-slate-200">
            Seu gargalo nao e falta de conteudo. E repeticao insuficiente em contextos reais. Ataque o proximo bloco com execucao, nao com desculpa elegante.
          </p>
          <div className="mt-6 flex flex-wrap gap-3 text-sm text-white/85">
            <span className="rounded-full border border-white/15 bg-white/10 px-4 py-2">Falar</span>
            <span className="rounded-full border border-white/15 bg-white/10 px-4 py-2">Escrever</span>
            <span className="rounded-full border border-white/15 bg-white/10 px-4 py-2">Revisar</span>
          </div>
        </div>
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
          <h2 className="text-xl font-semibold text-white">Meta de pratica</h2>
          <div className="mt-6 flex items-end gap-3">
            <p className="text-5xl font-semibold text-white">{data.goalCompletionPercent}%</p>
            <p className="pb-2 text-slate-400">concluido</p>
          </div>
          <p className="mt-3 text-sm text-slate-300">
            {data.dailyGoal.completed} / {data.dailyGoal.target} blocos de pratica registrados hoje.
          </p>
          <div className="mt-5 h-3 rounded-full bg-white/5">
            <div
              className="h-full rounded-full bg-[linear-gradient(90deg,#5FFBF1,#35A7FF)]"
              style={{ width: `${data.goalCompletionPercent}%` }}
            />
          </div>
        </div>
      </div>

      <div className="grid gap-5 md:grid-cols-2 xl:grid-cols-3">
        {dashboardModules.map((module) => (
          <ModuleCard
            key={module.href}
            href={module.href}
            title={module.title}
            description={module.description}
            accent={module.accent}
            meta={`${data.moduleProgress[module.href.replace("/", "")] ?? 0}%`}
            metaLabel={module.metaLabel}
          />
        ))}
      </div>

      <div className="grid gap-6 xl:grid-cols-[1.5fr_1fr]">
        <ProgressChart
          values={data.weeklyActivity}
          title="Atividades desta semana"
          description="Atividades validas registradas por dia na janela semanal atual."
          valueSuffix=""
        />
        <div className="grid gap-5 sm:grid-cols-2 xl:grid-cols-1">
          <StatCard
            label="Sequencia diaria"
            value={`${data.currentStreakDays} dias`}
            hint="Consistencia real. Nao interrompa por preguica travestida de agenda."
          />
          <StatCard
            label="Atividades hoje"
            value={`${data.completedActivitiesToday}`}
            hint="Contagem real de atividades validas concluidas no dia atual."
          />
          <StatCard
            label="Atividades na semana"
            value={`${data.weeklyActivityCount}`}
            hint="Volume real acumulado na janela semanal atual."
          />
          <StatCard
            label="Proxima acao"
            value={data.nextSuggestedAction.type}
            hint="A pagina parou de inventar prioridade a partir de regra espalhada."
          />
          <StatCard
            label="Atividades recentes"
            value={`${data.recentActivities.length}`}
            hint="Volume pequeno e legivel, como o contrato exige."
          />
        </div>
      </div>
    </PageShell>
  );
}
