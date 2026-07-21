export function Logo({ compact = false }: { compact?: boolean }) {
  return (
    <div className="flex items-center gap-3">
      <img
        src="/icone-talanguage.png"
        alt="Ícone do Talanguage"
        width={44}
        height={44}
        className="
          h-11 w-11 shrink-0
          rounded-2xl
          object-cover
          drop-shadow-[0_0_18px_rgba(86,247,238,0.35)]
        "
      />

      {!compact && (
        <div className="leading-tight">
          <p className="font-display text-2xl font-semibold tracking-tight text-white">
            Talanguage
          </p>

          <p className="mt-1 text-sm text-slate-400">
            Prática diária com direção
          </p>
        </div>
      )}
    </div>
  );
}