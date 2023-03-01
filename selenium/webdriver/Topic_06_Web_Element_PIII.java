package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.Random;
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
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String emailAddress, firstName, midleName, lastName,fullName, password;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstName = "Lee";
		midleName ="Sun";
		lastName ="Jae";
		password ="1234567";
		emailAddress = "sunlee" + rand.nextInt(9999) + "@gmail.com";
		fullName = firstName+" "+ midleName+" "+ lastName;		
				
	}

	@Test
	public void TC_01_Login_with_empty_Email_and_password() {
		driver.get("http://live.techpanda.org/");
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
		driver.get("http://live.techpanda.org/");
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
		driver.get("http://live.techpanda.org/");
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
		driver.get("http://live.techpanda.org/");
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
		driver.get("http://live.techpanda.org/");
		//click my account
		driver.findElement(By.xpath("//div[@class ='footer-container']//a[@title ='My Account']")).click();
		sleepInSecond(3);
		//click create account button
		driver.findElement(By.xpath("//a[@class='button' and @title ='Create an Account']")).click();
		//input validate first name, middle name, last name, email, password, confirm password
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(midleName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='button' and @title ='Register']")).click();
		//verify succesfull register
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).getText(),"Thank you for registering with Main Website Store.");
		String contactInformationText = driver.findElement(By.xpath("//h3[text()=\"Contact Information\"]/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
		//click my account
		driver.findElement(By.xpath("//a[@class ='skip-link skip-account skip-active']")).click();
		//click log out button
		driver.findElement(By.xpath("//a[@title ='Log Out']")).click();
		sleepInSecond(3);
		//check he thong da navigate thanh cong
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/logoutSuccess/");
	}
	@Test
	public void TC_06_WebElement() {
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
