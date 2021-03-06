package total;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to hold in memory the RAW content of all input files that we need to consider.
 * Each input file is represented here as a {@code List<String>} which represents the lines in that file
 */
public record RawInputData(List<String> lines) {

    public static RawInputData create() {
//        List<String> lines = readAllLines("..//b_better_start_small.in.txt");
//        List<String> lines = readAllLines("..//b_better_start_small.in.txt");
//        List<String> lines = readAllLines("..//c_collaboration.in.txt");
//        List<String> lines = readAllLines("..//d_dense_schedule.in.txt");
        List<String> lines = readAllLines("..//e_exceptional_skills.in.txt");
//        List<String> lines = readAllLines("..//f_find_great_mentors.in.txt");
        return new RawInputData(lines);
    }

    private static List<String> readAllLines(String fileName) {
        List<String> result = new ArrayList<>();
        InputStream inputStream = Main.class.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        } else {
            try (Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    result.add(line);
                }
            }
        }
        return result;
    }

}
