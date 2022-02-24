import java.util.EnumMap;

public record Contributor(String name, EnumMap<Skill, Integer> skillLevel) {
}
