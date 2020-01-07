package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_05_Xpath_Css {
	// Khai bao 1 bien driver dai dien cho Selenium Driver
	WebDriver driver;
	String firstName="Hai";
	String lastName="Dang";
	String middleName="Ngoc";
	String emailAddress="haidang1232";
	String password="123456";
	
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
	
	@BeforeMethod
	public void beforemethod() {
		// TODO Auto-generated method stub
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
	}

	@Test
	public void TC_01_LogiWithEmailAndPasswordEmpty() {
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		
		String emailErrorMesg= driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMesg, "This is a required field.");
		
		String passErrorMsg=driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passErrorMsg, "This is a required field.");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12344123@12365.1236");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String validEmailErrorMsg=driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(validEmailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	@Test
	public void TC_03_LogiWithPassLessThan6Chars() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String validPassErrorMsg=driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(validPassErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_LogiWithInCorrectEmailAndPass() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailandpassErrorMsg=driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(emailandpassErrorMsg, "Invalid login or password.");
		
	}
	
	@Test
	public void TC_05_CreateAccount() throws InterruptedException {
		int randomNumber= randomNumber();
		
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//a[@title='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(middleName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress + randomNumber+"@yopmail.com" );
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='You are now logged out']")).isDisplayed());
		
		//Sleep
		Thread.sleep(3000);
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "http://live.demoguru99.com/index.php/");
	}

	
	@Test
	public void TC_06_LogiWithValidEmailAndPass() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress+"@yopmail.com");
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String dashboardTitle=driver.findElement(By.xpath("//div[@class='dashboard']//h1")).getText();
		Assert.assertEquals(dashboardTitle, "MY DASHBOARD");
		
		String welcomeMsg=driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText();
		String fullName= firstName + " " + middleName + " " + lastName;
		
		Assert.assertEquals(welcomeMsg, "Hello, " + fullName + "!");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-account box-info']//p[contains(text(),'"+ fullName +"')]")).isDisplayed());
				
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-account box-info']//p[contains(.,'"+ emailAddress+ "@yopmail.com')]")).isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random rand=new Random();
		int number=rand.nextInt(100000);
		return number;
	}

}