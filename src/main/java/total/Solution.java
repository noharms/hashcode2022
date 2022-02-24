package total;

import java.util.LinkedHashMap;
import java.util.List;

public record Solution(LinkedHashMap<Project, List<Contributor>> projectToAssignments) {

}
