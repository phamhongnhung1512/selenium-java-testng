package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	Random rand;
	Select Select;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailUser, password;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		firstName ="Lee";
		lastName ="Sun Jae";
		emailUser ="sunlee" + rand.nextInt(9999)+"@gmail.com";
		password ="Abc123456@";
		}

	@Test
	public void TC_01_Create_New_Employee() {
		//Open register screen
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		sleepInSecond(3);		
		//Input validate information
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailUser);
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText("15");
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText("July");
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText("1991");
		driver.findElement(By.name("Password")).sendKeys(password);
		driver.findElement(By.name("ConfirmPassword")).sendKeys(password);
		
		//Verify so luong item trong tung dropdown
		driver.findElements(By.xpath("//select[@name='DateOfBirthDay']/option")).size();
		//Assert.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option")).getSize(),"32");
		//Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getOptions(),"12");
		//Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getOptions(),"112");
				
		//Click register
		driver.findElement(By.name("register-button")).click();
		sleepInSecond(3);
		//Verify register successfully
		Assert.assertTrue(driver.getPageSource().contains("Your registration completed"));
		//Open my account screen
		driver.findElement(By.xpath("//a[text()='My account']")).click();
		//Login
		driver.findElement(By.name("Email")).sendKeys(emailUser);
		driver.findElement(By.name("Password")).sendKeys(password);
		driver.findElement(By.cssSelector("button[class='button-1 login-button']")).click();
		sleepInSecond(3);
		
		//Verify information
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='gender-male']")).getAttribute("value"),"M");
		Assert.assertEquals(driver.findElement(By.name("FirstName")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.name("LastName")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.name("Email")).getAttribute("value"),emailUser);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption(),"15");
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption(),"July");
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption(),"1991");
				
		}
	@Test
	public void TC_02() {
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
		//driver.quit();
		}
}
