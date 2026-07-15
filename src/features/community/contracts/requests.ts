export type CommunityLanguage = "ENGLISH" | "SPANISH";
export type CommunityLevel = "BEGINNER" | "INTERMEDIATE" | "ADVANCED";
export type CommunityInterestTargetType = "GROUP" | "PARTNER";

export type ListPracticeGroupsQuery = {
  language?: CommunityLanguage;
  level?: CommunityLevel;
};

export type ListPracticePartnersQuery = {
  language?: CommunityLanguage;
};

export type RegisterCommunityInterestRequest = {
  targetType: CommunityInterestTargetType;
  targetId: string;
};
