package total;

import total.solvers.ComplexSolver;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        RawInputData inputData = RawInputData.create();


        Solution optimalSolution = null;
        double deltaA = 100;
        double deltaB = 100;
        double deltaC = 100;
        double deltaD = 100;
        int n1 = 4;
        int n2 = 4;
        int n3 = 4;
        int n4 = 4;
        int counter = 0;
        while (counter < 1000) {
            counter++;
            Project.a = new Random().nextInt(0, 100000);
            Project.b = new Random().nextInt(0, 100000);
            Project.c = new Random().nextInt(0, 100000);
            Project.d = new Random().nextInt(0, 100000);
            Contributor.x = new Random().nextInt(0, 100000);
            Contributor.y = new Random().nextInt(0, 100000);
            Solution solution = new ComplexSolver(ProblemDescription.from(inputData)).solve();
            System.out.println(solution.computeScore());

            if (optimalSolution == null || optimalSolution.computeScore() < solution.computeScore()) {
                optimalSolution = solution;
                System.out.println("New all time high ! yippie ! " + solution.computeScore());
            }
        }


        OutputWriter writer = new OutputWriter(optimalSolution);
        writer.write();

        System.out.println("Victory.");
    }
}
