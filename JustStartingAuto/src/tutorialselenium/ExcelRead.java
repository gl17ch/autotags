package tutorialselenium;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ExcelRead {
	
//	 URL to get the binary - http://poi.apache.org/download.html
//	 Binary Name - poi-bin-3.11-beta2-20140822.zip
//	 Extract the binary
//	 Add all the jars from the location you extracted to the build path
//	 Also add all the jars from lib, do not add the jar file of log4j
//	 Also add all the jars from ooxml-lib
//	 Only works for Excel 2007+

	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Goutham\\selenium webdrivers\\chromedriver.exe");
		String baseURL;
		WebDriver driver;
		driver = new ChromeDriver();
		
		XSSFWorkbook ExcelWBook;
		XSSFSheet ExcelWSheet;
		XSSFCell Cell;
	
		// Location of the Excel file
		String path = "D:\\Users\\goutham.p\\eclipse-workspace\\JustStartingAuto\\bin\\tutorialselenium\\ExcelRead.xlsx";
		String sheetName = "Sheet1";
		
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			Cell = ExcelWSheet.getRow(0).getCell(0);
			String tagURL = Cell.getStringCellValue();
			Cell = ExcelWSheet.getRow(0).getCell(1);
			String ctaURL = Cell.getStringCellValue();
			ExcelWBook.close();
			baseURL = ctaURL;
			driver.get(baseURL);
			Thread.sleep(3000);
			String ActualctaURL = driver.getCurrentUrl();			
			Assert.assertEquals(tagURL, ActualctaURL);
			driver.close();
	}	

}