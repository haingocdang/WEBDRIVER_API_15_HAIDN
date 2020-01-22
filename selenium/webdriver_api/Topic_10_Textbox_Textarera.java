package webdriver_api;

import java.util.Random;
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

import jdk.management.resource.internal.inst.RandomAccessFileRMHooks;

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
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		String customerName="Hai Dang";
		// sys
		String dateOfBirth="1984-05-26";
		String address="72 Phan Dang Luu";
		String city="Sai Gon";
		String state="Phu Nhuan";
		String pin="123456";
		String mobilePhoneNumber="0919111111";
		String emailAddress="hai.dang"+randomNumber()+"@yopmail.com";
		String password="123456";
		
		driver.findElement(By.name("name")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']"));
		driver.findElement(By.id("dob")).sendKeys(dateOfBirth);
		driver.findElement(By.name("addr")).sendKeys(address);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pin);
		driver.findElement(By.name("telephoneno")).sendKeys(mobilePhoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		String customID=driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		//String cus=driver.findElement(By.xpath("By.xpath(\"//td[text()='Customer Name']/following-sibling::td\"))")).getText();
		System.out.println(customID);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
	//	Assert.assertEquals(driver.findElement(By.xpath("By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobilePhoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailAddress);
		
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

	public int randomNumber() {
		Random rand=new Random();
		int number=rand.nextInt(100000);
		return number;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}