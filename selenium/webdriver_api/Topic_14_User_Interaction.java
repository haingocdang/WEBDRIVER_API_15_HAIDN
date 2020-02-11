package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class Topic_14_User_Interaction {
	// Khai bao 1 bien driver dai dien cho Selenium Driver
	WebDriver driver;
	Actions action;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khoi tao FF
		driver = new FirefoxDriver();
		action=new Actions(driver);
		// Cho cho element dc hien thi truoc khi tuong tac trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Maximize browser
		driver.manage().window().maximize();
		
	}

//	@Test
	public void TC_01_() {
		
	}

//	@Test
	public void TC_02_ClickAndHold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> numbers=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int numberSize=numbers.size();
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(9)).release().perform();
		
		List <WebElement> selectedNumbers=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		

	}

//	@Test
	public void TC_03_ClickAndHoldRamdon() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> numbers=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int numberSize=numbers.size();
		action.keyDown(Keys.CONTROL).click(numbers.get(0)).click(numbers.get(2)).click(numbers.get(4)).click(numbers.get(6)).click(numbers.get(10)).perform();
		
		//action.click(numbers.get(2)).click(numbers.get(4)).click(numbers.get(6)).click(numbers.get(10)).perform();
		action.keyUp(Keys.CONTROL).perform();
		Thread.sleep(2000);
		
		//List <WebElement> selectedNumbers=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));

	}
	
//	@Test
	public void TC_04_DoubleClick() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
		
		Thread.sleep(2000);
		
		//List <WebElement> selectedNumbers=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));

	}
	
	@Test
	public void TC_05_RightClick() throws InterruptedException {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		String option="Copy";
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		action.moveToElement(driver.findElement(By.xpath("//span[text()='"+ option + "']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='"+ option+ "']")).isDisplayed());
		
		Thread.sleep(2000);
		
		//List <WebElement> selectedNumbers=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}