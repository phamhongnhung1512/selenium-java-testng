package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextBox = By.xpath("//div//input[@name ='user_email']");
	By radioButton = By.xpath("//div//input[@id ='under_18']");
	By EduTextArea = By.cssSelector("textarea[id='edu']"); 
	By TextName = By.xpath("//h5[text()='Name: User5']");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_WebElement() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(driver.findElement(emailTextBox).isDisplayed());
		Assert.assertTrue(driver.findElement(radioButton).isDisplayed());
		Assert.assertTrue(driver.findElement(EduTextArea).isDisplayed());
		Assert.assertFalse(driver.findElement(TextName).isDisplayed());
		if(driver.findElement(TextName).isDisplayed()) {
			driver.findElement(emailTextBox).sendKeys("Automation Testing");
			System.out.println("Element is displayed");
		}
		{
			System.out.println("Element is not displayed");
		};
		if (driver.findElement(radioButton).isDisplayed()) {
			driver.findElement(radioButton).click();
			System.out.println("Element is displayed");
		}
		{	
			System.out.println("Element is not displayed");
		}		
		}

	@Test
	public void TC_03_WebElement() {
		}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
		}
}
