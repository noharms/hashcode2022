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
        Map<Contributor, Integer> occupiedUntil = new LinkedHashMap<>();
        problem.contributors()
                .stream()
                .sorted(Comparator.comparing(Contributor::heuristicValue))
                .forEach(contributor -> occupiedUntil.put(contributor, 0));
        int currentDay = 0;
        long maxNumberDays = problem.projects().stream().mapToLong(Project::duration).sum()*10;
        Project projectToWorkOn;
        LinkedHashMap<Project, List<Contributor>> projectToAssignments = new LinkedHashMap<>();
        while (currentDay < maxNumberDays) {
            do {
                int finalRound = currentDay;
                projectToWorkOn = null;
                for (Project project : projectsByScore) {
                    List<Contributor> availableContributors = occupiedUntil.entrySet().stream().filter(entry -> entry.getValue() <= finalRound).map(Map.Entry::getKey).collect(Collectors.toList());
                    Optional<List<Contributor>> contributors = project.needsTheseContributors(availableContributors);
                    if (contributors.isPresent()) {
                        projectToAssignments.put(project, contributors.get());
                        projectToWorkOn = project;
                        project.levelup(contributors.get());
                        contributors.get().forEach(contributor -> occupiedUntil.merge(contributor, project.duration(), Integer::sum));
                    }
                }
                if (projectToWorkOn != null) {
                    projectsByScore.remove(projectToWorkOn);
                }
            } while (projectToWorkOn != null);
            currentDay++;
        }
        return new Solution(projectToAssignments);
    }


}
