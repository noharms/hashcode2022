package total;

import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {

        RawInputData inputData = RawInputData.create();

        ProblemDescription problemDescription = ProblemDescription.from(inputData);

        Solution solution = new Solution(new LinkedHashMap<>());

        OutputWriter writer = new OutputWriter(solution);
        writer.write();

        System.out.println("Victory.");
    }
}
