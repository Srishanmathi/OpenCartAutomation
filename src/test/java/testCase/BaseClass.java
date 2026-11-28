
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
        p = new Properties();

        String configPath = System.getProperty("user.dir")
                + "/src/test/resources/config.properties";

        File configFile = new File(configPath);

        // Read local config file when available
        if (configFile.exists()) {
            try (FileReader fr = new FileReader(configFile)) {
                p.load(fr);
            }
        }

        // Read GitHub Actions environment variables
        setPropertyFromEnvironment("appURL", "APP_URL");
        setPropertyFromEnvironment("username", "TEST_USERNAME");
        setPropertyFromEnvironment("password", "TEST_PASSWORD");

        String appURL = p.getProperty("appURL");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());

        String source = driver.getPageSource();

        System.out.println("Page Source: " + source.substring(0,
                Math.min(1000, source.length())));

        if (appURL == null || appURL.trim().isEmpty()) {
            throw new RuntimeException("APP_URL is missing");
        }

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordManagerEnabled");

        if ("true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"))) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get(appURL);

// Wait for page loading
try {
    Thread.sleep(5000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}

// Capture page details
logger.info("Current URL: " + driver.getCurrentUrl());
logger.info("Page Title: " + driver.getTitle());
logger.info("Page Source: " + driver.getPageSource().substring(
        0, Math.min(driver.getPageSource().length(), 2000)));

// Capture screenshot
if ("true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"))) {
    File screenshot = new File("screenshots/GitHubPage.png");
    screenshot.getParentFile().mkdirs();

    File source = ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.FILE);

    try {
        java.nio.file.Files.copy(source.toPath(), screenshot.toPath(),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
        logger.error("Screenshot capture failed", e);
    }
}

logger.info("Browser launched successfully");
logger.info("Application URL opened");
    }

    private void setPropertyFromEnvironment(String propertyName, String envName) {

        String value = System.getenv(envName);

        if (value != null && !value.trim().isEmpty()) {
            p.setProperty(propertyName, value);
        }
    }

    public String captureScreen(String testName) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());

        File sourceFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File folder = new File(System.getProperty("user.dir") + "/screenshots");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String targetPath = folder.getAbsolutePath() + "/"
                + testName + "_" + timeStamp + ".png";

        File targetFile = new File(targetPath);

        java.nio.file.Files.copy(sourceFile.toPath(), targetFile.toPath());

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
