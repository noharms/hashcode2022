package total.solvers;

import total.ProblemDescription;
import total.Project;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleSolver extends Solver {
    public SimpleSolver(ProblemDescription problem) {
        super(problem);
    }

    @Override
    public void solve() {
        final List<Project> projectsByScore = problem.projects()
                .stream()
                .sorted(Comparator.comparing(Project::score).reversed())
                .collect(Collectors.toList());
        for (Project project : projectsByScore) {
            project.needsTheseContributors(problem.contributors());
        }
    }


}
