package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea_PI {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String firstName,middleName,lastName, employeeID, userName;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		firstName = "Lee";
		middleName = "Sun";
		lastName ="Jae";
		employeeID = String.valueOf(rand.nextInt(99999));
		userName = String.valueOf(rand.nextInt(99999)) + firstName;
	}

	@Test
	public void TC_01_Create_New_Employee() {
		//Login successfully
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(3);
		//Open PIM screen
		driver.findElement(By.xpath("//ul[@class='oxd-main-menu']//span[text()='PIM']")).click();
		sleepInSecond(3);
		//Open Employee tab
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		//Input validate thong tin 
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("middleName")).sendKeys(middleName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		driver.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")).click();
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div//following-sibling::div//input")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div//following-sibling::div//input")).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div//following-sibling::div//input")).sendKeys(employeeID);
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input")).sendKeys(userName);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div//input")).sendKeys("Abc123456!");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div//input")).sendKeys("Abc123456!");
		//Click Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(7);
		//Verify information 
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='middleName']")).getAttribute("value"),middleName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text() ='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		sleepInSecond(3);
		//Open Immigration tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button[@class='oxd-button oxd-button--medium oxd-button--text']")).click();
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div//input")).sendKeys("0904686868");
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div//textarea")).sendKeys("This is\na good automation testing course");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(5);
		//Edit information
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(5);
		//Verify information
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),"0904686868");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),"This is\na good automation testing course");
		//Log out
		driver.findElement(By.xpath("//span[@class ='oxd-userdropdown-tab']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(5);
		//Log in by user in above step
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys("Abc123456!");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Open My info screen
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='middleName']")).getAttribute("value"),middleName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		sleepInSecond(3);
		//Open Immigration tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();
		sleepInSecond(3);
		//Verify information	
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),"0904686868");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),"This is\na good automation testing course");
	}
	
	
	@Test
	public void TC_02() {
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
