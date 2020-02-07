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

//	@Test
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

//	@Test
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
	public void TC_03_() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		String xPathLocator="//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		//driver.findElement(By.xpath(xPathLocator)).click();
		
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath(xPathLocator)).isSelected());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickElementByJavaScript(String xPathLocator) {
		WebElement element=driver.findElement(By.xpath(xPathLocator));
		je.executeScript("arguments[0].click();", element);
	}
	
	public void clickCheckbox(String xPatchLocator) {
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
}