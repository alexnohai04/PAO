package Services;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static final String FILE_PATH = "audit.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logAction(String action) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             PrintWriter pw = new PrintWriter(fw)) {

            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(formatter);
            pw.printf("%s,%s%n", timestamp, action);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
