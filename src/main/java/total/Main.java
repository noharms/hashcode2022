package total;

import total.solvers.SimpleSolver;

public class Main {

    public static void main(String[] args) {

        RawInputData inputData = RawInputData.create();

        ProblemDescription problemDescription = ProblemDescription.from(inputData);

        Solution solution = new SimpleSolver(problemDescription).solve();

        OutputWriter writer = new OutputWriter(solution);
        writer.write();

        System.out.println("Victory.");
    }
}
