package total.solvers;

import total.ProblemDescription;

public abstract class Solver {
    final ProblemDescription problem;

    public Solver(ProblemDescription problem) {
        this.problem = problem;
    }

    abstract public void solve();
}
