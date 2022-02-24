package total;

import java.util.Map;

public record Contributor(String name, Map<String, Integer> skillLevel) {

    // a * nSkills
    public double heuristicValue() {
        int a = 1;
        double numerator = a * skillLevel.size();
        double denominator = 1;
        return numerator / denominator;
    }

}
