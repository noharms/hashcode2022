package total;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public record OutputWriter(Solution solution) {

    public void write() {
        StringBuilder outputBuilder = new StringBuilder();

        // number of projects planned
        int nProjectsPlanned = solution.projectToAssignments().size();
        outputBuilder.append(nProjectsPlanned);
        outputBuilder.append("\n");

        // loop over projects
        for (var entry : solution.projectToAssignments().entrySet()) {
            Project project = entry.getKey();
            List<Contributor> assignments = entry.getValue();

            outputBuilder.append(project.name());
            outputBuilder.append("\n");

            outputBuilder.append(assignments.stream().map(Contributor::name).collect(Collectors.joining(" ")));
            outputBuilder.append("\n");
        }

        String outputFileName = "output1.txt";
        try (FileWriter fw = new FileWriter(outputFileName)) {
            fw.write(outputBuilder.toString());
        } catch (IOException e) {
            throw new IllegalStateException("Output did not work.");
        }
    }
}
