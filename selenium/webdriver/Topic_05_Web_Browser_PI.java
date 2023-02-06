package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_PI {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		}
	@Test
	public void TC_01_() {
		// >=2 tab thi đóng tab/window ma no dang dung, neu co 1 tab thi dong Browser
		//driver.close();
		// đóng Browser(ko quan tam co bao tab/window)
		//driver.quit();
		// mở 1 url
		driver.get("http://live.techpanda.org/");
		// get url hien tai
		driver.getCurrentUrl();
		// tim 1 element, dat bien 
		WebElement searchTextbox = driver.findElement(By.xpath("//input[@id ='search']"));
		searchTextbox.sendKeys("kimnganle@gmail.com");
		// tim n element
		List<WebElement> links = driver.findElements(By.xpath("//div[@class ='links']"));
		links.size();
		//tra ve pagesource
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("2015 Magento Demo Store. All Rights Reserved."));
		}
}
