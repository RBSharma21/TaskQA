package pageObjects;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EdgeHomeObjects{

	WebDriver driver;
	
	public EdgeHomeObjects(WebDriver Stepsdriver) {
		this.driver = Stepsdriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='loginMenus d-flex align-items-center justify-content-between']")
	WebElement navigationMenu;
	
	@FindBy(xpath = "//a[contains(text(),'EDGE-Q')]")
	WebElement edgeQOption;
	
	@FindBy(xpath = "(//a[contains(text(),'Our Story')])[1]")
	WebElement ourStoryOption;
			
	@FindBy(xpath = "(//a[contains(text(),'Our Solution')])[1]")
	WebElement ourSolutionOption;
	
	@FindBy(xpath = "(//a[contains(text(),'Our Team')])[1]")
	WebElement ourTeamOption;
	
	@FindBy(xpath = "(//a[contains(text(),'Partners')])[1]")
	WebElement partnersOption;
	
	@FindBy(xpath = "(//a[contains(text(),'Accelerator Program')])[1]")
	WebElement acceleratorProgramOption;
	
	@FindBy(xpath = "//a[contains(text(),'Contact us')]")
	WebElement contactUsOption;
	
	@FindBy(xpath = "//span[@class='carousel-control-prev-icon']")
	WebElement corouselPrev;	
	
	@FindBy(xpath = "//span[@class='carousel-control-next-icon']")
	WebElement corouselNext;	
	
	@FindBy(xpath = "//a[contains(text(),'INVEST NOW')]")
	WebElement investNowButton;	
	
	@FindBy(id = "iframewin")
	WebElement joinNowFrame;	
	
	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement emailTB;	
	
	@FindBy(xpath = "//input[@value='Join Now']")
	WebElement joinNowButton;
	
	@FindBy(xpath = "//a[@class='carousel-control-prev']/span[1]")
	WebElement leftCorousalButton;
	
	@FindBy(xpath = "//a[@class='carousel-control-next']/span[1]")
	WebElement rightCorousalButton;
	
	//Error message
	//*[contains(text(),'Please correct the marked field(s) below.')]
	
	
	public void openURL(String URL) {
		driver.navigate().to(URL);
		
	}
	
	public void openNavigationMenu() {
		navigationMenu.click();
	}

	public void OpenEachMenuOption() {
		
		String parentWindow = driver.getWindowHandle();
		if(ourStoryOption.isDisplayed()) {
			edgeQOption.click();
			
		}else {
			driver.switchTo().window(parentWindow);
			driver.navigate().refresh();
			navigationMenu.click();
			edgeQOption.click();
		}
		
		if(ourStoryOption.isDisplayed()) {
			ourStoryOption.click();
			
		}else {
			driver.switchTo().window(parentWindow);
			driver.navigate().refresh();
			navigationMenu.click();
			ourStoryOption.click();
		}
		
		if(ourSolutionOption.isDisplayed()) {
			ourSolutionOption.click();
			
		}else {
			driver.switchTo().window(parentWindow);
			driver.navigate().refresh();
			navigationMenu.click();
			ourSolutionOption.click();
		}
		
		if(ourTeamOption.isDisplayed()) {
			ourTeamOption.click();
			
		}else {
			driver.switchTo().window(parentWindow);
			driver.navigate().refresh();
			navigationMenu.click();
			ourTeamOption.click();
		}
		
		if(partnersOption.isDisplayed()) {
			partnersOption.click();

		}else {
			driver.switchTo().window(parentWindow);
			driver.navigate().refresh();
			navigationMenu.click();
			partnersOption.click();
		}
		
		if(acceleratorProgramOption.isDisplayed()) {
			acceleratorProgramOption.click();
			
		}else {
			driver.switchTo().window(parentWindow);
			driver.navigate().refresh();
			navigationMenu.click();
			acceleratorProgramOption.click();
		}
		
		if(contactUsOption.isDisplayed()) {
			contactUsOption.click();
			
		}else {
			driver.switchTo().window(parentWindow);
			driver.navigate().refresh();
			navigationMenu.click();
			contactUsOption.click();
		}
		
	}

	public void openContactUS() {
		navigationMenu.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(contactUsOption));
		contactUsOption.click();
		
		
	}

	public void clickLeftCorousalButton() {
		Assert.assertTrue(leftCorousalButton.isDisplayed());
		leftCorousalButton.click();
	}

	public void clickRightCorousalButton() {
		Assert.assertTrue(rightCorousalButton.isDisplayed());
		rightCorousalButton.click();
	}

	public void clickInvestNow() throws InterruptedException {
		
		Thread.sleep(2000);
		
		Actions action = new Actions(driver);
		action.moveToElement(investNowButton).perform();
		
		Assert.assertTrue("Invest Now button is Not present", investNowButton.isDisplayed());
		
		action.click(investNowButton).perform();
		
	}

	public void enterEmailAndJoin(String mailId, String expectedText) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.switchTo().frame(joinNowFrame);
		emailTB.sendKeys(mailId);
		
		wait.until(ExpectedConditions.elementToBeClickable(joinNowButton));
		
		Actions action = new Actions(driver);
		
		action.moveToElement(joinNowButton).perform();
		action.click(joinNowButton).perform();
		
		String parent = driver.getWindowHandle();
		
		Set<String> wins = driver.getWindowHandles();
		
		Iterator<String> I1 = wins.iterator();
		String[] childWins = new String[wins.size()];
		int i = 0;
		while(I1.hasNext()) {
			childWins[i] = I1.next();
			
			driver.switchTo().window(childWins[i]);
			i++;
		}
		String actualText = driver.findElement(By.xpath("//div[@changeid='THANKS_MSG'][2]")).getText();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@changeid='THANKS_MSG'][2]"))));
		
		Assert.assertEquals("The text on the page does not match to"+ expectedText, expectedText, actualText);

		
		driver.switchTo().window(parent);
	}

	public void enterImproperEmailAndJoin(String imProperMailId, String expectedText) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.switchTo().frame(joinNowFrame);
		emailTB.sendKeys(imProperMailId);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", joinNowButton);
		wait.until(ExpectedConditions.elementToBeClickable(joinNowButton));
		
		Actions action = new Actions(driver);
		
		action.moveToElement(joinNowButton).perform();
		action.click(joinNowButton).perform();
		
		
		String actualText = driver.findElement(By.xpath("//*[contains(text(),'Please correct the marked field(s) below.')]")).getText();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'Please correct the marked field(s) below.')]"))));
		
		Assert.assertEquals("The text on the page does not match to"+ expectedText, expectedText, actualText);

	}
	
}