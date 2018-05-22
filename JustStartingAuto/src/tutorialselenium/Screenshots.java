package tutorialselenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	
	public static String takeScreenshot(WebDriver driver, String fileName,String Fldpath) throws IOException {
		fileName = fileName + ".png";
		String destination;
		if(!Fldpath.equals("")) {
			String directory = "D:\\Users\\goutham.p\\git\\JustStartingAuto\\Results\\"+Fldpath+"\\";
			File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(directory + fileName));
			 destination = directory + fileName;
		} else {
			String directory = "D:\\Users\\goutham.p\\git\\JustStartingAuto\\Results\\";
			File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(directory + fileName));
			 destination = directory + fileName;
		}
		return destination;
	}

	public static String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}