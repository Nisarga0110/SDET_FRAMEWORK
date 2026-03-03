package utils;

import core.FrameworkCore;  
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final String SCREENSHOT_FOLDER = "screenshots/";

    public static String captureScreenshot(String testName) {

        WebDriver driver = FrameworkCore.getDriver();

        if (driver == null) {
            throw new RuntimeException("Driver is null. Cannot take screenshot.");
        }

        // Create folder if not exists
        File directory = new File(SCREENSHOT_FOLDER);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Timestamp for unique name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath =
                SCREENSHOT_FOLDER + testName + "_" + timestamp + ".png";

        // Capture screenshot
        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(source.toPath(),
                       Paths.get(screenshotPath),
                       StandardCopyOption.REPLACE_EXISTING); // safer
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}