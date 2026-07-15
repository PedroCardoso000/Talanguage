export function CommunityPreviewCard({
  badge,
  title,
  description,
  meta,
  cta,
}: {
  badge: string;
  title: string;
  description: string;
  meta: string;
  cta: string;
}) {
  return (
    <div className="relative overflow-hidden rounded-[1.75rem] border border-white/10 bg-white/5">
      <div className="pointer-events-none blur-[2px] opacity-60">
        <div className="p-5">
          <div className="flex flex-wrap items-center gap-3">
            <span className="rounded-full border border-cyan-400/25 bg-cyan-400/10 px-3 py-1 text-xs uppercase tracking-[0.16em] text-cyan-200">
              {badge}
            </span>
            <span className="rounded-full border border-white/10 bg-white/5 px-3 py-1 text-xs text-slate-300">
              Preview
            </span>
          </div>
          <h3 className="mt-4 text-2xl font-semibold text-white">{title}</h3>
          <p className="mt-3 text-sm leading-7 text-slate-300">{description}</p>
          <p className="mt-4 text-sm text-slate-400">{meta}</p>
          <div className="mt-5">
            <div className="inline-flex rounded-2xl bg-[linear-gradient(135deg,#5FFBF1,#35A7FF)] px-4 py-3 font-semibold text-slate-950">
              {cta}
            </div>
          </div>
        </div>
      </div>

      <div className="absolute inset-0 flex items-center justify-center bg-[linear-gradient(180deg,rgba(7,17,34,0.2),rgba(7,17,34,0.72))] p-6 text-center">
        <div className="rounded-[1.5rem] border border-white/10 bg-[#071122]/85 px-5 py-4 backdrop-blur">
          <p className="text-xs uppercase tracking-[0.22em] text-cyan-200/75">Próxima versão</p>
          <p className="mt-2 text-sm leading-6 text-slate-200">
            Este fluxo ainda não está aberto. Quando entrar, precisa resolver prática real, não virar rede social genérica.
          </p>
        </div>
      </div>
    </div>
  );
}
