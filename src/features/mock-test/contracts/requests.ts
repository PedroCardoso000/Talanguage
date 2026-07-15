export type MockTestAnswerSubmission = {
  questionId: string;
  selectedOption: string;
};

export type MockTestSubmissionRequest = {
  mockTestId: string;
  answers: MockTestAnswerSubmission[];
};
