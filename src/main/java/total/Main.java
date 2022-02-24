package total;

import total.solvers.SimpleSolver;

public class Main {

    public static void main(String[] args) {

        RawInputData inputData = RawInputData.create();

        ProblemDescription problemDescription = ProblemDescription.from(inputData);

        Solution solution = new SimpleSolver(problemDescription).solve();

        double deltaA = 0.2;
        double deltaB = 0.2;
        double deltaC = 0.2;
        double deltaD = 0.2;
        int n1 = 10;
        int n2 = 10;
        int n3 = 10;
        int n4 = 10;
        for (int i = 0; i < n1; i++) {
            Project.a = Project.a + deltaA;
            for (int j = 0; j < n2; j++) {
                Project.b = Project.b + deltaB;
                for (int k = 0; k < n3; k++) {
                    Project.c = Project.c + deltaC;
                    for (int l = 0; l < n4; l++) {
                        Contributor.d = Contributor.d + deltaD;
                    }
                }
            }
        }

        System.out.println(solution.computeScore());
        OutputWriter writer = new OutputWriter(solution);
        writer.write();

        System.out.println("Victory.");
    }
}
