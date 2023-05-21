package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_User_Interation_PII {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	Random rand;
	WebDriverWait explicitWait;
	Select Select;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		rand = new Random();
		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		}

	//@Test
	public void TC_01_Click_And_Hold() {
		//Open link
		driver.get("https://automationfc.github.io/jquery-selectable/");
		//Click and hold tu 1 den 4
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		action.clickAndHold(listNumber.get(0))
		.moveToElement(listNumber.get(3))
		.release()
		.perform();
		sleepInSecond(3);
		//Verify lua chon
		List<WebElement> listSelectedNumber = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(listSelectedNumber.size(), 4);
		}
	
	//@Test
	public void TC_02_Click_And_Hold_Random() {
		//Open link
		driver.get("https://automationfc.github.io/jquery-selectable/");
		//Click and hold random 1, 3, 7, 8
		//Step 1: tao list
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		//Nhan Ctrl xuong
		action.keyDown(Keys.CONTROL).perform();
		//Click chon random 1, 3, 7, 8
		action.click(listNumber.get(0))
		.click(listNumber.get(2))
		.click(listNumber.get(6))
		.click(listNumber.get(7)).perform();
		//Nha phim Ctrl ra
		action.keyUp(Keys.CONTROL).perform();
		//Verify lua chon
		List<WebElement> listSelectedNumber = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(listSelectedNumber.size(), 4);	
		}
	
	@Test
	public void TC_03_Right_Click() {
		//Open link
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		//Right click
		action.contextClick(driver.findElement(By.cssSelector("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		//Verify Menu Quit hien thi
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		sleepInSecond(3);
		//Hover chuot vao Quit
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		//Verify 
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-hover")).isDisplayed());
		sleepInSecond(3);
		//Click chon Quit
		action.click(driver.findElement(By.cssSelector("li.context-menu-hover"))).perform();
		driver.switchTo().alert().accept();
		sleepInSecond(3);
		//Verify Quit menu khong con dc hien thi
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
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
