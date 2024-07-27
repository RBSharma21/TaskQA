package pageTests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.EdgeHomeObjects;

public class TC06 extends BaseTest{

	static EdgeHomeObjects ed;

	@Test
	public static void Test06() throws FileNotFoundException, IOException {
		
//		openBrowser();
		ed = new EdgeHomeObjects(driver);
		
		String URL  = excelDataReader("URL");
		
		ed.openURL(URL);
		ed.enterImproperEmailAndJoin(excelDataReader("ImProper mailId"), excelDataReader("Join Now unSuccess Message"));
		
	}	
	
}
