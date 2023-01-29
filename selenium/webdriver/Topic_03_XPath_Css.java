package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath_Css {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}

	@Test
	public void TC_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id ='txtEmail-error' and @class ='error']")).getText(),"Vui lòng nhập email");		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error' and @class='error']")).getText(),"Vui lòng nhập lại địa chỉ email");		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id ='txtPassword-error' and @class ='error']")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id ='txtCPassword-error' and @class ='error']")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error' and @class ='error']")).getText(),"Vui lòng nhập số điện thoại.");

	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@class='text form-control'and@id='txtFirstname']")).sendKeys("Pham");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("nhung.123");
		driver.findElement(By.xpath("//input [@class='text form-control' and @id ='txtCEmail']")).sendKeys("nhung.123");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @ id='txtPassword']")).sendKeys("nhung123456");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("nhung123456");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @id='txtPhone']")).sendKeys("01234567894");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error' and @class='error']")).getText(),"Vui lòng nhập email hợp lệ");		
		
	}

	@Test
	public void TC_03_Incorrect_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@class='text form-control'and@id='txtFirstname']")).sendKeys("Pham");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("nhung@gmail.com");
		driver.findElement(By.xpath("//input [@class='text form-control' and @id ='txtCEmail']")).sendKeys("nhung123@gmail.com");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @ id='txtPassword']")).sendKeys("nhung123456");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("nhung123456");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @id='txtPhone']")).sendKeys("01234567894");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label [@id='txtCEmail-error' and @class='error']")).getText(),"Email nhập lại không đúng");
		}
	
	@Test
	public void TC_04_Invalid_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@class='text form-control'and@id='txtFirstname']")).sendKeys("Pham");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("nhung@gmail.com");
		driver.findElement(By.xpath("//input [@class='text form-control' and @id ='txtCEmail']")).sendKeys("nhung@gmail.com");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @ id='txtPassword']")).sendKeys("123");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("nhung123");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @id='txtPhone']")).sendKeys("01234567894");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error' and @class ='error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		}
	
	@Test
	public void TC_05_Incorrect_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html"); 
		driver.findElement(By.xpath("//input[@class='text form-control'and@id='txtFirstname']")).sendKeys("Pham");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("nhung@gmail.com");
		driver.findElement(By.xpath("//input [@class='text form-control' and @id ='txtCEmail']")).sendKeys("nhung@gmail.com");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @ id='txtPassword']")).sendKeys("nhung123456");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("123nhung");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @id='txtPhone']")).sendKeys("01234567894");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error' and @class ='error']")).getText(),"Mật khẩu bạn nhập không khớp");
		}

	@Test
	public void TC_06_Invalid_Phone_Number() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//input[@class='text form-control'and@id='txtFirstname']")).sendKeys("Pham");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtEmail']")).sendKeys("nhung@gmail.com");
		driver.findElement(By.xpath("//input [@class='text form-control' and @id ='txtCEmail']")).sendKeys("nhung@gmail.com");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @ id='txtPassword']")).sendKeys("nhung123456");
		driver.findElement(By.xpath("//input[@class='text form-control' and @id='txtCPassword']")).sendKeys("nhung123456");
		driver.findElement(By.xpath("//input[@class ='text form-control' and @id='txtPhone']")).sendKeys("78123456789");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error' and @class ='error']")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
