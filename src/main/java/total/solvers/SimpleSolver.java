package total.solvers;

import total.Contributor;
import total.ProblemDescription;
import total.Project;
import total.Solution;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleSolver extends Solver {
    public SimpleSolver(ProblemDescription problem) {
        super(problem);
    }

    @Override
    public Solution solve() {
        List<Project> projectsByScore = problem.projects()
                .stream()
                .sorted(Comparator.comparing(Project::score).reversed())
                .collect(Collectors.toList());
        Map<Contributor, Integer> occupiedUntil = new HashMap<>();
        problem.contributors()
                .forEach(contributor -> occupiedUntil.put(contributor, 0));
        int round = 0;
        long maxNumberRounds = problem.projects().stream().mapToLong(Project::duration).sum();
        Project projectToWorkOn;
        LinkedHashMap<Project, List<Contributor>> projectToAssignments = new LinkedHashMap<>();
        while (round < maxNumberRounds) {
            do {
                int finalRound = round;
                projectToWorkOn = null;
                for (Project project : projectsByScore) {
                    List<Contributor> availableContributors = occupiedUntil.entrySet().stream().filter(entry -> entry.getValue() <= finalRound).map(Map.Entry::getKey).collect(Collectors.toList());
                    Optional<List<Contributor>> contributors = project.needsTheseContributors(availableContributors);
                    if (contributors.isPresent()) {
                        projectToAssignments.put(project, contributors.get());
                        projectToWorkOn = project;
                        contributors.get().forEach(contributor -> occupiedUntil.merge(contributor, project.duration(), Integer::sum));
                    }
                }
                if (projectToWorkOn != null) {
                    projectsByScore.remove(projectToWorkOn);
                }
            } while (projectToWorkOn != null);
            round++;
        }

        return new Solution(projectToAssignments);
    }


}
