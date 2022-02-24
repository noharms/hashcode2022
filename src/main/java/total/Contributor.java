package total;

import java.util.Map;

public final class Contributor {
    private final String name;
    private final Map<String, Integer> skillLevel;

    public Contributor(String name, Map<String, Integer> skillLevel) {
        this.name = name;
        this.skillLevel = skillLevel;
    }

    public static double aContributor = 1;

    // a * nSkills
    public double heuristicValue() {
        double numerator = aContributor * skillLevel.size();
        double denominator = 1;
        return numerator / denominator;
    }

    public String name() {
        return name;
    }

    public Map<String, Integer> skillLevel() {
        return skillLevel;
    }

    @Override
    public String toString() {
        return "Contributor[" +
                "name=" + name + ", " +
                "skillLevel=" + skillLevel + ']';
    }


}
