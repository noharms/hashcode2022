package total;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public record ProblemDescription(List<Project> projects, List<Contributor> contributors) {

    public static ProblemDescription from(RawInputData inputData) {

        List<String> lines = inputData.lines();

        // read header
        String[] piecesHeaderLine = lines.get(0).split(" ");
        int nContributors = Integer.parseInt(piecesHeaderLine[0]);
        int nProjects = Integer.parseInt(piecesHeaderLine[1]);

        // read contributors
        int lineCounter = 1;
        List<Contributor> contributors = new ArrayList<>();
        for (int i = 0; i < nContributors; i++) {
            String line = lines.get(lineCounter++);
            String[] pieces = line.split(" ");
            String contributorName = pieces[0];
            int nSkills = Integer.parseInt(pieces[1]);

            Map<String, Integer> skillToLevel = new HashMap<>();
            for (int j = 0; j < nSkills; j++) {
                String lineSkillWithLevel = lines.get(lineCounter++);
                String[] skillWithLevel = lineSkillWithLevel.split(" ");
                String skill = skillWithLevel[0];
                int level = Integer.parseInt(skillWithLevel[1]);

                skillToLevel.put(skill, level);
            }

            Contributor newContributor = new Contributor(contributorName, skillToLevel);
            contributors.add(newContributor);
        }

        // read projects
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < nProjects; i++) {
            String projectHeaderLine = lines.get(lineCounter++);

            String[] pieces = projectHeaderLine.split(" ");

            String projectName = pieces[0];
            int daysNeeded = Integer.parseInt(pieces[1]);
            int score = Integer.parseInt(pieces[2]);
            int maxDaysAllowed = Integer.parseInt(pieces[3]);
            int nRoles = Integer.parseInt(pieces[4]);

            List<SkillLevel> skillLevels = new ArrayList<>();
            for (int j = 0; j < nRoles; j++) {
                String roleLine = lines.get(lineCounter++);

                String[] skillWithLevel = roleLine.split(" ");
                String skill = skillWithLevel[0];
                int level = Integer.parseInt(skillWithLevel[1]);

                skillLevels.add(new SkillLevel(skill, level));
            }

            projects.add(new Project(projectName, daysNeeded, score, maxDaysAllowed, skillLevels));

        }

        return new ProblemDescription(projects, contributors);
    }
}
