package selenium.automation.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SnapShotUtil {
	public static void takeSnapShot(WebDriver driver, String outputPath) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		File dest = new File(outputPath);
		FileUtils.copyFile(src, dest);
	}
}
