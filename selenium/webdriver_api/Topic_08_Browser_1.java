package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Browser_1 {
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
	public void TC_01_Url() {
		
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
		
	}

	@Test
	public void TC_02_Title() {
		
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		

	}

	@Test
	public void TC_03_Navigation() {
		
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}
	
	@Test
	public void TC_04_PageSource() {
		
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		
		String pageSourceLogin=driver.getPageSource();
		System.out.println(pageSourceLogin);
		Assert.assertTrue(pageSourceLogin.contains("Login or Create an Account"));
		
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String pageSourceCreateAccount=driver.getPageSource();
		Assert.assertTrue(pageSourceCreateAccount.contains("Create an Account"));
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}