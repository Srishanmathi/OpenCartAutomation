package utilities;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentReportManager {
    public static ExtentReports extent;
    public static synchronized ExtentReports getReport() {
        if (extent == null) {
            String reportFolder = System.getProperty("user.dir") + "/test-output";
            String reportPath = reportFolder + "/ExtentReport.html";
            File folder = new File(reportFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            try {
                Files.deleteIfExists(Path.of(reportPath));
            } catch (IOException e) {
                throw new RuntimeException(
                        "Unable to delete the previous Extent Report", e);
            }
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("OpenCart Automation Report");
            reporter.config().setReportName("OpenCart Functional Testing");
            reporter.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Application", "OpenCart");
            extent.setSystemInfo("Automation Tool", "Selenium");
            extent.setSystemInfo("Testing Framework", "TestNG");
            extent.setSystemInfo("Language", "Java");
            extent.setSystemInfo("Browser", "Chrome");
            System.out.println("Extent Report Path: " + reportPath);
        }
        return extent;
    }
}