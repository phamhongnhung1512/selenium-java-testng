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

public class Topic_09_Custom_Dropdown_Shortcut_ClearCode {
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
	public void TC_01_Test_JQuery() {
		//Open link
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//Chon 1 item cua Select a speed dua vao ham selectItemInDropdonw da viet
		selectItemInDropdonw("span#speed-button", "ul#speed-menu>li.ui-menu-item", "Fast", "span#speed-button>span.ui-selectmenu-text");
		sleepInSecond(3);
		//Chon 1 item khac cua Select a speed 
		selectItemInDropdonw("span#speed-button", "ul#speed-menu>li.ui-menu-item", "Medium", "span#speed-button>span.ui-selectmenu-text");
		sleepInSecond(3);
		//Chon 1 item khac cua Select a speed 
		selectItemInDropdonw("span#speed-button", "ul#speed-menu>li.ui-menu-item", "Faster", "span#speed-button>span.ui-selectmenu-text");
		sleepInSecond(3);
		
		//Chon 1 item cua Select a file dua vao ham selectItemInDropdonw da viet
		selectItemInDropdonw("span#files-button", "ul#files-menu>li.ui-menu-item", "ui.jQuery.js", "span#files-button>span.ui-selectmenu-text");
		sleepInSecond(3);
		//Chon 1 item khac cua Select a file 
		selectItemInDropdonw("span#files-button", "ul#files-menu>li.ui-menu-item", "Some other file with a very long option text", "span#files-button>span.ui-selectmenu-text");
		sleepInSecond(3);
		//Chon 1 item khac cua Select a file 
		selectItemInDropdonw("span#files-button", "ul#files-menu>li.ui-menu-item", "Some unknown file", "span#files-button>span.ui-selectmenu-text");
		sleepInSecond(3);
		}
	
	@Test
	public void TC_02_Test_ReactJS() {
		//Open link
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		//Chon 1 item cua Select a file dua vao ham selectItemInDropdonw da viet
		selectItemInDropdonw("li.dropdown-toggle", "ul.dropdown-menu>li", "Second Option", "li.dropdown-toggle");
		sleepInSecond(3);
		}

	@Test
	public void TC_03_Test_Editable() {
		//Open link
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		//Chon 1 item cua Select a file dua vao ham selectItemInDropdonw da viet
		editableItemInDropdonw("input.search", "div[role ='option']", "Aruba", "div[class ='divider text']") ;
		sleepInSecond(3);
		}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
		}
		//Tranh lap lai code n lan thi chi goi ham ra de dung
		//Di kem vs tham so
		//Neu truyen cung 1 gia tri vao trong ham thi se la vo ngia
		//Nen define de dung di dung la n lan
	public void selectItemInDropdonw(String parentCss, String allItemCss, String expectedTextItem, String selectedItem ) {
		//Click de xo ra item in dropdownlist
		driver.findElement(By.cssSelector(parentCss)).click();
		//Wait de show all item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		//Chon item
		for (WebElement tempItem : speedDropdownItems) {
			if (tempItem.getText().trim().equals(expectedTextItem) ) {
				tempItem.click();
				System.out.println (tempItem.getText());
				break;
				}
			}	
		//Kiem tra item da dc chon 
		Assert.assertEquals(driver.findElement(By.cssSelector(selectedItem)).getText(), expectedTextItem);
	}
	
	public void editableItemInDropdonw(String editableCss, String allItemCss, String expectedTextItem, String selectedItemCss ) {
		//Click de xo ra item in dropdownlist
		driver.findElement(By.cssSelector(editableCss)).sendKeys(expectedTextItem);
		//Wait de show all item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		//Chon item
		for (WebElement tempItem : speedDropdownItems) {
			if (tempItem.getText().trim().equals(expectedTextItem) ) {
				tempItem.click();
				System.out.println (tempItem.getText());
				break;
				}
			}	
		//Kiem tra item da dc chon 
		Assert.assertEquals(driver.findElement(By.cssSelector(selectedItemCss)).getText(), expectedTextItem);
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
