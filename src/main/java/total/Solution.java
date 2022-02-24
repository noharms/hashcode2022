package total;

import java.util.*;

public record Solution(LinkedHashMap<Project, List<Contributor>> projectToAssignments) {

    public long computeScore() {
        Map<Contributor, Integer> availableAt = new HashMap<>();
        long totalScore = 0;
        for (Map.Entry<Project, List<Contributor>> entry : projectToAssignments.entrySet()) {
            Project project = entry.getKey();
            List<Contributor> contributors = entry.getValue();
            int firstDateWhereAllAreAvailable = contributors.stream()
                    .mapToInt(contributor -> availableAt.getOrDefault(contributor, 0))
                    .min()
                    .getAsInt();
            int freeAgainAtDay = firstDateWhereAllAreAvailable + project.duration();
            contributors.forEach(contributor -> availableAt.put(contributor, freeAgainAtDay));
            if (contributors.size() != project.requiredLevels().size()) return -1;
            for (int value = 0; value < contributors.size(); value++) {
                final Contributor contributor = contributors.get(value);
                final SkillLevel skillLevel = project.requiredLevels().get(value);
                if (contributor.skillLevel().get(skillLevel.skill()) < skillLevel.level()) return -1;
            }
            project.levelup(contributors);
            final int penalty = Math.max(freeAgainAtDay - project.bestBefore(), 0);
            final int score = project.score() - penalty;
            totalScore += score;
        }
        return totalScore;
    }

}
