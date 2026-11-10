package testCase;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class BaseClass {
    public WebDriver driver;
    public Logger logger;
    public Properties p;
    @BeforeMethod
    public void browserlaunch() throws IOException {
        logger = LogManager.getLogger(this.getClass());
        FileReader fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
        p = new Properties();
        p.load(fr);
        fr.close();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordManagerEnabled");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(p.getProperty("appURL"));
        logger.info("Browser launched successfully");
        logger.info("Application URL opened");
    }
    public String captureScreen(String testName) throws IOException {String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File folder = new File(System.getProperty("user.dir") + "/screenshots");
    if (!folder.exists()) {
        folder.mkdirs();
    }
    String targetPath = folder.getAbsolutePath() + "/" + testName + "_" + timeStamp + ".png";
    File targetFile = new File(targetPath);
    java.nio.file.Files.copy(sourceFile.toPath(),targetFile.toPath());
    return targetPath;
}
    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }
}