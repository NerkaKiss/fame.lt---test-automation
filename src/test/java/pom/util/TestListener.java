package pom.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result.id(), result.getName());
    }

    private void takeScreenshot(String resultId, String resultTestName) {
        if (Driver.getDriver() == null) return;
        if (Driver.getDriver() instanceof TakesScreenshot screenshot) {
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formatedDateTime = LocalDateTime.now().format(formatter);

            String destFileName = "./screenshots/%s_%s_%s_screenshot.png"
                    .formatted(formatedDateTime, resultTestName, resultId);

            File destFile = new File(destFileName);
            try {
                FileUtils.copyFile(srcFile, destFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //    @Override
//    public void onStart(ITestContext context) {
//        System.out.println("ITestListener onStart");
//        System.out.println(context.getName());
//        System.out.println(context.getHost());
//        System.out.println("-".repeat(80));
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        System.out.println("ITestListener onTestStart");
//        System.out.println(result.id());
//        System.out.println(result.getName());
//        System.out.println(result.getHost());
//        System.out.println(result.getStatus());
//        System.out.println("-".repeat(80));
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        System.out.println("ITestListener onTestSuccess");
//        System.out.println(result.id());
//        System.out.println(result.getName());
//        System.out.println(result.getHost());
//        System.out.println(result.getStatus());
//        System.out.println("-".repeat(80));
//    }
}
