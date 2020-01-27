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
import org.openqa.selenium.support.ui.Select;


public class Topic_10_Selectbox {
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
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
	}

	@Test
	public void TC_01_Dopdownlist() throws InterruptedException {
		WebElement jobRole1Element=driver.findElement(By.id("job1"));
		Select jobRole1Selctbox=new Select(jobRole1Element);
		
		Assert.assertFalse(jobRole1Selctbox.isMultiple());
		
		jobRole1Selctbox.selectByVisibleText("Mobile Testing");
		//Thread.sleep(5000);
		Assert.assertEquals(jobRole1Selctbox.getFirstSelectedOption().getText(),"Mobile Testing");
		
		jobRole1Selctbox.selectByValue("manual");
		Assert.assertEquals(jobRole1Selctbox.getFirstSelectedOption().getAttribute("value"),"manual");
		
		jobRole1Selctbox.selectByIndex(9);
		Thread.sleep(5000);
		Assert.assertEquals(jobRole1Selctbox.getFirstSelectedOption().getText(),"Functional UI Testing");
		
		WebElement jobRole2Element=driver.findElement(By.id("job2"));
		Select jobRole2Selctbox=new Select(jobRole2Element);
		
		Assert.assertTrue(jobRole2Selctbox.isMultiple());
		jobRole2Selctbox.selectByVisibleText("Automation");
		jobRole2Selctbox.selectByVisibleText("Mobile");
		jobRole2Selctbox.selectByVisibleText("Desktop");
		
		List<WebElement> options=jobRole2Selctbox.getAllSelectedOptions();
		for(WebElement list:options) {
	//		Assert.assertEquals(actual, expected);
			System.out.println(list.getText());
		}
		


		

	}

//	@Test
	public void TC_02_() {
		driver.get("456789e333w");

	}

//	@Test
	public void TC_03_() {
		driver.get("11110");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}