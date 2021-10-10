import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HdlFileWriter {

    public static void write(File outputFile, Chip chip) throws IOException {
        FileWriter writer = new FileWriter(outputFile);
    }
}
