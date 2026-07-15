export type MockTestQuestion = {
  id: string;
  question: string;
  options: string[];
};

export type CurrentMockTestResponse = {
  id: string;
  title: string;
  questions: MockTestQuestion[];
};

export type MockTestResultQuestion = {
  questionId: string;
  selectedOption: string;
  correctOption: string;
  explanation: string;
  isCorrect: boolean;
};

export type MockTestResult = {
  attemptId: string;
  mockTestId: string;
  score: number;
  totalQuestions: number;
  recommendation: string;
  completedAt: string;
  questions: MockTestResultQuestion[];
};
