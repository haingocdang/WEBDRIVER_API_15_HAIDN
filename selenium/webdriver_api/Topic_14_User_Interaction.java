package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.Robot;

import java.awt.AWTException;
import java.awt.Point;

import org.openqa.selenium.Alert;

import java.util.List;

import java.awt.event.InputEvent;

import org.openqa.selenium.Dimension;
//import org.openqa.selenium.Point;

public class Topic_14_User_Interaction {
	// Khai bao 1 bien driver dai dien cho Selenium Driver
	WebDriver driver;
	Actions action;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khoi tao FF
		driver = new FirefoxDriver();
		
	//	System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver_chrome79.exe");
	//	driver=new ChromeDriver();
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
	
//	@Test
	public void TC_05_RightClick() throws InterruptedException {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		String option="Edit";
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		action.moveToElement(driver.findElement(By.xpath("//span[text()='"+ option + "']"))).perform();
		
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='"+ option+ "']")).isDisplayed());
		
		action.click(driver.findElement(By.xpath("//span[text()='"+ option + "']"))).perform();
		
		Alert alert=driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "clicked: "+ option);
		
		Thread.sleep(2000);
		
		alert.accept();
		
	}
	
//	@Test
	public void TC_06_DragAndDrop() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();;
		
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='You did great!']")).isDisplayed());
		
		//List <WebElement> selectedNumbers=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));

	}

	@Test
	public void TC_07_DragAndDrop_HTML5() throws InterruptedException, AWTException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		
	//	action.dragAndDrop(driver.findElement(By.id("column-a")), driver.findElement(By.id("column-b"))).perform();;
		String sourceLocator="//div[@id='column-a']";
		String targetLocator="//div[@id='column-b']";
		
		drag_and_drop_html5_by_xpath(sourceLocator, targetLocator);
		
		
		Thread.sleep(2000);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(),"B");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(),"A");

		
	//	Assert.assertTrue(driver.findElement(By.xpath("//div[text()='You did great!']")).isDisplayed());
		
		//List <WebElement> selectedNumbers=driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));

	}
	
	public void drag_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException{
		
		WebElement source=driver.findElement(By.xpath(sourceLocator));
		WebElement target=driver.findElement(By.xpath(targetLocator));
		
		//Setup Robot
		java.awt.Robot robot ;
		robot= new java.awt.Robot();
		robot.setAutoDelay(50);
		
		//Get Size elements		
		Dimension sourceSize=source.getSize();
		Dimension targetSize=target.getSize();
		
		//Get center distance
		int xCentreSource=sourceSize.width/2;
		int yCentreSource=sourceSize.height/2;
		int xCentreTarget=targetSize.width/2;
		int yCentreTarget=targetSize.height/2;
		
		org.openqa.selenium.Point sourceLocation=source.getLocation();
		org.openqa.selenium.Point targetLocation=target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());
		
		//Make Mouse coordinate center of Element
		sourceLocation.x+=20+xCentreSource;
		sourceLocation.y+=110+yCentreSource;
		targetLocation.x+=20+xCentreTarget;
		targetLocation.y+=110+yCentreTarget;
		
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());
		
		//Move mouse to drag and drop from Location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);
		
		//Click and Drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x-targetLocation.x)/2)+targetLocation.x,((sourceLocation.y-targetLocation.y)/2));
		
		//Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);
		
		//Frop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}