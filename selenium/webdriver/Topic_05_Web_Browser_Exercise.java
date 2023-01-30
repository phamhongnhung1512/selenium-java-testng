package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	@Test
	public void TC_01_Url() {
		driver.get("http://live.techpanda.org/");
		//Page login
		driver.findElement(By.xpath("//div[@class ='footer']//a[@title ='My Account']")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Create an account
		driver.findElement(By.xpath("//a [@title ='Create an Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		//driver.close();		
		}

	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		//Page login
		driver.findElement(By.xpath("//div[@class ='footer']//a[@title ='My Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		//Create an account
		driver.findElement(By.xpath("//a [@title ='Create an Account']")).click();
		sleepInSecond(3);
					
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		//driver.close();		
		}

	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.techpanda.org/");
		//Click on My Account
		driver.findElement(By.xpath("//div[@class ='footer']//a[@title ='My Account']")).click();
		sleepInSecond(3);
					
		//Create an account
		driver.findElement(By.xpath("//a [@title ='Create an Account']")).click();
		sleepInSecond(3);
		
		//Back lai
		driver.navigate().back();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		//Forward
		driver.navigate().forward();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");		
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
