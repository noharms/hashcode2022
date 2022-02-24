package total;

import java.util.*;
import java.util.stream.IntStream;

public record Project(String name, int duration, int score, int bestBefore, List<SkillLevel> requiredLevels) {

    public static double a = 1;
    public static double b = 1;
    public static double c = 1;

    public Optional<List<Contributor>> needsTheseContributors(List<Contributor> availableContributors) {
        LinkedHashSet<Contributor> required = new LinkedHashSet<>();
        for (SkillLevel requiredLevel : requiredLevels) {
            Optional<Contributor> optionalContributor = availableContributors
                    .stream()
                    .filter(contributor -> !required.contains(contributor))
                    .filter(contributor -> contributor.skillLevel().getOrDefault(requiredLevel.skill(), 0) >= requiredLevel.level())
                    .findFirst();
            if (optionalContributor.isEmpty()) return Optional.empty();
            else required.add(optionalContributor.get());
        }
        return Optional.of(required.stream().toList());
    }

    public void levelup(List<Contributor> contributors) {
        IntStream.range(0, contributors.size())
                .forEach(index -> {
                    final String skill = requiredLevels.get(index).skill();
                    if (requiredLevels.get(index).level() >= contributors.get(index).skillLevel().getOrDefault(skill, 0)) {
                        contributors.get(index).skillLevel().merge(skill, 1, Integer::sum);
                    }
                });
    }

    // a * score / (b * duration + c * nRequiredSkills)
    public double heuristicValue() {
        double numerator = a * score;
        double denominator = b * duration + c * requiredLevels.size();
        return numerator / denominator;
    }
}
