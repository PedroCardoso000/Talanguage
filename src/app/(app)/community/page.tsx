"use client";

import { PageShell } from "@/components/page-shell";
import { CommunityPreviewCard } from "@/features/community/components/community-preview-card";
import {
  communityPreviewGroups,
  communityPreviewPartners,
} from "@/features/community/mocks/preview";

export default function CommunityPage() {
  return (
    <PageShell
      title="Comunidade"
      description="Esta área fica para a próxima versão. Primeiro, o produto precisa sustentar prática real com consistência antes de abrir uma camada social."
      action={
        <div className="inline-flex items-center rounded-full border border-amber-300/20 bg-amber-300/10 px-4 py-2 text-sm font-medium text-amber-100">
          Próxima versão
        </div>
      }
    >
      <div className="rounded-[2rem] border border-white/10 bg-[linear-gradient(135deg,rgba(255,157,102,0.14),rgba(255,255,255,0.04))] p-6">
        <p className="text-sm uppercase tracking-[0.2em] text-amber-100/80">Preview bloqueado</p>
        <h2 className="mt-4 text-3xl font-semibold text-white">A comunidade ainda não está liberada.</h2>
        <p className="mt-3 max-w-3xl text-slate-200">
          Quando esta área abrir, ela deve ajudar você a encontrar grupos e parceiros para praticar. Não vai existir chat fake,
          matchmaking inventado ou promessa vazia de networking.
        </p>
        <div className="mt-6 grid gap-4 md:grid-cols-3">
          <div className="rounded-[1.5rem] border border-white/10 bg-white/5 p-4">
            <p className="text-sm font-semibold text-white">O que deve entrar</p>
            <p className="mt-2 text-sm leading-6 text-slate-300">Grupos de prática, parceiros por objetivo e interesse registrado de forma simples.</p>
          </div>
          <div className="rounded-[1.5rem] border border-white/10 bg-white/5 p-4">
            <p className="text-sm font-semibold text-white">O que não entra agora</p>
            <p className="mt-2 text-sm leading-6 text-slate-300">Chat em tempo real, feed social, vídeo e qualquer camada que distraia do treino.</p>
          </div>
          <div className="rounded-[1.5rem] border border-white/10 bg-white/5 p-4">
            <p className="text-sm font-semibold text-white">O que fazer enquanto isso</p>
            <p className="mt-2 text-sm leading-6 text-slate-300">Use speaking, writing, review e mock test. Eles já geram progresso real hoje.</p>
          </div>
        </div>
      </div>

      <div className="grid gap-6 xl:grid-cols-2">
        <section className="space-y-5">
          <div>
            <p className="text-sm uppercase tracking-[0.2em] text-cyan-200/70">Grupos</p>
            <h2 className="mt-2 text-3xl font-semibold text-white">Prévia do que deve existir</h2>
          </div>
          {communityPreviewGroups.map((group) => (
            <CommunityPreviewCard
              key={group.id}
              badge={group.badge}
              title={group.title}
              description={group.description}
              meta={group.meta}
              cta={group.cta}
            />
          ))}
        </section>

        <section className="space-y-5">
          <div>
            <p className="text-sm uppercase tracking-[0.2em] text-cyan-200/70">Parceiros</p>
            <h2 className="mt-2 text-3xl font-semibold text-white">Prévia do futuro módulo</h2>
          </div>
          {communityPreviewPartners.map((partner) => (
            <CommunityPreviewCard
              key={partner.id}
              badge={partner.badge}
              title={partner.title}
              description={partner.description}
              meta={partner.meta}
              cta={partner.cta}
            />
          ))}
        </section>
      </div>
    </PageShell>
  );
}
