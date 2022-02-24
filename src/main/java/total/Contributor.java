package total;

import java.util.Map;

public final class Contributor {

    public static double x = 1;
    public static double y = 1;

    private final String name;
    private final Map<String, Integer> skillLevel;

    public Contributor(String name, Map<String, Integer> skillLevel) {
        this.name = name;
        this.skillLevel = skillLevel;
    }

    public static double aContributor = 1;

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


    // a * nSkills
    public double heuristicValue() {
        int summedSkill = skillLevel.values().stream().mapToInt(i -> i).sum();
        double numerator = x * skillLevel.size() + y * summedSkill;
        double denominator = 1;
        return numerator / denominator;
    }

}
