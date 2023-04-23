package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
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

	@Test
	public void TC_01_Test_JQuery() {
		//Open link
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//Click de xo ra item in dropdownlist
		driver.findElement(By.cssSelector("span#speed-button")).click();
		//Wait de show all item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li.ui-menu-item")));
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("li.ui-menu-item"));
		//Chon item
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			if (itemText.equals("Fast") ) {
				tempItem.click();
				System.out.println (itemText);
				break;
				}
			}	
		//Kiem tra item da dc chon 
		driver.findElement(By.cssSelector("span#speed-button")).getText();
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		}
	@Test
	public void TC_02_Test_ReactJS() {
		//Open link
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		//Click de xo ra item in dropdownlist
		driver.findElement(By.cssSelector("li.dropdown-toggle")).click();
		//Wait show all items
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='dropdown-menu']//a")));
		List <WebElement> dropdownMenuItem = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//a"));
		//Chon item
		for(WebElement temItemInDropdownMenu: dropdownMenuItem  ) {
			String selectedItem = temItemInDropdownMenu.getText();
			if (selectedItem.equals("Second Option")) {
				temItemInDropdownMenu.click();
				break;
				}
			}
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
