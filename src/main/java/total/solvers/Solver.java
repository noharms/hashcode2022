package total.solvers;

import total.ProblemDescription;
import total.Solution;

public abstract class Solver {
    final ProblemDescription problem;

    public Solver(ProblemDescription problem) {
        this.problem = problem;
    }

    abstract public Solution solve();
}
