package utilities;
import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import testCase.BaseClass;
public class TestListener implements ITestListener {
    ExtentReports extent;
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    @Override
    public void onStart(ITestContext context) {
        extent = ExtentReportManager.getReport();
    }
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(
                result.getTestClass().getName() + "."
                + result.getMethod().getMethodName()
        );
        extentTest.set(test);
        String[] groups = result.getMethod().getGroups();
        if (groups.length > 0) {
            extentTest.get().assignCategory(groups);
        }
        extentTest.get().log(Status.INFO, "Test execution started");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,
                result.getMethod().getMethodName() + " passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL,
                result.getMethod().getMethodName() + " got failed");
        extentTest.get().log(Status.INFO,
                result.getThrowable());
        try {
            BaseClass base = (BaseClass) result.getInstance();
            String screenshotPath = base.captureScreen(
                    result.getMethod().getMethodName()
            );
            extentTest.get().fail(
                    "Failure Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(
                            screenshotPath
                    ).build()
            );
        } catch (Exception e) {
            extentTest.get().log(Status.WARNING,
                    "Screenshot could not be attached: " + e.getMessage());
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP,
                result.getMethod().getMethodName() + " got skipped");
        if (result.getThrowable() != null) {
            extentTest.get().log(Status.INFO, result.getThrowable());
        }
    }
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Extent Report generated successfully!");
    }
}