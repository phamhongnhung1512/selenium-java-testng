package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	@Test
	public void Browser_TC_01_Verify_Url() {
		//Open link
		driver.get("http://live.techpanda.org/");
		//Click my account
		driver.findElement(By.xpath("//div [@class =\"footer\"]//a[@title =\"My Account\"]")).click();
		sleepInSecond(3);
		//Verify current url login
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");	
		//Click create an account
		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		sleepInSecond(3);
		//Verify current url create account
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");		
		}
	@Test
	public void Browser_TC_02_Verify_Title() {
		//Open link
		driver.get("http://live.techpanda.org/");
		//Click my account
		driver.findElement(By.xpath("//div [@class =\"footer\"]//a[@title =\"My Account\"]")).click();
		sleepInSecond(3);
		//Verify title login
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		//Click create an account
		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		sleepInSecond(3);
		//Verify title create account
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");	
		}
	@Test
	public void Browser_TC_03_Navigate() {
		//Open link
		driver.get("http://live.techpanda.org/");
		//Click my account
		driver.findElement(By.xpath("//div [@class =\"footer\"]//a[@title =\"My Account\"]")).click();
		//Click create an account
		driver.findElement(By.xpath("//a[@title ='Create an Account']")).click();
		//Verify current url create account
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");	
		//back to previous page
		driver.navigate().back();
		sleepInSecond(3);
		//Verify previous url 
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");	
		//forward to next page
		driver.navigate().forward();
		//Verify next page
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");	
		}
	@Test
	public void Browser_TC_04_GetPageSourceCode() {
		
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
