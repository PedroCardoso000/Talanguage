import type { CommunityInterestTargetType, CommunityLanguage, CommunityLevel } from "./requests";

export type PracticeGroup = {
  id: string;
  title: string;
  language: CommunityLanguage;
  level: CommunityLevel;
  description: string;
  memberCount: number;
};

export type PracticePartner = {
  userId: string;
  displayName: string;
  languagesPracticed: CommunityLanguage[];
  level: CommunityLevel;
  availabilityNote: string;
};

export type CommunityInterestResponse = {
  id: string;
  targetType: CommunityInterestTargetType;
  targetId: string;
  createdAt: string;
};
