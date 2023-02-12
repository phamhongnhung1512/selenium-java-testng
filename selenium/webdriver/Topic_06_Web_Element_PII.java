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
	By under18radioButton = By.xpath("//div//input[@id ='under_18']");
	By eduTextArea = By.cssSelector("textarea#edu"); 
	By user5TextName = By.xpath("//h5[text()='Name: User5']");
	By job1TextBox = By.xpath("//select [@id='job1']");
	By job2DropDownList = By.xpath("//select [@id='job2']");
	By deveplopmentCheckBox = By.xpath("//input[@type='checkbox']");
	By slider01 = By.xpath("//input[@name=\"slider-1\"]");
	By passwordTextBox = By.id("disable_password") ;
	By disableRadioButton = By.cssSelector("input#radio-disabled");
	By bioTextArea = By.cssSelector("textarea#bio");
	By jobRole3TextArea = By.name("user_job3");
	By interestsDisabledCheckBox = By.id("check-disbaled");
	By slider02 = By.cssSelector("input#slider-2");
	By javaLanguagesCheckbox = By.xpath("//input[@name=\"java\"]");
	By emailInputTextbox = By.xpath("//input[@id ='email']");
	By passwordTextbox = By.xpath("//input[@id ='new_password']");
	By lowercasecharCompleted = By.xpath("//li[@class=\"lowercase-char completed\"]");
	By lowercasecharUncompleted = By.xpath("//li[@class=\"lowercase-char not-completed\"]");
	By uppercasecharCompleted = By.xpath("//li[@class='uppercase-char completed']");
	By uppercasecharUncompleted = By.xpath("//li[@class='uppercase-char not-completed']");
	By numbercharCompleted = By.xpath("//li[@class='number-char completed']");
	By numbercharUncompleted = By.xpath("//li[@class='number-char not-completed']");
	By specialcharCompleted = By.xpath("//li[@class ='special-char completed']");
	By specialcharUncompleted = By.xpath("//li[@class ='special-char not-completed']");	
	By eightcharCompleted = By.xpath("//li[@class ='8-char completed']");
	By eightcharUncompleted = By.xpath("//li[@class ='8-char not-completed']");

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_CheckDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(driver.findElement(emailTextBox).isDisplayed());
		Assert.assertTrue(driver.findElement(under18radioButton).isDisplayed());
		Assert.assertTrue(driver.findElement(eduTextArea).isDisplayed());
		Assert.assertFalse(driver.findElement(user5TextName).isDisplayed());
		if(driver.findElement(emailTextBox).isDisplayed()) {
			driver.findElement(emailTextBox).sendKeys("Automation Testing");
			System.out.println("Element is displayed");
		}
		{
			System.out.println("Element is not displayed");
		};
		if (driver.findElement(under18radioButton).isDisplayed()) {
			driver.findElement(under18radioButton).click();
			System.out.println("Element is displayed");
		}
		{	
			System.out.println("Element is not displayed");
		}		
	}
	//@Test
	public void TC_02_CheckEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//check enabled
		Assert.assertTrue(driver.findElement(emailTextBox).isEnabled());
		Assert.assertTrue(driver.findElement(under18radioButton).isEnabled());
		Assert.assertTrue(driver.findElement(eduTextArea).isEnabled());
		Assert.assertTrue(driver.findElement(job1TextBox).isEnabled());
		Assert.assertTrue(driver.findElement(job2DropDownList).isEnabled());
		Assert.assertTrue(driver.findElement(deveplopmentCheckBox).isEnabled());
		Assert.assertTrue(driver.findElement(slider01).isEnabled());
		//check disabled
		Assert.assertFalse(driver.findElement(passwordTextBox).isEnabled());
		Assert.assertFalse(driver.findElement(disableRadioButton).isEnabled());
		Assert.assertFalse(driver.findElement(bioTextArea).isEnabled());
		Assert.assertFalse(driver.findElement(jobRole3TextArea).isEnabled());
		Assert.assertFalse(driver.findElement(interestsDisabledCheckBox).isEnabled());
		Assert.assertFalse(driver.findElement(slider02).isEnabled());
		//print
		if(driver.findElement(passwordTextBox).isEnabled()) {
			System.out.println("Element is enabled");
		}
		{
			System.out.println("Element is disenabled");
		};
	}
	
	//@Test
	public void TC_03_Checkselected() {
		//step 1: open url
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//step 2: click age radio button, java language checkbox
		driver.findElement(under18radioButton).click();
		driver.findElement(javaLanguagesCheckbox).click();
		//step 3: check selected 
		Assert.assertTrue(driver.findElement(under18radioButton).isSelected());
		Assert.assertTrue(driver.findElement(javaLanguagesCheckbox).isSelected());
		//step 4: click bo chon 
		driver.findElement(javaLanguagesCheckbox).click();
		//step 5: check selected
		Assert.assertFalse(driver.findElement(javaLanguagesCheckbox).isSelected());
		//print
		if(driver.findElement(under18radioButton).isSelected()) {
			System.out.println("Element is selected");
		}
		{
			System.out.println("Element is de-selected");
		};
		if(driver.findElement(javaLanguagesCheckbox).isSelected()) {
			System.out.println("Element is selected");
		}
		{
			System.out.println("Element is de-selected");
		};
	}
	
	@Test
	public void TC_04_Register_function() {
		//step 1: open url
		driver.get("https://login.mailchimp.com/signup/");
		//input email hop le
		driver.findElement(emailInputTextbox).sendKeys("nhungpham@gmail.com");
		//input only lower case characters password 
		driver.findElement(passwordTextbox).sendKeys("abc");
		//verify password only lower case characters
		Assert.assertTrue(driver.findElement(lowercasecharCompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(uppercasecharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(numbercharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(specialcharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(eightcharUncompleted).isDisplayed());
		//input only upper case characters password 
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		//verify password only upper case characters
		Assert.assertTrue(driver.findElement(lowercasecharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(uppercasecharCompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(numbercharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(specialcharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(eightcharUncompleted).isDisplayed());		
		//input only number characters password 
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("876");
		//verify password only number characters
		Assert.assertTrue(driver.findElement(lowercasecharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(uppercasecharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(numbercharCompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(specialcharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(eightcharUncompleted).isDisplayed());			
		//input only special characters password 
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("@#!*");
		//verify password only special characters
		Assert.assertTrue(driver.findElement(lowercasecharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(uppercasecharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(numbercharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(specialcharCompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(eightcharUncompleted).isDisplayed());			
		//input at least eight characters password 
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("abcdf1234");
		//verify password greater than eight characters
		Assert.assertTrue(driver.findElement(lowercasecharCompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(uppercasecharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(numbercharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(specialcharUncompleted).isDisplayed());
		Assert.assertTrue(driver.findElement(eightcharCompleted).isDisplayed());			
	}
	
	@Test
	public void TC_05_WebElement() {
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
