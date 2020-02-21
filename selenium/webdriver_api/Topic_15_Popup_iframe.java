package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Popup_iframe {
	// Khai bao 1 bien driver dai dien cho Selenium Driver
	WebDriver driver;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khoi tao FF
		driver = new FirefoxDriver();
		
		// Cho cho element dc hien thi truoc khi tuong tac trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Maximize browser
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_() throws InterruptedException {
		driver.get("https://kyna.vn");
		
		List<WebElement> fancyPopup=driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Fancy box size:"+fancyPopup.size());
		
		if (fancyPopup.size()>0) {
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		String facebookifrane="//iframe[contains(@src,'facebook.com/kyna.vn')]";
		driver.switchTo().frame(driver.findElement(By.xpath(facebookifrane)));
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "170K likes");
		
		
		driver.switchTo().defaultContent();
		driver.findElement(By.className("button-login")).click();
		driver.findElement(By.id("user-login")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("user-password")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("btn-submit-login")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='user']")).getText(),"Automation FC");
		///driver.findElement(By.xpath("//a[text()='Kyna.vn']")).click();
		
		Thread.sleep(3000);
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}