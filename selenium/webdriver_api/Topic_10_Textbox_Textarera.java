package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	String customerID;
	String customerName="Hai Dang";
	String dateOfBirth="1984-05-26";
	String address="72 Phan Dang Luu\n24 Phan Dang Luu";
	By cusNameBy=By.name("name");
	By nameBy=By.name("name");
	By dateofBirthBY=By.id("dob");
	By addressBy=By.name("addr");
	By cityBy=By.name("city");
	By stateBy=By.name("state");
	By pinBy=By.name("pinno");
	By mobileNumBy=By.name("telephoneno");
	By emailBy=By.name("emailid");
	By passBy=By.name("password");
	
	
	
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
	public void TC_01_NewCustomer() throws InterruptedException {
				
		String welcomeMessage=driver.findElement(By.xpath("//marquee")).getText();
		Assert.assertTrue(welcomeMessage.contains("Welcome To Manager's Page of Guru99 Bank"));
		
		String managerID=driver.findElement(By.xpath("//td[contains(text(),'Manger Id')]")).getText();
	
		Assert.assertTrue(managerID.contains("Manger Id : "+ userName));
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

	
		String city="Sai Gon";
		String state="Phu Nhuan";
		String pin="123456";
		String mobilePhoneNumber="0919111111";
		String emailAddress="hai.dang"+randomNumber()+"@yopmail.com";
		String password="123456";
		
	/*	driver.findElement(By.name("name")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']"));
		driver.findElement(By.id("dob")).sendKeys(dateOfBirth);
		driver.findElement(By.name("addr")).sendKeys(address);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pin);
		driver.findElement(By.name("telephoneno")).sendKeys(mobilePhoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("password")).sendKeys(password);*/
		
		inputData(cusNameBy, customerName);
		driver.findElement(By.xpath("//input[@value='m']"));
		inputData(dateofBirthBY, dateOfBirth);
		inputData(addressBy, address);
		inputData(cityBy, city);
		inputData(stateBy, state);
		inputData(pinBy, pin);
		inputData(mobileNumBy, mobilePhoneNumber);
		inputData(emailBy, emailAddress);
		inputData(passBy, password);
		driver.findElement(By.name("sub")).click();
		
		customerID=driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		//String cus=driver.findElement(By.xpath("By.xpath(\"//td[text()='Customer Name']/following-sibling::td\"))")).getText();
		System.out.println(customerID);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
	//	Assert.assertEquals(driver.findElement(By.xpath("By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
	//	System.out.println(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobilePhoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailAddress);
		
	//	Manger Id : mngr242838
		
	/*	driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td/input")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).getText(), address);
		
		
		String newAddress="1A Phan Xich Long";
		String newCity="Ha Noi";
		String newState="Hoan Kiem";
		String newPin="789123";
		String newMobilePhoneNumber="0919222222";
		String newEmailAddress="haigialai"+randomNumber()+"@yopmail.com";
		
			inputData(addressBy, newAddress);
		inputData(cityBy, newCity);
		inputData(stateBy, newState);
		inputData(pinBy, newPin);
		inputData(mobileNumBy, newMobilePhoneNumber);
		inputData(emailBy, newEmailAddress);
		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), newPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), newMobilePhoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), newEmailAddress);*/
	}

	@Test
	public void TC_02_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td/input")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).getText(), address);
		
		
		String newAddress="1A Phan Xich Long";
		String newCity="Ha Noi";
		String newState="Hoan Kiem";
		String newPin="789123";
		String newMobilePhoneNumber="0919222222";
		String newEmailAddress="haigialai"+randomNumber()+"@yopmail.com";
		
	/*	driver.findElement(By.name("addr")).sendKeys(newAddress);
		driver.findElement(By.name("city")).sendKeys(newCity);
		driver.findElement(By.name("state")).sendKeys(newState);
		driver.findElement(By.name("pinno")).sendKeys(newPin);
		driver.findElement(By.name("telephoneno")).sendKeys(newMobilePhoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(newEmailAddress);
		driver.findElement(By.name("sub")).click();*/
		
		inputData(addressBy, newAddress);
		inputData(cityBy, newCity);
		inputData(stateBy, newState);
		inputData(pinBy, newPin);
		inputData(mobileNumBy, newMobilePhoneNumber);
		inputData(emailBy, newEmailAddress);
		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), newPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), newMobilePhoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), newEmailAddress);
	}


	public int randomNumber() {
		Random rand=new Random();
		int number=rand.nextInt(100000);
		return number;
	}
	
	public void inputData (By by, String data) {
		WebElement element=driver.findElement(by); 
		element.clear();
		element.sendKeys(data);
		
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}