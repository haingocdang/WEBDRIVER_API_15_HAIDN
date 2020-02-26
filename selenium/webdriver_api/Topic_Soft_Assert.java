package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Topic_Soft_Assert {
	SoftAssert soft;
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		soft = new SoftAssert();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		
		checkEquals(true, false);

		checkEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field. Failed");

		checkEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");

		checkTrue(isElementDisplayed("//button[@id='send2']"));

		checkTrue(isElementDisplayed("//button[@id='send_failed_1']"));

		checkTrue(isElementDisplayed("//button[@id='send_failed_2']"));

		checkTrue(isElementDisplayed("//button[@id='send_failed_3']"));
		

		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isElementDisplayed(String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			System.out.println("Exception = " + e.getMessage());
			return false;
		}
	}

	public boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			soft.assertTrue(condition);
		} catch (Throwable e) {
			System.out.println("Exception = " + e.getMessage());
			pass = false;
		}
		return pass;
		
	}

	public boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			soft.assertEquals(actual, expected);
		} catch (Throwable e) {
			System.out.println("Exception = " + e.getMessage());
			pass = false;
		}
		return pass;
	}

}