export function InterestButton({
  label,
  loading,
  registered,
  onClick,
}: {
  label: string;
  loading: boolean;
  registered: boolean;
  onClick: () => void;
}) {
  return (
    <button
      type="button"
      disabled={loading || registered}
      onClick={onClick}
      className="h-11 rounded-2xl bg-[linear-gradient(135deg,#5FFBF1,#35A7FF)] px-5 font-semibold text-slate-950 disabled:cursor-not-allowed disabled:opacity-60"
    >
      {registered ? "Interesse registrado" : loading ? "Registrando..." : label}
    </button>
  );
}
