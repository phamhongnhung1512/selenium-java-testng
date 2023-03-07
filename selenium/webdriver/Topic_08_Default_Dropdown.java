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

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String customerID, emailCustomer;
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		emailCustomer = "sunlee"+ String.valueOf(rand.nextInt(99999));
		}

	@Test
	public void TC_01_Create_New_Employee() {
		//Login successfully
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.name("uid")).sendKeys("mngr483191");
		driver.findElement(By.name("password")).sendKeys("jYhuqYn");
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(3);
		//Open new customer screen
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		sleepInSecond(3);
		//Input validate information
		driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td/input")).sendKeys("Lee Sun Jae");
		driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td/input[@value='f']")).click();
		driver.findElement(By.xpath("//td[text()='Date of Birth']/following-sibling::td/input")).sendKeys("15121991");
		driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).sendKeys("this is a good course Thanks");
		driver.findElement(By.xpath("//td[text()='City']/following-sibling::td/input")).sendKeys("Hanoi");
		driver.findElement(By.xpath("//td[text()='State']/following-sibling::td/input")).sendKeys("VinhBacBo");
		driver.findElement(By.xpath("//td[text()='PIN']/following-sibling::td/input")).sendKeys("012345");		
		driver.findElement(By.xpath("//td[text()='Mobile Number']/following-sibling::td/input")).sendKeys("0904686871");
		driver.findElement(By.xpath("//td[text()='E-mail']/following-sibling::td/input")).sendKeys(emailCustomer+"@gmail.com");
		driver.findElement(By.xpath("//td[text()='Password']/following-sibling::td/input")).sendKeys("Abc1234567!");
		driver.findElement(By.name("sub")).click();
		sleepInSecond(3);
		//Get CustomerID
		driver.findElement(By.xpath("//td[text() ='Customer ID']/following-sibling::td")).getText();
		customerID = driver.findElement(By.xpath("//td[text() ='Customer ID']/following-sibling::td")).getText();
		//Verify information of registered customer
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='Customer Name']/following-sibling::td")).getText(),"Lee Sun Jae");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='Gender']/following-sibling::td")).getText(),"female");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='Birthdate']/following-sibling::td")).getText(),"1991-12-15");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='Address']/following-sibling::td")).getText(),"this is a good course Thanks");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='City']/following-sibling::td")).getText(),"Hanoi");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='State']/following-sibling::td")).getText(),"VinhBacBo");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='Pin']/following-sibling::td")).getText(),"012345");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='Mobile No.']/following-sibling::td")).getText(),"0904686871");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='Email']/following-sibling::td")).getText(),emailCustomer+"@gmail.com");
		//Open Edit customer
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		sleepInSecond(3);
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		sleepInSecond(3);
		//Verify information in Edit screen
		driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td/input")).getAttribute("value");
		driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).getText();
		//Edit information in Edit screen
		driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).clear();
		driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).sendKeys("Now we have another good course manythank");
		driver.findElement(By.xpath("//td[text()='State']/following-sibling::td/input")).clear();
		driver.findElement(By.xpath("//td[text()='State']/following-sibling::td/input")).sendKeys("Dongbangsonghong");
		driver.findElement(By.xpath("//td[text()='City']/following-sibling::td/input")).clear();
		driver.findElement(By.xpath("//td[text()='City']/following-sibling::td/input")).sendKeys("Hatay");
		driver.findElement(By.xpath("//td[text()='PIN']/following-sibling::td/input")).clear();	
		driver.findElement(By.xpath("//td[text()='PIN']/following-sibling::td/input")).sendKeys("123456");		
		driver.findElement(By.xpath("//td[text()='Mobile Number']/following-sibling::td/input")).clear();
		driver.findElement(By.xpath("//td[text()='Mobile Number']/following-sibling::td/input")).sendKeys("0904686868");
		driver.findElement(By.xpath("//td[text()='E-mail']/following-sibling::td/input")).clear();
		driver.findElement(By.xpath("//td[text()='E-mail']/following-sibling::td/input")).sendKeys(emailCustomer+"2"+"@gmail.com");
		driver.findElement(By.name("sub")).click();
		sleepInSecond(3);		
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
		//driver.quit();
		}
}
