import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {

    public final Solution solution;

    public OutputWriter(Solution solution) {
        this.solution = solution;
    }

    public static OutputWriter from(Solution solution) {
        return new OutputWriter(solution);
    }

    public void write() {
        String data = "our data \n here ";
        String outputFileName = "output1.txt";
        try (FileWriter fw = new FileWriter(outputFileName)) {
            fw.write(data);
        } catch (IOException e) {
            throw new IllegalStateException("Output did not work.");
        }
    }
}
