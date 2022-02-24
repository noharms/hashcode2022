import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to hold in memory the RAW content of all input files that we need to consider.
 * Each input file is represented here as a {@code List<String>} which represents the lines in that file
 */
public class RawInputData {

    public final List<String> linesFile1;

    public RawInputData(List<String> linesFile1) {
        this.linesFile1 = linesFile1;
    }

    public static RawInputData create() {

        // file 1
        List<String> linesEnnoInputFile = readAllLines("enno_exampl_input.txt");

        // file 2
        // .. specify further files here ..
        // List<String> linesXYZ = readAllLines("xyz.txt");

        return new RawInputData(linesEnnoInputFile);
    }

    private static List<String> readAllLines(String fileName) {
        List<String> result = new ArrayList<>();
        InputStream inputStream = RawInputData.class.getResourceAsStream(fileName);
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
