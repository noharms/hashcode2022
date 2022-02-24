package total.solvers;

import total.Contributor;
import total.ProblemDescription;
import total.Project;
import total.Solution;

import java.util.*;
import java.util.stream.Collectors;

public class ComplexSolver extends Solver {
    public ComplexSolver(ProblemDescription problem) {
        super(problem);
    }

    @Override
    public Solution solve() {
        List<Project> projectsByScore = problem.projects()
                .stream()
                .sorted(Comparator.comparing(Project::heuristicValue).reversed())
                .collect(Collectors.toList());
        Map<Contributor, Integer> occupiedUntil = new HashMap<>();
        problem.contributors()
                .forEach(contributor -> occupiedUntil.put(contributor, 0));
        Map<String, List<Contributor>> sortedContributors = new HashMap<>();
        for (Contributor contributor : problem.contributors()) {
            for (Map.Entry<String, Integer> entry : contributor.skillLevel().entrySet()) {
                sortedContributors.computeIfAbsent(entry.getKey(), s -> new ArrayList<>())
                        .add(contributor);
            }
        }
        sortedContributors.values().forEach(contributors -> contributors.sort(Comparator.comparing(Contributor::heuristicValue)));
        int currentDay = 0;
        long maxNumberDays = problem.projects().stream().mapToLong(Project::duration).sum() * 10;
        Project projectToWorkOn;
        LinkedHashMap<Project, List<Contributor>> solution = new LinkedHashMap<>();
        while (currentDay < maxNumberDays) {
            int fCurrentDay = currentDay;
            Map<String, List<Contributor>> sortedAvailable = sortedContributors.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> e.getValue()
                                    .stream()
                                    .filter(contributor -> occupiedUntil.get(contributor) <= fCurrentDay)
                                    .collect(Collectors.toList()))
                    );
            do {
                projectToWorkOn = null;
                for (Project project : projectsByScore) {
                    Optional<List<Contributor>> contributors = project.needsTheseContributors(sortedAvailable);
                    if (contributors.isPresent()) {
                        solution.put(project, contributors.get());
                        projectToWorkOn = project;
                        project.levelup(contributors.get());
                        contributors.get().forEach(contributor -> occupiedUntil.put(contributor, project.duration() + fCurrentDay));
                    }
                }
                if (projectToWorkOn != null) {
                    projectsByScore.remove(projectToWorkOn);
                }
            } while (projectToWorkOn != null);
            currentDay++;
        }
//        System.out.println(projectToAssignments);
        return new Solution(solution);
    }
}
