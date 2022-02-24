package total;

import java.util.Map;
import java.util.stream.Collectors;

public record Analysis(ProblemDescription problemDescription) {

    public Map<Project, Double> projectsToValue() {
        return problemDescription.projects().stream()
                                 .collect(Collectors.toMap(project -> project, Project::heuristicValue));
    }

    public Map<Contributor, Double> contributorsToValue() {
        return problemDescription.contributors().stream()
                                 .collect(Collectors.toMap(contributor -> contributor, Contributor::heuristicValue));
    }
}
