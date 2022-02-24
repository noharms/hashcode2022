import java.util.List;

public record Project(String name, int duration, int score, int bestBefore, List<SkillLevel> requiredLevels) {
}
