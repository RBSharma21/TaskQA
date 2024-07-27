package pageTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	static int datacn = 0;
	static int datarn = 0;

	// This method reads data from config.properties file 
	public static String configMethod(String prop) throws FileNotFoundException, IOException {
		
		Properties prop1 = new Properties();
		prop1.load(new FileInputStream("config.properties"));
		return prop1.getProperty(prop);
	}
	
	// This methos opens Browser for automating the application
	@BeforeTest
	public static void openBrowser() throws FileNotFoundException, IOException {
		String browser = BaseTest.configMethod("browser");
		if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");	
			driver = new EdgeDriver(options);
		
		}else if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");	
			driver = new ChromeDriver(options);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		
	
	}	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
	
	static String excelDataReader(String header) throws FileNotFoundException, IOException {
		
		String ExcelFile = BaseTest.configMethod("EXCELFILE");
		String SheetName = BaseTest.configMethod("SheetName");
		System.out.println("Excel File = "+ ExcelFile+"\nSheetName ="+SheetName);
		 String ValueToReturn = "";
		 
		 String projectPath = System.getProperty("user.dir");
			File file= new File(projectPath+"\\src\\test\\resources\\TestData\\"+ExcelFile);
		FileInputStream fis= new FileInputStream(file);
		
		XSSFWorkbook wb;
		wb =new XSSFWorkbook(fis);
		
		XSSFSheet sheet= wb.getSheet(SheetName);
		try {
			
			
		Iterator<Row> ri= sheet.iterator();
		
		while(ri.hasNext()){
			Row row= ri.next();
			Iterator<Cell> ci= row.iterator();
			
			while(ci.hasNext()){
				
				Cell cell= ci.next();
					if(cell.getStringCellValue().equalsIgnoreCase(header)) {
						datacn = cell.getColumnIndex();
						datarn = row.getRowNum()+1;
						System.out.println("Header "+header+" Found at Row: "+datarn+" and cell: "+datacn);
					}
				if(datarn==row.getRowNum() && datacn==cell.getColumnIndex()) {
						ValueToReturn = cell.getStringCellValue();
						System.out.println("Found: "+ValueToReturn);
				}
			}
		}
		
		}finally {
			wb.close();
			fis.close();
		}
		return ValueToReturn;
	}
	
}
