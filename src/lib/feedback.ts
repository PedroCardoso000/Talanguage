import { mockTestRecommendations } from "@/features/mock-test/mocks/questions";

export function buildMockRecommendation(score: number) {
  if (score >= 4) {
    return mockTestRecommendations.strong;
  }

  if (score >= 2) {
    return mockTestRecommendations.medium;
  }

  return mockTestRecommendations.weak;
}
