package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
	WebDriver driver;
	Random rand;
	WebDriverWait explicitWait;
	Select Select;
	String projectPath = System.getProperty("user.dir");

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		rand = new Random();
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		}

	//@Test
	public void TC_01_Button() {
		//Open link
		driver.get("https://www.fahasa.com/customer/account/create");
		//Navigate sang tab dang nhap
		driver.findElement(By.xpath("//a[text()= 'Đăng nhập']")).click();
		//Verify button dang nhap is disabled
		By loginButton = By.xpath("//button[@class='fhs-btn-login']");
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background");
		Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
		//Input du lieu hop le vao email va password
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("sunbeongocngech@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456789");
		
		//Verify button dang nhap is enable
		loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		Color loginButtonBackgroundColor = Color.fromString(loginButtonBackground);
		Assert.assertEquals(loginButtonBackgroundColor.asHex().toUpperCase(), "#C92127");
			
		}
	@Test
	public void TC_02_Default_Checkbox_Or_Radio() {
		//Open link
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");	
		//Click vao 1 checkbox
		driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]/preceding-sibling::input")).click();
		//Kiem tra checkbox da dc chon
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]/preceding-sibling::input")).isSelected());
		//Sau khi checkbox dc chon thi tiep tuc thuc hien bo chon 
		driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]/preceding-sibling::input")).click();
		//Kiem tra checkbox da dc chon
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()=\"Dual-zone air conditioning\"]/preceding-sibling::input")).isSelected());
			
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
