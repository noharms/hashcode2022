package total;

import java.util.*;

public record Project(String name, int duration, int score, int bestBefore, List<SkillLevel> requiredLevels) {

    public Optional<List<Contributor>> needsTheseContributors(List<Contributor> availableContributors) {
        LinkedHashSet<Contributor> required = new LinkedHashSet<>();
        for (SkillLevel requiredLevel : requiredLevels) {
            Optional<Contributor> optionalContributor = availableContributors
                    .stream()
                    .filter(contributor -> !required.contains(contributor))
                    .filter(contributor -> contributor.skillLevel().getOrDefault(requiredLevel.skill(), 0) > requiredLevel.level())
                    .min(Comparator.comparing(contributor -> contributor.skillLevel().getOrDefault(requiredLevel.skill(), 0)));
            if (optionalContributor.isEmpty()) return Optional.empty();
            else required.add(optionalContributor.get());
        }
        return Optional.of(required.stream().toList());
    }
}
