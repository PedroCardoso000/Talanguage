import type { SpeakingTopic } from "@/features/speaking/contracts";

export type SpeakingTopicSeed = SpeakingTopic & {
  category: string;
  objective: string;
  mentorMessage: string;
  initialPrompt: string;
  followUpPrompt: string;
};

export const speakingTopicSeeds: SpeakingTopicSeed[] = [
  {
    id: "presentation",
    title: "Apresentacao profissional",
    language: "ENGLISH",
    level: "INTERMEDIATE",
    category: "Trabalho",
    objective: "Treinar apresentacao pessoal e contexto profissional.",
    mentorMessage:
      "Imagine que voce esta se apresentando em uma reuniao internacional. Fale com clareza e mostre confianca.",
    initialPrompt: "Tell me about your current role and the kind of projects you usually work on.",
    followUpPrompt: "What result from your work are you most proud of, and why?",
  },
  {
    id: "travel",
    title: "Check-in no hotel",
    language: "ENGLISH",
    level: "BEGINNER",
    category: "Viagem",
    objective: "Treinar vocabulario util para viagens.",
    mentorMessage:
      "Voce chegou ao hotel e precisa confirmar reserva, horarios e pedir ajuda com o quarto.",
    initialPrompt: "You have just arrived at the hotel. How would you confirm your reservation at the front desk?",
    followUpPrompt: "Now ask one extra question about breakfast, Wi-Fi, or check-out time.",
  },
  {
    id: "interview",
    title: "Entrevista de emprego",
    language: "ENGLISH",
    level: "ADVANCED",
    category: "Carreira",
    objective: "Ganhar confianca em respostas de entrevista.",
    mentorMessage:
      "Explique sua experiencia, destaque resultados e responda com objetividade.",
    initialPrompt: "Why are you interested in this role, and how does your background prepare you for it?",
    followUpPrompt: "Give one concrete example that proves your impact in a previous position.",
  },
];

function toFallbackCategory(level: SpeakingTopic["level"]) {
  switch (level) {
    case "BEGINNER":
      return "Fundamentos";
    case "INTERMEDIATE":
      return "Pratica guiada";
    case "ADVANCED":
      return "Performance";
    default:
      return "Speaking";
  }
}

export function toSpeakingScenarioDisplay(topic: SpeakingTopic) {
  return {
    id: topic.id,
    title: topic.title,
    level: topic.level,
    category: topic.category ?? toFallbackCategory(topic.level),
    objective: topic.objective ?? "Practice speaking with more clarity and better structure.",
    mentorMessage:
      topic.mentorMessage ?? "Respond with complete sentences and keep your main idea easy to follow.",
    initialPrompt:
      topic.initialPrompt ?? "Answer naturally and explain your idea with one concrete example.",
    followUpPrompt:
      speakingTopicSeeds.find((item) => item.id === topic.id)?.followUpPrompt ??
      "Add one more detail that supports your main point.",
  };
}
