package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register");
	}

	@Test
	public void TC_01_id() {
		driver.findElement(By.id("FirstName")).sendKeys("Pham");
		}

	@Test
	public void TC_02_Class() {
		driver.get("https://demo.nopcommerce.com/search");
		driver.findElement(By.className("search-box-text")).sendKeys("Xiaomi Note 8 pro");
	}

	@Test
	public void TC_03_name() {
		driver.findElement(By.name("advs")).click();
	}
	@Test
	public void TC_04_Tagname() {
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Addresses")).click();
	}
	@Test
	public void TC_06_PartialLinkText() {
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test 
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("NganLe");
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("xinhdep");
		driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")).sendKeys("12");
		driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")).sendKeys("12");
		driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")).sendKeys("1991");
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("trongtoaletemhettenanh@gmail.com");
	}
	
	@Test 
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Sunbeo");
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("girlkuteyeuboydepgiai");
		//driver.quit();
	}
	
//	@Test
//	public void TC_09_Xpath() {
//		driver.get("http://live.techpanda.org/index.php/customer/account/login");
//		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class ='skip-link skip-account']")).click();
//		driver.findElement(By.xpath("//div[@id ='header-account']//a[@title ='My Account']")).click();
//		//driver.quit();
//	}
	
	@Test
	// practice with some text
	public void TC_10_Xpath() {
		driver.get("https://automationfc.github.io/basic-form/");
		System.out.println(driver.findElement(By.xpath("//h5[contains(text(),\"Michael Jackson\")]")).getText());
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
