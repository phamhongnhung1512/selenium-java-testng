package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_User_Interation_PI {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		}

	@Test
	public void TC_01_AutomationGitHubWeb() {
		//Open link
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		//Hover chuot  input#age
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		//Verify tooltips
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='tooltip']/div[contains(text(),'We ask for your age only for statistical purposes.')]")).getText(),"We ask for your age only for statistical purposes.");
			
		}
	
	@Test
	public void TC_02_Fahasa() {
		//Open link
		driver.get("https://www.fahasa.com/");
		//Hover chuot incon menu
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		//Hover chuot sach trong nuoc
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']"))).perform();	
		sleepInSecond(5000);
		//Verify submenu co muc ky nang song
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Kỹ Năng Sống']")).getText(),"Kỹ Năng Sống");
			
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
