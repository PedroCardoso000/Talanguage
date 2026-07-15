import type {
  AuthSessionResponse,
  AuthUser,
  CurrentSessionResponse,
} from "@/features/auth/contracts";
import { toLanguageOption, toLevelOption } from "@/lib/profile-options";
import type { UserProfile } from "@/types";

export function toUserProfile(
  user: AuthUser | CurrentSessionResponse | AuthSessionResponse["user"],
): UserProfile {
  return {
    id: user.id,
    name: user.name,
    email: user.email,
    targetLanguage: toLanguageOption(user.targetLanguage),
    currentLevel: toLevelOption(user.currentLevel),
    studyGoal: user.studyGoal,
    avatarUrl: user.avatarUrl,
  };
}
