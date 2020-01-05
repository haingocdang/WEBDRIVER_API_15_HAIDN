package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Topic_02_Locator_In_Selenium {
	// Khai bao 1 bien driver dai dien cho Selenium Driver
	WebDriver driver;

	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khoi tao FF
	//	driver = new FirefoxDriver();
	
		// Khoi tao Chrome
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver_chrome79.exe");
		driver=new ChromeDriver();
		
		// Cho cho element dc hien thi truoc khi tuong tac trong 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Maximize browser
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Locator() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
//		<input 
//		id="email" 
//		class="input-text required-entry validate-email" 
//		type="email" title="Email Address" 
//		value="" name="login[username]"
//		spellcheck="false" 
//		autocorrect="off" 
//		autocapitalize="off"/>
		
		// Thao tac vs Email 
		//ID
		driver.findElement(By.id("email")).sendKeys("Autotest@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		//NAME
		driver.findElement(By.name("send")).click();
		
		//CLASS (NEWSLETTER)
		driver.findElement(By.className("validate-email")).clear();
		driver.findElement(By.className("validate-email")).sendKeys("name@gmail.com");
		
		//TAG NAME (Tim bao nhieu link va in ra)
		//Dem bao nhieu Element tren page
		List <WebElement> links= driver.findElements(By.tagName("a"));
		System.out.println("Tong so link="+links.size());
		for(WebElement link:links) {
			System.out.println("Value="+link.getAttribute("href"));
		}
		
		// LINK TEXT (Link)
		/*
		driver.findElement(By.linkText("ORDERS AND RETURNS")).click(); //Chu thuong se khong dectect dc
		
		// PARTIAL LINK TEXT (link)
		driver.findElement(By.partialLinkText("ORDERS AND")).click();//Chu thuong se khong dectect dc
		*/
		//driver.findElement(By.partialLinkText("and Returns")).click();
		
		//CSS
		driver.findElement(By.cssSelector("input[name='login[username]']")).clear();
		driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("css_name@gmail.com"); //NAME
		
		driver.findElement(By.cssSelector(".validate-email")).clear();// CLASS
		driver.findElement(By.cssSelector(".validate-email")).sendKeys("css_class@gmail.com");// CLASS
		
		System.out.println("The a dung css="+ driver.findElements(By.cssSelector("a")).size()); //TAG NAME
		// driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/sales/guest/form/']")).click();// LINK TEXT
		
		//XPATH
		driver.findElement(By.xpath("//input[@id='email']")).clear();; // id
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xpath_id@gmail.com"); // id
		
		driver.findElement(By.xpath("//input[@name='login[username]']")).clear(); //NAME
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("xpath_name@gmail.com"); //NAME
		
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).clear();
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).sendKeys("xpath_class@gmail.com");// CLASS
		
		System.out.println("The a dung XPATH="+ driver.findElements(By.xpath("//a")).size()); //TAG NAME
		driver.findElement(By.xpath("//a[@href='http://live.demoguru99.com/index.php/sales/guest/form/']")).click();// LINK TEXT
		
		
	//	driver.findElement(By.name("login[username]")).sendKeys("Autotest@gmail.com");
		
	//	driver.findElement(By.className("input-text required-entry validate-email")).sendKeys("");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}