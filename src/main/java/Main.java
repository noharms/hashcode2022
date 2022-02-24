public class Main {

    public static void main(String[] args) {

        RawInputData inputData = RawInputData.create();

        Solution solution = Solution.from(inputData);

        OutputWriter writer = OutputWriter.from(solution);
        writer.write();

        System.out.println("Victory.");
    }
}
