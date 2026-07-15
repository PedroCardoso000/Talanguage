"use client";

import { PageShell } from "@/components/page-shell";
import { ProgressChart } from "@/components/progress-chart";
import { ProgressMeter } from "@/components/shared/progress-meter";
import { StatCard } from "@/components/stat-card";
import { useProgressReadModel } from "@/features/progress/hooks/use-progress-read-model";

export default function ProgressPage() {
  const { data, isLoading, errorMessage } = useProgressReadModel();

  if (isLoading) {
    return (
      <PageShell
        title="Carregando progresso"
        description="Reunindo seu resumo compartilhado antes de mostrar os numeros."
      >
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6 text-slate-300">
          Carregando read model de progresso...
        </div>
      </PageShell>
    );
  }

  if (errorMessage || !data) {
    return (
      <PageShell title="Progresso" description="O resumo nao foi carregado desta vez.">
        <div className="rounded-[2rem] border border-rose-400/20 bg-rose-400/10 p-6 text-rose-100">
          {errorMessage ?? "Nao foi possivel carregar o progresso agora."}
        </div>
      </PageShell>
    );
  }

  if (data.isEmpty) {
    return (
      <PageShell
        title="Progresso"
        description="Sem atividade persistida, nao existe progresso para enfeitar. Existe apenas o proximo treino."
      >
        <div className="rounded-[2rem] border border-dashed border-white/15 bg-white/5 p-8 text-slate-300">
          <h2 className="text-2xl font-semibold text-white">Seu painel ainda esta vazio</h2>
          <p className="mt-3 max-w-2xl text-sm leading-7">
            Assim que voce concluir speaking, writing, revisao ou simulado, este resumo passa a refletir dados reais
            persistidos no backend.
          </p>
        </div>
      </PageShell>
    );
  }

  return (
    <PageShell
      title="Progresso"
      description="Os numeros abaixo mostram se voce esta construindo fluencia ou so colecionando sensacao de esforco."
    >
      <div className="grid gap-5 md:grid-cols-2 xl:grid-cols-3">
        <StatCard label="Sequencia atual" value={`${data.summary.streakDays} dias`} hint="Calculada no backend a partir de atividades reais." />
        <StatCard label="Melhor sequencia" value={`${data.summary.longestStreakDays} dias`} hint="Maior bloco continuo de dias ativos ja registrado." />
        <StatCard label="Ultima atividade" value={data.summary.lastActivityDate ?? "Sem registro"} hint="Ultimo dia com atividade valida persistida." />
        <StatCard label="Dias praticados" value={`${data.summary.activityTotals.daysPracticed}`} hint="Quantidade real de dias com pelo menos uma atividade persistida." />
        <StatCard label="Revisoes de flashcards" value={`${data.summary.activityTotals.flashcardReviews}`} hint="Cada revisao registrada entra aqui. Nada de seed fixa." />
        <StatCard label="Speaking concluidos" value={`${data.summary.activityTotals.speakingSessions}`} hint="Sessoes de fala finalizadas e salvas." />
        <StatCard label="Writing enviados" value={`${data.summary.activityTotals.writingSubmissions}`} hint="Desafios de escrita realmente submetidos." />
        <StatCard label="Simulados concluidos" value={`${data.summary.activityTotals.mockTestsCompleted}`} hint="Tentativas finalizadas com resultado persistido." />
        <StatCard label="Nivel estimado" value={data.levelStatus} hint="Sem regra de produto e backend para nivel, o valor honesto e assumir que ainda nao existe." />
        <StatCard label="Total de atividades" value={`${data.summary.totalActivities}`} hint={`Meta diaria: ${data.summary.dailyGoal.completed}/${data.summary.dailyGoal.target}.`} />
      </div>

      <div className="grid gap-6 xl:grid-cols-[1.4fr_1fr]">
        <ProgressChart
          values={data.weeklyActivity}
          description="Atividades persistidas por dia na janela semanal."
          valueSuffix=""
        />
        <div className="rounded-[2rem] border border-white/10 bg-white/5 p-6">
          <h2 className="text-2xl font-semibold text-white">Placar de desempenho</h2>
          <div className="mt-6 space-y-4">
            <ProgressMeter label="Speaking score" value={data.summary.skillProgress.speaking} valueLabel={`${data.summary.skillProgress.speaking}/100`} color="from-cyan-300 to-sky-500" />
            <ProgressMeter label="Writing score" value={data.summary.skillProgress.writing} valueLabel={`${data.summary.skillProgress.writing}/100`} color="from-indigo-300 to-blue-500" />
            <ProgressMeter label="Consistencia" value={data.summary.skillProgress.consistency} valueLabel={`${data.summary.skillProgress.consistency}/100`} color="from-emerald-300 to-cyan-500" />
          </div>
        </div>
      </div>
    </PageShell>
  );
}
