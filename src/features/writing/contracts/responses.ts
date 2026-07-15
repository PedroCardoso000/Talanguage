import type { WritingLanguage, WritingLevel } from "@/features/writing/contracts/requests";

export type WritingSubmissionStatus = "REVIEWED";

export type WritingChallenge = {
  id: string;
  language: WritingLanguage;
  level: WritingLevel;
  prompt: string;
  focus: string;
};

export type WritingReviewFeedback = {
  strengths: string[];
  improvements: string[];
  nextAction: string;
};

export type CreateWritingSubmissionResponse = {
  submissionId: string;
  status: WritingSubmissionStatus;
  feedback: WritingReviewFeedback;
};

export type RecentWritingSubmission = {
  submissionId: string;
  challengeId: string;
  content: string;
  createdAt: string;
  status: WritingSubmissionStatus;
  feedback: WritingReviewFeedback;
};
