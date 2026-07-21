package com.talalanguage.api.application.dashboard;

import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.application.dashboard.model.DashboardSummaryView;
import com.talalanguage.api.application.progress.port.DailyGoalRepository;
import com.talalanguage.api.application.progress.port.LearningActivityRepository;
import com.talalanguage.api.application.progress.port.ProgressCalculator;
import com.talalanguage.api.domain.auth.User;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.LearningActivity;
import com.talalanguage.api.domain.progress.LearningDayPolicy;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class GetDashboardSummaryUseCase {

    private final UserRepository userRepository;
    private final LearningActivityRepository learningActivityRepository;
    private final DailyGoalRepository dailyGoalRepository;
    private final ProgressCalculator progressCalculator;
    private final Clock clock;

    public GetDashboardSummaryUseCase(
            UserRepository userRepository,
            LearningActivityRepository learningActivityRepository,
            DailyGoalRepository dailyGoalRepository,
            ProgressCalculator progressCalculator,
            Clock clock
    ) {
        this.userRepository = userRepository;
        this.learningActivityRepository = learningActivityRepository;
        this.dailyGoalRepository = dailyGoalRepository;
        this.progressCalculator = progressCalculator;
        this.clock = clock;
    }

    public DashboardSummaryView execute(Command command) {
        UserId userId = UserId.from(command.userId());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Authenticated user not found."));
        List<LearningActivity> activities = learningActivityRepository.findByUserId(userId);
        var summary = progressCalculator.calculateSummary(
                userId,
                activities,
                dailyGoalRepository.getForDate(userId, LearningDayPolicy.today(clock))
        );
        List<Integer> weeklyActivity = buildWeeklyActivity(activities);
        int completedActivitiesToday = countActivitiesForDate(activities, LearningDayPolicy.today(clock));

        return new DashboardSummaryView(
                user.name(),
                summary.streakDays(),
                completedActivitiesToday,
                weeklyActivity.stream().mapToInt(Integer::intValue).sum(),
                calculateGoalCompletionPercent(summary.dailyGoal().target(), summary.dailyGoal().completed()),
                buildModuleProgress(summary.skillProgress(), summary.dailyGoal().target(), summary.dailyGoal().completed()),
                weeklyActivity,
                new DashboardSummaryView.DailyGoalView(
                        summary.dailyGoal().target(),
                        summary.dailyGoal().completed()
                ),
                summary.skillProgress(),
                buildRecentActivities(activities),
                buildNextSuggestedAction(summary.skillProgress())
        );
    }

    private List<Integer> buildWeeklyActivity(List<LearningActivity> activities) {
        LocalDate today = LearningDayPolicy.today(clock);

        return java.util.stream.IntStream.rangeClosed(0, 6)
                .mapToObj(offset -> countActivitiesForDate(activities, today.minusDays(6L - offset)))
                .toList();
    }

    private int countActivitiesForDate(List<LearningActivity> activities, LocalDate date) {
        return (int) activities.stream()
                .filter(activity -> LearningDayPolicy.dateOf(activity.completedAt()).equals(date))
                .count();
    }

    private int calculateGoalCompletionPercent(int target, int completed) {
        if (target <= 0) {
            return 0;
        }

        return Math.min((int) Math.round((completed * 100.0) / target), 100);
    }

    private Map<String, Integer> buildModuleProgress(
            com.talalanguage.api.application.progress.model.SkillProgressView skillProgress,
            int dailyGoalTarget,
            int dailyGoalCompleted
    ) {
        int goalPercent = calculateGoalCompletionPercent(dailyGoalTarget, dailyGoalCompleted);

        return Map.of(
                "speak", skillProgress.speaking(),
                "write", skillProgress.writing(),
                "review", skillProgress.vocabulary(),
                "goals", goalPercent,
                "progress", skillProgress.consistency(),
                "mock-test", 0,
                "community", 0
        );
    }

    private List<DashboardSummaryView.RecentActivityView> buildRecentActivities(List<LearningActivity> activities) {
        return activities.stream()
                .sorted(Comparator.comparing(LearningActivity::completedAt, Comparator.reverseOrder()))
                .limit(5)
                .map(activity -> new DashboardSummaryView.RecentActivityView(
                        activity.id(),
                        activity.type().name(),
                        titleFor(activity.type()),
                        activity.completedAt().toString()
                ))
                .toList();
    }

    private DashboardSummaryView.NextSuggestedActionView buildNextSuggestedAction(
            com.talalanguage.api.application.progress.model.SkillProgressView skillProgress
    ) {
        if (skillProgress.speaking() <= skillProgress.writing()) {
            return new DashboardSummaryView.NextSuggestedActionView(
                    ActivityType.SPEAKING.name(),
                    "Continue praticando fala",
                    "/speak"
            );
        }

        return new DashboardSummaryView.NextSuggestedActionView(
                ActivityType.WRITING.name(),
                "Continue praticando escrita",
                "/write"
        );
    }

    private String titleFor(ActivityType type) {
        return switch (type) {
            case SPEAKING -> "Treino de fala concluido";
            case WRITING -> "Desafio de escrita enviado";
            case FLASHCARDS -> "Revisao de flashcards realizada";
            case GOALS -> "Meta atualizada";
            case MOCK_TEST -> "Simulado finalizado";
        };
    }

    public record Command(String userId) {
    }
}
