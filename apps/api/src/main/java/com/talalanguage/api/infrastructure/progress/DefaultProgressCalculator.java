package com.talalanguage.api.infrastructure.progress;

import com.talalanguage.api.application.progress.model.ActivityTotalsView;
import com.talalanguage.api.application.progress.model.DailyGoalView;
import com.talalanguage.api.application.progress.model.ProgressSummaryView;
import com.talalanguage.api.application.progress.model.SkillProgressView;
import com.talalanguage.api.application.progress.model.WeeklyProgressSummaryView;
import com.talalanguage.api.application.progress.port.ProgressCalculator;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.DailyGoal;
import com.talalanguage.api.domain.progress.LearningActivity;
import com.talalanguage.api.domain.progress.SkillType;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class DefaultProgressCalculator implements ProgressCalculator {

    @Override
    public ProgressSummaryView calculateSummary(UserId userId, List<LearningActivity> activities, DailyGoal dailyGoal) {
        List<LearningActivity> userActivities = sortAndFilter(userId, activities);
        Set<LocalDate> activeDays = toActiveDays(userActivities);
        int completedToday = (int) userActivities.stream()
                .filter(activity -> activity.completedAt().atZone(ZoneOffset.UTC).toLocalDate().equals(dailyGoal.date()))
                .count();

        return new ProgressSummaryView(
                calculateCurrentStreak(activeDays),
                calculateLongestStreak(activeDays),
                resolveLastActivityDate(activeDays),
                buildDailyGoalView(dailyGoal, completedToday),
                buildSkillProgress(userActivities, activeDays),
                userActivities.size(),
                buildActivityTotals(userActivities, activeDays)
        );
    }

    @Override
    public WeeklyProgressSummaryView calculateWeeklySummary(UserId userId, List<LearningActivity> activities) {
        List<LearningActivity> userActivities = sortAndFilter(userId, activities);
        Set<LocalDate> activeDays = toActiveDays(userActivities);
        LocalDate today = LocalDate.now(ZoneOffset.UTC);
        LocalDate weekStart = today.minusDays(6);
        List<LearningActivity> weeklyActivities = userActivities.stream()
                .filter(activity -> {
                    LocalDate activityDate = activity.completedAt().atZone(ZoneOffset.UTC).toLocalDate();
                    return !activityDate.isBefore(weekStart) && !activityDate.isAfter(today);
                })
                .toList();

        return new WeeklyProgressSummaryView(
                weekStart.toString(),
                today.toString(),
                weeklyActivities.size(),
                calculateCurrentStreak(activeDays),
                buildSkillProgress(weeklyActivities, activeDays),
                buildDailyActivityCounts(weeklyActivities, weekStart)
        );
    }

    private List<LearningActivity> sortAndFilter(UserId userId, List<LearningActivity> activities) {
        return activities.stream()
                .filter(activity -> activity.userId().equals(userId))
                .sorted(Comparator.comparing(LearningActivity::completedAt))
                .toList();
    }

    private DailyGoalView buildDailyGoalView(DailyGoal dailyGoal, int completed) {
        String status = completed <= 0
                ? "NOT_STARTED"
                : completed >= dailyGoal.targetActivities() ? "COMPLETED" : "IN_PROGRESS";

        return new DailyGoalView(
                dailyGoal.targetActivities(),
                Math.min(completed, dailyGoal.targetActivities()),
                status
        );
    }

    private SkillProgressView buildSkillProgress(List<LearningActivity> activities, Set<LocalDate> activeDays) {
        int speaking = averageScore(activities, SkillType.SPEAKING);
        int writing = averageScore(activities, SkillType.WRITING);
        int vocabulary = averageScore(activities, SkillType.VOCABULARY);
        int consistency = Math.min(calculateCurrentStreak(activeDays) * 10, 100);

        return new SkillProgressView(speaking, writing, vocabulary, consistency);
    }

    private int averageScore(List<LearningActivity> activities, SkillType skillType) {
        List<LearningActivity> matchingActivities = activities.stream()
                .filter(activity -> activity.skill() == skillType)
                .toList();

        if (matchingActivities.isEmpty()) {
            return 0;
        }

        return (int) Math.round(matchingActivities.stream()
                .mapToInt(LearningActivity::score)
                .average()
                .orElse(0));
    }

    private Set<LocalDate> toActiveDays(List<LearningActivity> activities) {
        return activities.stream()
                .map(activity -> activity.completedAt().atZone(ZoneOffset.UTC).toLocalDate())
                .collect(java.util.stream.Collectors.toSet());
    }

    private ActivityTotalsView buildActivityTotals(List<LearningActivity> activities, Set<LocalDate> activeDays) {
        Map<ActivityType, Long> countsByType = activities.stream()
                .collect(java.util.stream.Collectors.groupingBy(LearningActivity::type, java.util.stream.Collectors.counting()));

        return new ActivityTotalsView(
                activeDays.size(),
                countsByType.getOrDefault(ActivityType.SPEAKING, 0L).intValue(),
                countsByType.getOrDefault(ActivityType.WRITING, 0L).intValue(),
                countsByType.getOrDefault(ActivityType.FLASHCARDS, 0L).intValue(),
                countsByType.getOrDefault(ActivityType.MOCK_TEST, 0L).intValue(),
                countsByType.getOrDefault(ActivityType.GOALS, 0L).intValue()
        );
    }

    private List<Integer> buildDailyActivityCounts(List<LearningActivity> activities, LocalDate weekStart) {
        return java.util.stream.IntStream.range(0, 7)
                .mapToObj(offset -> countActivitiesForDate(activities, weekStart.plusDays(offset)))
                .toList();
    }

    private int countActivitiesForDate(List<LearningActivity> activities, LocalDate date) {
        return (int) activities.stream()
                .filter(activity -> activity.completedAt().atZone(ZoneOffset.UTC).toLocalDate().equals(date))
                .count();
    }

    private int calculateCurrentStreak(Set<LocalDate> activeDays) {
        if (activeDays.isEmpty()) {
            return 0;
        }

        LocalDate latestActivityDay = activeDays.stream().max(LocalDate::compareTo).orElse(null);
        if (latestActivityDay == null) {
            return 0;
        }

        LocalDate yesterday = LocalDate.now(ZoneOffset.UTC).minusDays(1);
        if (latestActivityDay.isBefore(yesterday)) {
            return 0;
        }

        int streak = 0;
        LocalDate cursor = latestActivityDay;

        while (activeDays.contains(cursor)) {
            streak++;
            cursor = cursor.minusDays(1);
        }

        return streak;
    }

    private int calculateLongestStreak(Set<LocalDate> activeDays) {
        if (activeDays.isEmpty()) {
            return 0;
        }

        List<LocalDate> sortedDays = activeDays.stream().sorted().toList();
        int longest = 1;
        int current = 1;

        for (int index = 1; index < sortedDays.size(); index++) {
            LocalDate previous = sortedDays.get(index - 1);
            LocalDate currentDate = sortedDays.get(index);

            if (previous.plusDays(1).equals(currentDate)) {
                current++;
                longest = Math.max(longest, current);
                continue;
            }

            current = 1;
        }

        return longest;
    }

    private String resolveLastActivityDate(Set<LocalDate> activeDays) {
        return activeDays.stream()
                .max(LocalDate::compareTo)
                .map(LocalDate::toString)
                .orElse(null);
    }
}
