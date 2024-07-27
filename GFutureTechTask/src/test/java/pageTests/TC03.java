package pageTests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.EdgeHomeObjects;

public class TC03 extends BaseTest{

	static EdgeHomeObjects ed;

	@Test
	public static void Test03() throws FileNotFoundException, IOException {
		
		ed = new EdgeHomeObjects(driver);
		
		String URL  = excelDataReader("URL");
		
		ed.openURL(URL);
		ed.clickLeftCorousalButton();
		ed.clickRightCorousalButton();
		
	}
}
