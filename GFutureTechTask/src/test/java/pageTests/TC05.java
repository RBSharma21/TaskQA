package pageTests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.EdgeHomeObjects;

public class TC05 extends BaseTest{

	static EdgeHomeObjects ed;

	@Test
	public static void Test05() throws FileNotFoundException, IOException {
		
		
		ed = new EdgeHomeObjects(driver);
		
		String URL  = excelDataReader("URL");
		ed.openURL(URL);
		ed.enterEmailAndJoin(excelDataReader("Proper mailId"), excelDataReader("Join Now Success Message"));
		
	}
}
