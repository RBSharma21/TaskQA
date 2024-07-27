package pageTests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import pageObjects.EdgeContactUsObjects;
import pageObjects.EdgeHomeObjects;

public class TC02 extends BaseTest {

	static EdgeHomeObjects eh;
	static EdgeContactUsObjects ecus;

	@Test
	public static void Test02() throws FileNotFoundException, IOException, InterruptedException {
		
		eh = new EdgeHomeObjects(driver);
		ecus = new EdgeContactUsObjects(driver);
		String URL  = excelDataReader("URL");
		eh.openURL(URL);
		eh.openContactUS();
		ecus.fillDetails(excelDataReader("FirstName"), excelDataReader("LastName"), excelDataReader("Proper mailId"), excelDataReader("Contact"), excelDataReader("Country"), excelDataReader("Description"), excelDataReader("ContactUsMessage"));
		
	}

}
