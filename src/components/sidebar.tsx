"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import {
  BarChart3,
  BookOpenCheck,
  FileText,
  Gauge,
  Home,
  Mic,
  Target,
  UserCircle2,
  Users,
} from "lucide-react";

import { Logo } from "@/components/logo";
import { cn } from "@/lib/utils";

const links = [
  { href: "/dashboard", label: "Inicio", icon: Home },
  { href: "/speak", label: "Falar", icon: Mic },
  { href: "/write", label: "Escrever", icon: FileText },
  { href: "/review", label: "Revisar", icon: BookOpenCheck },
  { href: "/community", label: "Comunidade", icon: Users },
  { href: "/profile", label: "Perfil", icon: UserCircle2 },
  { href: "/goals", label: "Metas", icon: Target },
  { href: "/progress", label: "Progresso", icon: BarChart3 },
  { href: "/mock-test", label: "Simulado", icon: Gauge },
];

export function Sidebar() {
  const pathname = usePathname();

  return (
    <>
      <aside className="hidden w-72 shrink-0 border-r border-white/10 bg-[#071122]/85 px-5 py-6 backdrop-blur xl:flex xl:flex-col">
        <Logo />
        <nav className="mt-10 space-y-2">
          {links.map(({ href, label, icon: Icon }) => {
            const active = pathname === href;
            return (
              <Link
                key={href}
                href={href}
                className={cn(
                  "flex items-center gap-3 rounded-2xl px-4 py-3 text-base text-slate-300 transition hover:bg-white/5 hover:text-white",
                  active && "bg-cyan-400/12 text-cyan-200 ring-1 ring-cyan-400/30",
                )}
              >
                <Icon className="h-5 w-5" />
                {label}
              </Link>
            );
          })}
        </nav>
        <div className="mt-auto rounded-[1.75rem] border border-cyan-400/15 bg-[linear-gradient(180deg,rgba(18,31,60,0.95),rgba(10,20,38,0.9))] p-5">
          <p className="text-xs uppercase tracking-[0.22em] text-cyan-200/70">Tala Pro</p>
          <h3 className="mt-3 text-xl font-semibold text-white">Treinos mais profundos</h3>
          <p className="mt-2 text-sm leading-6 text-slate-300">
            Evolucao nao vem de volume aleatorio. Vem de pratica guiada, consistencia e revisao inteligente.
          </p>
          <button className="mt-5 w-full rounded-2xl bg-[linear-gradient(135deg,#5FFBF1,#35A7FF)] px-4 py-3 font-semibold text-slate-950">
            Ver beneficios
          </button>
        </div>
      </aside>
      <nav className="scrollbar-none flex gap-3 overflow-x-auto border-b border-white/10 px-4 py-4 xl:hidden">
        {links.map(({ href, label }) => {
          const active = pathname === href;
          return (
            <Link
              key={href}
              href={href}
              className={cn(
                "whitespace-nowrap rounded-full border border-white/10 px-4 py-2 text-sm text-slate-300",
                active && "border-cyan-400/40 bg-cyan-400/10 text-cyan-200",
              )}
            >
              {label}
            </Link>
          );
        })}
      </nav>
    </>
  );
}
