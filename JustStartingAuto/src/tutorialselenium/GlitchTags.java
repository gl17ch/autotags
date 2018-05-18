package tutorialselenium;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GlitchTags {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String baseURL;
		WebDriver driver;
		ExtentReports report;
		ExtentTest test;

		 
		System.setProperty("webdriver.chrome.driver", "D:\\Goutham\\selenium webdrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		
		XSSFWorkbook ExcelWBook;
		XSSFSheet ExcelWSheet;
		XSSFCell Cell;
		report = new ExtentReports("D:\\Users\\goutham.p\\git\\JustStartingAuto\\outputFile\\gl17chtagsReport.html");
		// creating tests
		test = report.startTest("gl17ch tags started");
		
		String path = "D:\\Users\\goutham.p\\eclipse-workspace\\JustStartingAuto\\bin\\ExcelRead.xlsx";
		String sheetName = "Sheet1";  
		
		FileInputStream ExcelFile = new FileInputStream(path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);

		int totalRow = ExcelWSheet.getPhysicalNumberOfRows();
		
		//Get the redirection URL
		String[] value = new String[10];
		for (int j=0;j<=1;j++) {
			Cell = ExcelWSheet.getRow(j).getCell(1);
				String celldata = Cell.getStringCellValue().toString();
				value[j]= celldata;
				int sno = j+1;
				System.out.println("The value of cta url given is:" + sno + ". " + value[j]);
				
			}
		//check the given URL and compare with redirection URL
		
		for (int i=0;i<=totalRow-1;i++) {
			if(ExcelWSheet.getRow(i)==null) continue;
			Cell = ExcelWSheet.getRow(i).getCell(0);

				String CellData = Cell.getStringCellValue();
				
				int j = i+1;
				System.out.println(j + ". " + CellData);
			baseURL = CellData;
				driver.get(baseURL);
				test.log(LogStatus.INFO, "URL given");
				Thread.sleep(1000);
				String ActualURL = driver.getCurrentUrl();
				System.out.println("The final URL is " +ActualURL);
				Row row = ExcelWSheet.getRow(i);
				if(Arrays.asList(value).contains(ActualURL)) {
					System.out.println("The urls match");
					test.log(LogStatus.PASS, "The URLs matched");
				row.createCell(2).setCellValue("Pass");
				}
				else {
					System.out.println("Both the urls are different");
					test.log(LogStatus.FAIL, "Both the urls are different");
					row.createCell(2).setCellValue("Fail");
				}

				
		}
		
		System.out.println();
		driver.close();
		Date date1 = new Date();
		System.out.println(dateFormat.format(date1));
		System.out.println(totalRow);
		FileOutputStream fileOut = new FileOutputStream(path);
		ExcelWBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		ExcelWBook.close();
		driver.quit();
		report.endTest(test);
		report.flush();
	}

}
