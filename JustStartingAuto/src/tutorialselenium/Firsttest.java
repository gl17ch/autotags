package tutorialselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Firsttest {

	public static void main(String[] args) {
		// http://chromedriver.storage.googleapis.com/index.html
		String baseURL = "http://www.google.com";
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "D:\\Goutham\\selenium webdrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get(baseURL);

	}

}
