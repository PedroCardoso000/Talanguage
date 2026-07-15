import type { MockQuestion } from "@/types";

export const mockQuestions: MockQuestion[] = [
  {
    id: "q1",
    question: "Qual frase está gramaticalmente correta?",
    options: [
      "She go to work every day.",
      "She goes to work every day.",
      "She going to work every day.",
      "She gone to work every day.",
    ],
    correctAnswer: "She goes to work every day.",
    explanation: "No presente simples, he/she/it pede verbo com s.",
  },
  {
    id: "q2",
    question: "Qual expressão é mais apropriada para uma apresentação formal?",
    options: [
      "What's up, everyone?",
      "Let me walk you through the main points.",
      "This stuff is super cool.",
      "You guys should look at this.",
    ],
    correctAnswer: "Let me walk you through the main points.",
    explanation: "É a opção mais profissional e comum em contexto formal.",
  },
  {
    id: "q3",
    question: "Qual palavra completa melhor a frase: I need to ___ this report by 5 PM.",
    options: ["finish", "finishing", "finished", "finishes"],
    correctAnswer: "finish",
    explanation: "Após 'need to', usamos o verbo na forma base.",
  },
  {
    id: "q4",
    question: "Qual resposta mostra mais naturalidade em uma conversa?",
    options: [
      "I am agree with you.",
      "I very like this idea.",
      "I totally agree with you.",
      "I agree you very much.",
    ],
    correctAnswer: "I totally agree with you.",
    explanation: "A estrutura está correta e soa natural.",
  },
  {
    id: "q5",
    question: "Qual conectivo ajuda a contrastar ideias em um texto?",
    options: ["Because", "However", "Therefore", "Also"],
    correctAnswer: "However",
    explanation: "However é usado para introduzir contraste.",
  },
];

export const mockTestRecommendations = {
  strong:
    "Você já tem base consistente. Priorize conversação e escrita guiada para ganhar naturalidade.",
  medium:
    "Sua base existe, mas ainda está instável. Reforce revisão diária e exercícios curtos de escrita.",
  weak:
    "Você está tentando avançar sem consolidar fundamento. Foque em vocabulário essencial, presente simples e leitura curta diária.",
} as const;
