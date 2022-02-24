package total;

import java.util.Map;

public record Contributor(String name, Map<String, Integer> skillLevel) {

    public static double aContributor = 1;

    // a * nSkills
    public double heuristicValue() {
        double numerator = aContributor * skillLevel.size();
        double denominator = 1;
        return numerator / denominator;
    }

}
