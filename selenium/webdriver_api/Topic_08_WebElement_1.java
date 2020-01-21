package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

//import com.sun.org.apache.bcel.internal.generic.Select;

public class Topic_08_WebElement_1 {
	// Khai bao 1 bien driver dai dien cho Selenium Driver
	WebDriver driver;
	By radioUnder18= By.id("under_18");
	By email= By.id("mail");
	By education= By.id("edu");
	By passwordBy=By.id("password");
	By developmentCheckboxBy=By.xpath("//input[@value='interest_development']");
	
	
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
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
	}

	@Test
	public void TC_Check_isDisplay() throws InterruptedException {
	//	WebElement emailTextbox=driver.findElement(email);
	//	WebElement educationTextarea=driver.findElement(education);
	//	WebElement radio18=driver.findElement(radioUnder18);
		String data="Automation Testing";
		
		/*Assert.assertTrue(emailTextbox.isDisplayed());
		Assert.assertTrue(radio18.isDisplayed());
		Assert.assertTrue(educationTextarea.isDisplayed());
		
		emailTextbox.sendKeys(data);
		Assert.assertEquals(emailTextbox.getText(), data);
		System.out.println(emailTextbox.getText());
		
		educationTextarea.sendKeys(data);
		Assert.assertEquals(educationTextarea.getText(), data);
		System.out.println(educationTextarea.getText());
		
		radio18.click();
		Assert.assertTrue(radio18.isSelected());*/
	/*	if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys(data);
			
		}
		
		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys(data);
		}
		
		if (radio18.isDisplayed()) {
			radio18.click();
			
		}
	}*/
		if (isElementDisplay(email)) {
			sendkeyToElement(email, data);
		}
		String value=driver.findElement(By.id("password")).getAttribute("placeholder");
		System.out.println(value);
	}

//	 @Test
	public void TC_02_Check_Enable() throws InterruptedException {
		
/*		WebElement emailTextbox=driver.findElement(email);
		WebElement radioAge18=driver.findElement(radioUnder18);
		WebElement educationTextarea=driver.findElement(education);
		WebElement job1Selectbox=driver.findElement(By.xpath("//select[@id='job1']"));
		WebElement developmentCheckbox=driver.findElement(By.xpath("//input[@value='interest_development']"));
		WebElement slider1=driver.findElement(By.xpath("//input[@id='slider-1']"));
		
		if (emailTextbox.isEnabled()) {
			System.out.println( "Element is enabled");
		}

		if (radioAge18.isEnabled()) {
			System.out.println("Element is enabled");
		}
		
		if (educationTextarea.isEnabled()) {
			System.out.println("Element is enabled");
		}
		
		if (job1Selectbox.isEnabled()) {
			System.out.println("Element is enabled");
		}
		
		if (developmentCheckbox.isEnabled()) {
			System.out.println("Element is enabled");
		}
		
		if (slider1.isEnabled()){
			System.out.println("Element is enabled");
		}
		
		WebElement passwordTextbox=driver.findElement(By.id("password"));
		WebElement radioAgeDisable=driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		WebElement bioTextarea=driver.findElement(By.id("bio"));
		WebElement jobRole2Textbox=driver.findElement(By.xpath("//select[@id='job2']/option[@value='automation']"));
		WebElement checkboxDisable=driver.findElement(By.id("check-disbaled"));
		WebElement slider2=driver.findElement(By.id("slider-2"));
		
		if (passwordTextbox.isEnabled()) {
			System.out.println("Element is enabled");
			
		} else {
			System.out.println("Element is disabled");

		}
						
		if (radioAgeDisable.isEnabled()) {
			System.out.println("Element is enabled");
			
		} else {
			System.out.println("Element is disabled");

		}
		
		if (bioTextarea.isEnabled()) {
			System.out.println("Element is enabled");
			
		} else {
			System.out.println("Element is disabled");

		}
		
		if (jobRole2Textbox.isEnabled()) {
			System.out.println("Element is enabled");
			
		} else {
			System.out.println("Element is disabled");

		}
		
		if (checkboxDisable.isEnabled()) {
			System.out.println("Element is enabled");
			
		} else {
			System.out.println("Element is disabled");

		}
		
		if (slider2.isEnabled()) {
			System.out.println("Element is enabled");
			
		} else {
			System.out.println("Element is disabled");

		}*/
		Assert.assertTrue(ischeckElementEnable(email));
		Assert.assertTrue(ischeckElementEnable(education));
		Assert.assertTrue(ischeckElementEnable(radioUnder18));
		Assert.assertFalse(ischeckElementEnable(passwordBy));
		// Assert.assertFalse(ischeckElementEnable(jobRole2SelectBoxBy));
		
		By job3DropdownBy=By.id("job3");
		Assert.assertFalse(ischeckElementEnable(job3DropdownBy));
		
		By jobRole2SelectBoxBy=By.id("job2");
		Select job2Select=new Select(driver.findElement(jobRole2SelectBoxBy));
		job2Select.selectByValue("manual");
		Thread.sleep(10000);
	//	job2Select.
	//	driver.findElement(jobRole2SelectBoxBy)
		String value=job2Select.getFirstSelectedOption().getText();
		System.out.println(value);
	//	driver.findElement(jobRole2SelectBoxBy).get
		
	
	}

//	@Test
	public void TC_03_IsSelected() throws InterruptedException {
		
		driver.findElement(radioUnder18).click();
//		Thread.sleep(10000);
		driver.findElement(developmentCheckboxBy).click();
		Assert.assertTrue(ischeckElementSelected(radioUnder18));
		Assert.assertTrue(driver.findElement(developmentCheckboxBy).isSelected());
		
		driver.findElement(radioUnder18).click();
		driver.findElement(developmentCheckboxBy).click();
		Assert.assertTrue(driver.findElement(radioUnder18).isSelected());
		Assert.assertFalse(ischeckElementSelected(developmentCheckboxBy));
		
	}
	
	public boolean isElementDisplay(By by) {
		WebElement element=driver.findElement(by);
		if (element.isDisplayed()) {
			return true;			
		} else {
			return false;

		}
	}
	
	public boolean ischeckElementEnable (By by) {
		WebElement element=driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by +"] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by +"] is disabled");
			return false;
		}
	}
	
	public boolean ischeckElementSelected(By by) {
		WebElement element=driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by +"] is selected");
			return true;
		} else {
			System.out.println("Element [" + by +"] is deselected");
			return false;
		}
	}
	
	public void sendkeyToElement(By by, String value) {
		WebElement element=driver.findElement(by);
		element.sendKeys(value);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}