"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";
import { useMemo, useState } from "react";
import { Bell, Flame, Search } from "lucide-react";

import { logoutUser } from "@/features/auth/actions/auth-actions";
import { useNotificationsPanel } from "@/features/notifications/hooks/use-notifications-panel";
import { useProgressSummary } from "@/features/progress/hooks/use-progress-summary";
import { useAppStore } from "@/store/use-app-store";

function formatRelativeDate(value: string) {
  const date = new Date(value);
  const diffMs = Date.now() - date.getTime();
  const diffHours = Math.max(Math.floor(diffMs / 3600000), 0);

  if (diffHours < 1) {
    return "Agora";
  }

  if (diffHours < 24) {
    return `${diffHours}h atras`;
  }

  const diffDays = Math.floor(diffHours / 24);
  return `${diffDays}d atras`;
}

export function Topbar() {
  const router = useRouter();
  const user = useAppStore((state) => state.user);
  const clearUser = useAppStore((state) => state.clearUser);
  const [isLoggingOut, setIsLoggingOut] = useState(false);
  const [isNotificationsOpen, setIsNotificationsOpen] = useState(false);
  const { notifications, unreadCount, isLoading, errorMessage, isUpdatingId, markAsRead } = useNotificationsPanel();
  const { summary } = useProgressSummary();

  const hasNotifications = useMemo(() => notifications.length > 0, [notifications]);

  async function handleLogout() {
    setIsLoggingOut(true);

    try {
      await logoutUser();
    } finally {
      clearUser();
      setIsLoggingOut(false);
      router.replace("/login");
    }
  }

  return (
    <header className="flex flex-col gap-4 border-b border-white/10 px-4 py-4 sm:px-6 xl:flex-row xl:items-center xl:justify-between">
      <div className="relative w-full max-w-2xl">
        <Search className="pointer-events-none absolute left-4 top-1/2 h-4 w-4 -translate-y-1/2 text-slate-500" />
        <input
          className="h-12 w-full rounded-2xl border border-white/10 bg-white/5 pl-11 pr-4 text-sm text-white outline-none placeholder:text-slate-500 focus:border-cyan-400/35"
          placeholder='Buscar algo (ex.: "praticar apresentacao")'
        />
      </div>
      <div className="flex items-center gap-3 self-end xl:self-auto">
        <div className="flex items-center gap-2 rounded-2xl border border-white/10 bg-white/5 px-4 py-2">
          <Flame className="h-4 w-4 text-orange-400" />
          <div>
            <p className="text-sm font-semibold text-white">{summary?.streakDays ?? "--"}</p>
            <p className="text-xs text-slate-400">Sequencia</p>
          </div>
        </div>
        <div className="relative">
          <button
            type="button"
            onClick={() => setIsNotificationsOpen((current) => !current)}
            className="relative flex h-11 w-11 items-center justify-center rounded-2xl border border-white/10 bg-white/5 text-slate-300"
          >
            <Bell className="h-5 w-5" />
            {unreadCount > 0 ? (
              <span className="absolute -right-1 -top-1 flex h-5 min-w-5 items-center justify-center rounded-full bg-cyan-300 px-1 text-[11px] font-semibold text-slate-950">
                {unreadCount}
              </span>
            ) : null}
          </button>
          {isNotificationsOpen ? (
            <div className="absolute right-0 top-14 z-20 w-96 rounded-[1.75rem] border border-white/10 bg-[#0b1528] p-4 shadow-2xl">
              <div className="flex items-center justify-between">
                <div>
                  <p className="text-sm uppercase tracking-[0.18em] text-cyan-200/70">Notificacoes</p>
                  <p className="mt-1 text-sm text-slate-400">{unreadCount} nao lidas</p>
                </div>
                <button
                  type="button"
                  onClick={() => setIsNotificationsOpen(false)}
                  className="rounded-full border border-white/10 px-3 py-1 text-xs text-slate-300"
                >
                  Fechar
                </button>
              </div>
              <div className="mt-4 space-y-3">
                {isLoading ? <div className="rounded-2xl bg-white/5 p-4 text-sm text-slate-300">Carregando...</div> : null}
                {!isLoading && errorMessage ? (
                  <div className="rounded-2xl bg-rose-400/10 p-4 text-sm text-rose-100">{errorMessage}</div>
                ) : null}
                {!isLoading && !errorMessage && !hasNotifications ? (
                  <div className="rounded-2xl bg-white/5 p-4 text-sm text-slate-300">Nenhuma notificacao por enquanto.</div>
                ) : null}
                {!isLoading && !errorMessage
                  ? notifications.map((notification) => (
                      <div
                        key={notification.id}
                        className={`rounded-2xl border p-4 ${
                          notification.read
                            ? "border-white/10 bg-white/5"
                            : "border-cyan-400/25 bg-cyan-400/10"
                        }`}
                      >
                        <div className="flex items-start justify-between gap-3">
                          <div>
                            <p className="text-sm font-semibold text-white">{notification.title}</p>
                            <p className="mt-2 text-sm leading-6 text-slate-300">{notification.message}</p>
                            <p className="mt-2 text-xs text-slate-500">{formatRelativeDate(notification.createdAt)}</p>
                          </div>
                          {!notification.read ? (
                            <button
                              type="button"
                              onClick={() => void markAsRead(notification.id)}
                              disabled={isUpdatingId === notification.id}
                              className="rounded-full border border-cyan-300/30 px-3 py-1 text-xs text-cyan-100 disabled:opacity-60"
                            >
                              {isUpdatingId === notification.id ? "..." : "Marcar"}
                            </button>
                          ) : null}
                        </div>
                        {notification.actionRoute ? (
                          <Link
                            href={notification.actionRoute}
                            onClick={() => setIsNotificationsOpen(false)}
                            className="mt-3 inline-flex text-sm text-cyan-200"
                          >
                            Abrir
                          </Link>
                        ) : null}
                      </div>
                    ))
                  : null}
              </div>
            </div>
          ) : null}
        </div>
        <Link href="/profile" className="flex items-center gap-3 rounded-2xl border border-white/10 bg-white/5 px-3 py-2">
          <div className="flex h-11 w-11 items-center justify-center overflow-hidden rounded-2xl bg-[linear-gradient(135deg,#5FFBF1,#2F7CFF)] font-semibold text-slate-950">
            {user?.avatarUrl ? (
              // eslint-disable-next-line @next/next/no-img-element
              <img src={user.avatarUrl} alt={user.name} className="h-full w-full object-cover" />
            ) : (
              user?.name.slice(0, 1).toUpperCase()
            )}
          </div>
          <div className="min-w-0">
            <p className="truncate text-sm font-semibold text-white">Ola, {user?.name}</p>
            <p className="text-xs text-slate-400">{user?.targetLanguage}</p>
          </div>
        </Link>
        <button
          type="button"
          onClick={handleLogout}
          disabled={isLoggingOut}
          className="rounded-2xl border border-white/10 bg-white/5 px-4 py-2 text-sm text-slate-200 transition hover:border-cyan-400/35 hover:text-white disabled:cursor-not-allowed disabled:opacity-60"
        >
          {isLoggingOut ? "Saindo..." : "Sair"}
        </button>
      </div>
    </header>
  );
}
