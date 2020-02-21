package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import javafx.scene.control.Alert;
import org.openqa.selenium.Alert;

public class Topic_11_Radio_Button_Checkbox {
	// Khai bao 1 bien driver dai dien cho Selenium Driver
	WebDriver driver;
	JavascriptExecutor je;
	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khoi tao FF
		driver = new FirefoxDriver();
		je=(JavascriptExecutor) driver;
		// Cho cho element dc hien thi truoc khi tuong tac trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Maximize browser
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Buton() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		String myAccountLinkFooter="//div[@class='footer']//a[text()='My Account']";
		clickElementByJavaScript(myAccountLinkFooter);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		String createMyAccountButton="//a[@title='Create an Account']";
		clickElementByJavaScript(createMyAccountButton);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
		Thread.sleep(2000);
		
	}

	@Test
	public void TC_02_Checkbox() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		//driver.findElement(By.xpath("//input[@id='eq3']")).click();
		String checkBoxXpathLocatorLabel="//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		clickElementByJavaScript(checkBoxXpathLocatorLabel);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		Thread.sleep(2000);
		
		deSelectCheckbox(checkBoxXpathLocatorLabel);
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
	}
	
	

	@Test
	public void TC_03_RadioButton() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		String xPathLocatorInput="//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		//driver.findElement(By.xpath(xPathLocator)).click();
		
		Thread.sleep(2000);
		clickCheckbox_Radio(xPathLocatorInput);
		Assert.assertTrue(driver.findElement(By.xpath(xPathLocatorInput)).isSelected());
		
	}
	
	@Test
	public void TC_04_AcceptAlert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert alert=driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		Thread.sleep(1500);
		alert.accept();
		
		Thread.sleep(1500);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
	}
	
	@Test
	public void TC_05_ConfirmAlert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert alert=driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		Thread.sleep(1500);
		alert.dismiss();
		
		Thread.sleep(1500);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}
	
	@Test
	public void TC_06_PromptAlert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert alert=driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		Thread.sleep(1500);
		String textAlert="This is a test";
		alert.sendKeys(textAlert);
		alert.accept();
		
		Thread.sleep(1500);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: "+textAlert);
		
	}
	
	@Test
	public void TC_07_AuthenticationAlert() throws InterruptedException {
		String url="http://the-internet.herokuapp.com";
		String username="admin";
		String password="admin";
		
		driver.get(url);
		//driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
		
		url=urlAuthentication(url, username, password)+"/basic_auth";
		driver.get(url);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickElementByJavaScript(String xPathLocator) {
		WebElement element=driver.findElement(By.xpath(xPathLocator));
		je.executeScript("arguments[0].click();", element);
	}
	
	public void clickCheckbox_Radio(String xPatchLocator) {
		WebElement element=driver.findElement(By.xpath(xPatchLocator));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void deSelectCheckbox(String xPathLocator) {
		WebElement element=driver.findElement(By.xpath(xPathLocator));
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public String urlAuthentication(String url, String username, String password) {
		
		String[] splitUrl=url.split("//");
		
		url=splitUrl[0]+username+":"+password+ "@" + splitUrl[1];
		
		return url;
	}
}