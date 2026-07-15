import type { WritingChallenge } from "@/features/writing/contracts";

export type WritingChallengeSeed = WritingChallenge & {
  title: string;
  focus: string;
};

export const writingChallengeSeeds: WritingChallengeSeed[] = [
  {
    id: "follow-up-email",
    title: "E-mail de follow-up",
    language: "ENGLISH",
    level: "BEGINNER",
    prompt: "Write a short follow-up email after a meeting and reinforce your interest in continuing the conversation.",
    focus: "Professional tone and clarity.",
  },
  {
    id: "trip-story",
    title: "Relato de viagem",
    language: "ENGLISH",
    level: "INTERMEDIATE",
    prompt: "Describe a memorable trip in 6 to 8 sentences, including the place, your emotions, and what you learned.",
    focus: "Narrative flow and descriptive vocabulary.",
  },
  {
    id: "opinion",
    title: "Opiniao estruturada",
    language: "ENGLISH",
    level: "ADVANCED",
    prompt: "Defend your opinion about studying languages every day in a short text with an opening, one argument, and a closing.",
    focus: "Cohesion and organization.",
  },
];

export const writingFeedbackSuggestions = [
  "Open with a clearer objective and end with one concrete next step.",
  "Use a few connectors to make the text sound less basic and more natural.",
  "Break long sentences into shorter blocks with one idea each.",
  "Add one personal detail so the message sounds less robotic.",
];

function toFallbackTitle(id: string) {
  return id
    .split("-")
    .map((chunk) => chunk.charAt(0).toUpperCase() + chunk.slice(1))
    .join(" ");
}

export function toWritingChallengeDisplay(challenge: WritingChallenge) {
  const seed = writingChallengeSeeds.find((item) => item.id === challenge.id);

  return {
    id: challenge.id,
    language: challenge.language,
    level: challenge.level,
    prompt: challenge.prompt,
    title: seed?.title ?? toFallbackTitle(challenge.id),
    focus: challenge.focus ?? seed?.focus ?? "Clarity and structure.",
  };
}
