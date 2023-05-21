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

public class Topic_18_Popup_PIII {
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

	@Test
	public void TC_01_Fixed_Not_In_DOM() {
		//Open URl
		driver.get("https://tiki.vn/");
		By loginPopup = By.xpath("//div[@class='ReactModal__Content ReactModal__Content--after-open']");
		sleepInSecond(3);
		//Click dang nhap button
		driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
		//Verify popup xuat hien
		Assert.assertEquals(driver.findElements(loginPopup).size(),1);
		//Click dang nhap bang email
		driver.findElement(By.xpath("//p[text()='Đăng nhập bằng email']")).click();
		//Khong nhap du lieu va click dang nhap button
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		//Verify error message
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(), "Mật khẩu không được để trống");
		//Dong popup
		driver.findElement(By.xpath("//img[@class='close-img']")).click();
		//Verify popup khong xuat hien
		Assert.assertEquals(driver.findElements(loginPopup).size(),0);	
	}
	
	@Test
	public void TC_02_Fixed_Not_In_DOM() {
		//Open URl
		driver.get("https://www.facebook.com/");
		By createAccountPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		//Click dang nhap button
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(3);
		//Verify popup xuat hien
		Assert.assertEquals(driver.findElements(createAccountPopup).size(),1);
		//Dong popup
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);		
		//Verify popup khong xuat hien
		Assert.assertEquals(driver.findElements(createAccountPopup).size(),0);	
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
