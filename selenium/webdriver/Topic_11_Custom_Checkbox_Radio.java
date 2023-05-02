package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Random rand;
	WebDriverWait explicitWait;
	Select Select;
	String projectPath = System.getProperty("user.dir");

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		rand = new Random();
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		}

	//@Test
	public void TC_01() {
		//Open link
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		//Click radio button dang ky cho ng than
		// ko work dc do the bi an
		driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/preceding-sibling::div/input")).click();
		//Verify Button da dc chon
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/preceding-sibling::div/input")).isSelected());	
		}
	
	//@Test
	public void TC_02() {
		//Open link
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		//Click radio button dang ky cho ng than
		driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/parent::label")).click();
		//Verify Button da dc chon
		// ko work dc do ham isSelected chi dung dc cho the input
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/parent::label")).isSelected());
		}
	
	//@Test
	public void TC_03() {
		//Open link
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		//Click radio button dang ky cho ng than
		driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/parent::label")).click();
		//Verify Button da dc chon
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/preceding-sibling::div/input")).isSelected());	
		// work dc nhung phai su dung 2 selector khac nhau cho cung 1 vi tri
		}
			
	//@Test
	public void TC_04() {
		//Open link
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		//Click radio button dang ky cho ng than
		jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/preceding-sibling::div/input")));

		//Verify Button da dc chon
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/preceding-sibling::div/input")).isSelected());	
		}
	
	@Test
	public void TC_05() {
		//Open link
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(5);
		//Check can tho radio chua dc chon
		//cach 1
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label=\"Cần Thơ\" and @aria-checked=\"false\"]")).isDisplayed());	
		//cach 2
		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label=\"Cần Thơ\"]")).getAttribute("aria-checked"), "false");
		//Click radio can tho
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@aria-label=\"Cần Thơ\" and @aria-checked=\"false\"]")));
		
		//Check radio da dc chon chua
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label=\"Cần Thơ\"][aria-checked=\"true\"]")).isDisplayed());
		}
		
	@AfterClass
	public void afterClass() {
		//driver.quit();
		}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
