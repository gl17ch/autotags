package tutorialselenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcelTags {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String baseURL;
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "D:\\Goutham\\selenium webdrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		
		XSSFWorkbook ExcelWBook;
		XSSFSheet ExcelWSheet;
		XSSFCell Cell;
		
		String path = "D:\\Users\\goutham.p\\eclipse-workspace\\JustStartingAuto\\bin\\ExcelRead.xlsx";
		String sheetName = "Sheet1";
		
		
		FileInputStream ExcelFile = new FileInputStream(path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		int totalRow = ExcelWSheet.getPhysicalNumberOfRows();
/*		for (int i=0;i<=totalRow-1;i++) {
				Cell = ExcelWSheet.getRow(i).getCell(0);

				String CellData = Cell.getStringCellValue();
				
				int j = i+1;
				System.out.println(j + ". " + CellData);
			baseURL = CellData;
				driver.get(baseURL);
				Thread.sleep(1000);
				String ActualURL = driver.getCurrentUrl();
				System.out.println("The final URL is " +ActualURL);
				
				
		}*/
		
	//To compare the CTA URL
		String[] value= {};
		for (int j=0;j<3;j++) {
			Cell = ExcelWSheet.getRow(j).getCell(1);
				String test = Cell.getStringCellValue();
//				value = new String[j];
				value[j]= test;
				System.out.println("The value of cta url given is:" + j + ". " + value[j]);
//				System.out.println(test);
			}

		System.out.println();
		driver.close();
		Date date1 = new Date();
		System.out.println(dateFormat.format(date1));
		System.out.println(totalRow);
		ExcelWBook.close();
		
	}

}
