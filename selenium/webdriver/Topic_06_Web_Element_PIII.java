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

public class Topic_06_Web_Element_PIII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Login_with_empty_Email_and_password() {
		driver.get("https://www.google.com/url?q=http://live.techpanda.org/&sa=D&source=docs&ust=1676215088949797&usg=AOvVaw3sUf7fADY2kalys_I1RB54");
		//click my account
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[@title ='My Account']")).click();
		sleepInSecond(3);
		//bo trong user, password and click login button
		driver.findElement(By.xpath("//button[@class ='button' and @title ='Login']")).click();
		sleepInSecond(3);
		//verify error messeage in email address
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email' and text()='This is a required field.']")).isDisplayed());
		//verify error messeage in password
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass' and text()='This is a required field.']")).isDisplayed());
	}
	
	@Test
	public void TC_02_Login_with_invalidate_email() {
		driver.get("https://www.google.com/url?q=http://live.techpanda.org/&sa=D&source=docs&ust=1676215088949797&usg=AOvVaw3sUf7fADY2kalys_I1RB54");
		//click my account
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[@title ='My Account']")).click();
		sleepInSecond(3);
		//input invalidate email 
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234@123.123");
		//input validate password
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		//click login button
		driver.findElement(By.xpath("//button[@class ='button' and @title ='Login']")).click();
		sleepInSecond(3);
		//verify invalid messeage in email address
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-email-email' and text()='Please enter a valid email address. For example johndoe@domain.com.']")).isDisplayed());
	}
	
	@Test
	public void TC_03_Login_with_invalidate_password_less_than_6chars() {
		driver.get("https://www.google.com/url?q=http://live.techpanda.org/&sa=D&source=docs&ust=1676215088949797&usg=AOvVaw3sUf7fADY2kalys_I1RB54");
		//click my account
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[@title ='My Account']")).click();
		sleepInSecond(3);
		//input validate email 
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("lekimngan@gmail.com");
		//input invalidate password
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234a");
		//click login button
		driver.findElement(By.xpath("//button[@class ='button' and @title ='Login']")).click();
		sleepInSecond(3);
		//verify invalid messeage in password
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass' and text()='Please enter 6 or more characters without leading or trailing spaces.']")).isDisplayed());
	}
	
	@Test
	public void TC_04_Login_with_incorrect_email_password() {
		driver.get("https://www.google.com/url?q=http://live.techpanda.org/&sa=D&source=docs&ust=1676215088949797&usg=AOvVaw3sUf7fADY2kalys_I1RB54");
		//click my account
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[@title ='My Account']")).click();
		sleepInSecond(3);
		//input incorrect email 
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("lekimngan12@gmail.com");
		//input validate password
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456abc");
		//click login button
		driver.findElement(By.xpath("//button[@class ='button' and @title ='Login']")).click();
		sleepInSecond(3);
		//verify error messeage 
		Assert.assertTrue(driver.findElement(By.xpath("//li//span[text()='Invalid login or password.']")).isDisplayed());
	}
	
	@Test
	public void TC_05_Create_new_account() {
		driver.get("https://www.google.com/url?q=http://live.techpanda.org/&sa=D&source=docs&ust=1676215088949797&usg=AOvVaw3sUf7fADY2kalys_I1RB54");
		//click my account
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[@title ='My Account']")).click();
		sleepInSecond(3);
		//click create account button
		driver.findElement(By.xpath("//a[@class='button' and @title ='Create an Account']")).click();
		//input validate first name, middle name, last name, email, password, confirm password
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Jae");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Sun");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Lee");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("sun@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("1234567");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("1234567");
		driver.findElement(By.xpath("//button[@class='button' and @title ='Register']")).click();
		//verify succesfull register
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
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
