package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_10_Textbox_Textarera {
	// Khai bao 1 bien driver dai dien cho Selenium Driver
	WebDriver driver;
	String userName="mngr242838";
	String passWord="edyjapY";
	
	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khoi tao FF
		driver = new FirefoxDriver();
		
		// Cho cho element dc hien thi truoc khi tuong tac trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Maximize browser
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/v4/");
		
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWord);
		driver.findElement(By.name("btnLogin")).click();
		
	}

	
	@Test
	public void TC_01_NewCustomer() {
				
		String welcomeMessage=driver.findElement(By.xpath("//marquee")).getText();
		Assert.assertTrue(welcomeMessage.contains("Welcome To Manager's Page of Guru99 Bank"));
		
		String managerID=driver.findElement(By.xpath("//td[contains(text(),'Manger Id')]")).getText();
	
		Assert.assertTrue(managerID.contains("Manger Id : "+ userName));
		
	//	Manger Id : mngr242838
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