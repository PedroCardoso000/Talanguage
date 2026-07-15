import type { ProfileResponse } from "@/features/profile/contracts";
import { toLanguageOption, toLanguageValue, toLevelOption, toLevelValue } from "@/lib/profile-options";
import type { UserProfile } from "@/types";

export type ProfileFormValues = {
  name: string;
  email: string;
  targetLanguage: UserProfile["targetLanguage"];
  currentLevel: UserProfile["currentLevel"];
  studyGoal: string;
  avatarUrl: string;
};

export function toProfileForm(profile: ProfileResponse): ProfileFormValues {
  return {
    name: profile.name,
    email: profile.email,
    targetLanguage: toLanguageOption(profile.targetLanguage),
    currentLevel: toLevelOption(profile.currentLevel),
    studyGoal: profile.studyGoal ?? "",
    avatarUrl: profile.avatarUrl ?? "",
  };
}

export function toProfileRequest(form: ProfileFormValues) {
  return {
    name: form.name,
    targetLanguage: toLanguageValue(form.targetLanguage),
    currentLevel: toLevelValue(form.currentLevel),
    studyGoal: form.studyGoal.trim() ? form.studyGoal.trim() : null,
    avatarUrl: form.avatarUrl.trim() ? form.avatarUrl.trim() : null,
  };
}
