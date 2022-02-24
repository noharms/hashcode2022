package total;

import total.solvers.ComplexSolver;

public class Main {

    public static void main(String[] args) {

        RawInputData inputData = RawInputData.create();

        ProblemDescription problemDescription = ProblemDescription.from(inputData);


        Solution optimalSolution = null;
        double deltaA = 0.2;
        double deltaB = 0.2;
        double deltaC = 0.2;
        double deltaD = 0.2;
        int n1 = 4;
        int n2 = 4;
        int n3 = 4;
        int n4 = 4;
        for (int i = 0; i < n1; i++) {
            Project.a = Project.a + deltaA;
            for (int j = 0; j < n2; j++) {
                Project.b = Project.b + deltaB;
                for (int k = 0; k < n3; k++) {
                    Project.c = Project.c + deltaC;
                    for (int l = 0; l < n4; l++) {
                        Contributor.d = Contributor.d + deltaD;


                        Solution solution = new ComplexSolver(ProblemDescription.from(inputData)).solve();

                        if (optimalSolution == null || optimalSolution.computeScore() < solution.computeScore()) {
                            optimalSolution = solution;
                            System.out.println("New all time high ! yippie ! " + solution.computeScore());
                        }
                    }
                }
            }
        }

        OutputWriter writer = new OutputWriter(optimalSolution);
        writer.write();

        System.out.println("Victory.");
    }
}
