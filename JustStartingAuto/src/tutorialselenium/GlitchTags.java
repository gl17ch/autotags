package tutorialselenium;

import java.io.File;
import java.io.FileInputStream;
import tutorialselenium.Screenshots;
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

	public static String DRIVER_LOCATION, EXCEL_LOCATION, REPORT_LOCATION;
	public static String BASE_DIR = "=";
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String baseURL;
		WebDriver driver;
		ExtentReports report;
		ExtentTest test;

		EXCEL_LOCATION = new File(BASE_DIR).getAbsolutePath().replace("=", "ExcelFile\\ExcelRead.xls"); 
		System.out.println("--------->"+EXCEL_LOCATION);
		DRIVER_LOCATION = new File(BASE_DIR).getAbsolutePath().replace("=", "driver\\chromedriver.exe"); 
		System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
		
		driver = new ChromeDriver();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		
		XSSFWorkbook ExcelWBook;
		XSSFSheet ExcelWSheet;
		XSSFCell Cell;
		XSSFCell PlacementCell;
		REPORT_LOCATION = new File(BASE_DIR).getAbsolutePath().replace("=", "Results\\\\gl17chtagsReport.html"); 
		report = new ExtentReports(REPORT_LOCATION);
		// creating tests
		test = report.startTest("gl17ch tags started");
		
		String path = EXCEL_LOCATION;
		String sheetName = "Sheet1";  
		String ipath;
		String imagepath;
		String ActualURL;
		FileInputStream ExcelFile = new FileInputStream(path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);

		int totalRow = ExcelWSheet.getPhysicalNumberOfRows();
		
		//Get the redirection URL
		String[] value = new String[10];
		for (int j=1;j<=1;j++) {
			//Cell is CTA URL
			Cell = ExcelWSheet.getRow(j).getCell(2);
				String celldata = Cell.getStringCellValue().toString();
				value[j]= celldata;
				int sno = j+1;
				System.out.println("The value of cta url given is:" + sno + ". " + value[j]);
				
			}
		//check the given URL and compare with redirection URL
		
		for (int i=1;i<=totalRow-1;i++) {
			if(ExcelWSheet.getRow(i)==null) continue;
			Cell = ExcelWSheet.getRow(i).getCell(1);
			String CellData = Cell.getStringCellValue();
			PlacementCell = ExcelWSheet.getRow(i).getCell(0);
			String PlacementID = PlacementCell.getStringCellValue();
//				int j = i+1;
				System.out.println(i + ". " + PlacementID);
			baseURL = CellData;
			driver.manage().window().maximize();
			System.out.println(i + "URL ::>>" + baseURL);
				driver.get(baseURL);
				ipath = Screenshots.takeScreenshot(driver, PlacementID, "all");
				imagepath = test.addScreenCapture(ipath);
				test.log(LogStatus.INFO, "URL given "+ baseURL);
				Thread.sleep(1000);
				ActualURL = driver.getCurrentUrl();
				System.out.println("The final URL is " + ActualURL);
				Row row = ExcelWSheet.getRow(i);
				if(Arrays.asList(value).contains(ActualURL)) {
					System.out.println("The urls match");
					
				row.createCell(3).setCellValue("Pass");
				ipath = Screenshots.takeScreenshot(driver, PlacementID, "pass");
				imagepath = test.addScreenCapture(ipath);
				test.log(LogStatus.PASS,+ i + " . " + PlacementID + " The URLs matched "+ ActualURL + imagepath);
				}
				else {
					System.out.println("Both the urls are different");

					row.createCell(3).setCellValue("Fail");
					ipath = Screenshots.takeScreenshot(driver, PlacementID, "fail");
					imagepath = test.addScreenCapture(ipath);
					test.log(LogStatus.FAIL,+ i + ". " + PlacementID + " The URLs doesn't match "+ ActualURL + imagepath);
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
