package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_PI {
	WebDriver driver;
	Random rand;
	WebDriverWait explicitWait;
	Select Select;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, userName, password;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		rand = new Random();
		driver = new ChromeDriver();
		firstName ="NhungPham";
		userName = String.valueOf(rand.nextInt(99999)) + firstName;
		password = String.valueOf(rand.nextInt(99999)) + "@";
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

	//@Test
	public void TC_01_Fixed_In_DOM() {
		//Open URl
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.xpath("//div[@id='modal-login-v1']//div[@class='modal-content']");
		sleepInSecond(3);
		//Click dang nhap button
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		//Verify popup xuat hien
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		//Nhap Thong tin username, password
		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='account-input']")).sendKeys(userName);
		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='password-input']")).sendKeys(password);
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
		//Verify error message
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).getText(),"Tài khoản không tồn tại!");
		//Dong popup
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[@class='close']")).click();
		//Verify popup khong xuat hien
		//Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
			
	}
	
	@Test
	public void TC_02_Fixed_In_DOM() {
		//Open URl
		driver.get("https://skills.kynaenglish.vn/");
		//Click dang nhap button
		driver.findElement(By.xpath("//a[@class='login-btn']")).click();
		//Verify popup xuat hien
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='k-popup-account-login']")).isDisplayed());		
		//Nhap Thong tin username, password
		driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys(password);		
		driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
		//Verify error message
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Sai tên đăng nhập hoặc mật khẩu']")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
		//Dong popup
		driver.findElement(By.xpath("//button[@type='button' and @class='k-popup-account-close close']")).click();
		//Verify popup khong xuat hien
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='k-popup-account-login']")).isDisplayed());				
		
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
