package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class EdgeContactUsObjects {

WebDriver driver;
	
	public EdgeContactUsObjects(WebDriver Stepsdriver) {
		this.driver = Stepsdriver;
		PageFactory.initElements(driver, this);
	}
	
		@FindBy(name = "first_name")
		WebElement firstName;
		
		@FindBy(name = "last_name")
		WebElement lastName;
		
		@FindBy(name = "email")
		WebElement email;
		
		@FindBy(name = "contact")
		WebElement contactNum;
		
		@FindBy(xpath = "//div[@class=' css-1wy0on6']")
		WebElement countryDropDown;
		
		@FindBy(name = "describe")
		WebElement describe;
		
		@FindBy(xpath = "//button[contains(text(),'Submit')]")
		WebElement submit;
		
	public void fillDetails(String fName, String lName, String properEmail, String contact, String country, String description, String expectedText) throws InterruptedException {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		email.sendKeys(properEmail);
		contactNum.sendKeys(contact);
		
		Actions action = new Actions(driver);
		action.click(countryDropDown).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()='"+country+"']")).click();
		
		describe.sendKeys(description);
		submit.click();
		//success message
		
		String actualText = driver.findElement(By.xpath("//*[contains(text(),'The form was submitted successfully.')]")).getText();
		Assert.assertEquals(expectedText+" is not present on the page!!", expectedText, actualText);
	}
	
}
