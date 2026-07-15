type DashboardModule = {
  href: string;
  title: string;
  description: string;
  accent: string;
  metaLabel?: string;
};

export const dashboardModules: DashboardModule[] = [
  {
    href: "/speak",
    title: "Falar",
    description: "Pratique respostas, conversas e apresentações com feedback imediato.",
    accent: "linear-gradient(135deg,#5FFBF1,#1AB8B1)",
  },
  {
    href: "/write",
    title: "Escrever",
    description: "Escreva textos curtos com foco em clareza, gramática e vocabulário.",
    accent: "linear-gradient(135deg,#7AA2FF,#356BFF)",
  },
  {
    href: "/review",
    title: "Revisar",
    description: "Revise vocabulário, expressões e pontos fracos sem desperdiçar energia.",
    accent: "linear-gradient(135deg,#C287FF,#8B5CFF)",
  },
  {
    href: "/community",
    title: "Comunidade",
    description: "Próxima versão para grupos e parceiros de prática, sem prometer chat ou matchmaking agora.",
    accent: "linear-gradient(135deg,#FF9D66,#FF6B6B)",
    metaLabel: "Próxima versão",
  },
  {
    href: "/goals",
    title: "Metas",
    description: "Ajuste seu alvo semanal para evitar progresso imaginário.",
    accent: "linear-gradient(135deg,#7FFF8E,#4DBD5F)",
  },
  {
    href: "/progress",
    title: "Progresso",
    description: "Veja se sua disciplina existe nos dados ou só no discurso.",
    accent: "linear-gradient(135deg,#5FFBF1,#278CFF)",
  },
  {
    href: "/mock-test",
    title: "Simulado",
    description: "Teste sua base com perguntas objetivas e recomendação de estudo.",
    accent: "linear-gradient(135deg,#FFE07A,#F89B29)",
  },
];
