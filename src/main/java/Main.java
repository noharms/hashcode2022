public class Main {

    public static void main(String[] args) {

        RawInputData inputData = RawInputData.create();

        ProblemDescription problemDescription = ProblemDescription.from(inputData);

        Solution solution = Solution.from(problemDescription);

        OutputWriter writer = OutputWriter.from(solution);
        writer.write();

        System.out.println("Victory.");
    }
}
