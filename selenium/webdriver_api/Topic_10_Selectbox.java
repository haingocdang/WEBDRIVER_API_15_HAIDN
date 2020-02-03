package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jdk.nashorn.internal.runtime.options.Options;

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
		
		
		
		
	}

//	@Test
	public void TC_01_Dopdownlist() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement jobRole1Element=driver.findElement(By.id("job1"));
		Select jobRole1Selctbox=new Select(jobRole1Element);
		
		Assert.assertFalse(jobRole1Selctbox.isMultiple());
		
		jobRole1Selctbox.selectByVisibleText("Mobile Testing");
		//Thread.sleep(5000);
		Assert.assertEquals(jobRole1Selctbox.getFirstSelectedOption().getText(),"Mobile Testing");
		
		jobRole1Selctbox.selectByValue("manual");
		Assert.assertEquals(jobRole1Selctbox.getFirstSelectedOption().getAttribute("value"),"manual");
		
		jobRole1Selctbox.selectByIndex(9);
	//	Thread.sleep(5000);
		Assert.assertEquals(jobRole1Selctbox.getFirstSelectedOption().getText(),"Functional UI Testing");
		
		Assert.assertEquals(jobRole1Selctbox.getOptions().size(), 10);
		
		WebElement jobRole2Element=driver.findElement(By.id("job2"));
		Select jobRole2Selctbox=new Select(jobRole2Element);
		
		Assert.assertTrue(jobRole2Selctbox.isMultiple());
		
		jobRole2Selctbox.selectByVisibleText("Automation");
		jobRole2Selctbox.selectByVisibleText("Mobile");
		jobRole2Selctbox.selectByVisibleText("Desktop");
		
		List<String> arrayList=new ArrayList<String>();
		arrayList.add("Automation");
		arrayList.add("Mobile");
		arrayList.add("Desktop");
		List<WebElement> options=jobRole2Selctbox.getAllSelectedOptions();
		
		for(WebElement list:options) {
			Assert.assertEquals(list.getText(), arrayList.get(options.indexOf(list)));
			System.out.println(arrayList.get(options.indexOf(list)));
		}
		
				
		jobRole2Selctbox.deselectByVisibleText("Automation");
		jobRole2Selctbox.deselectByVisibleText("Mobile");
		jobRole2Selctbox.deselectByVisibleText("Desktop");
		
		
		List<WebElement> deselectOptions=jobRole2Selctbox.getAllSelectedOptions();
		Assert.assertEquals(deselectOptions.size(), 0);
		
	}

//	@Test
	public void TC_02_() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Hai");
		driver.findElement(By.id("LastName")).sendKeys("Dang");
		
		Select daySelectbox=new Select(driver.findElement(By.name("DateOfBirthDay")));
		Select monthSelectbox=new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Select yearSelectbox=new Select(driver.findElement(By.name("DateOfBirthYear")));
		
	//	Thread.sleep(3000);
		
		daySelectbox.selectByVisibleText("1");
		monthSelectbox.selectByVisibleText("May");
		yearSelectbox.selectByVisibleText("1980");
		
		Assert.assertEquals(daySelectbox.getOptions().size(), 32);
		Assert.assertEquals(monthSelectbox.getOptions().size(), 13);
		Assert.assertEquals(yearSelectbox.getOptions().size(), 112);
		
		String emailAddress="hai.dang" + randomNumber() +"@yopmail.com";
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());

	}

	@Test
	public void TC_03_CustomSelectbox() throws InterruptedException {
		
	/*	driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.findElement(By.id("number-button")).click();
		String option="19";
		JavascriptExecutor je = (JavascriptExecutor) driver;
		List <WebElement> numberSelectbox=driver.findElements(By.xpath("//ul[@id='number-menu']/li"));
		for(WebElement number:numberSelectbox) {
			if (number.getText().contains(option)) {
				je.executeScript("arguments[0].scrollIntoView(true);",number);
				Thread.sleep(2000);
				number.click();
				break;
			}
			System.out.println(number.getText());
		}
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+ option +"']")).isDisplayed());*/
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		By numberCustomDropdownBy= By.id("number-button");
		By allNumberBy=By.xpath("//ul[@id='number-menu']/li");
		String expectedNumber="19";
		selectItemsinCustomDropDown(expectedNumber, numberCustomDropdownBy, allNumberBy);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+ expectedNumber +"']")).isDisplayed());
	/*	driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		String optionGames="Tennis";
		driver.findElement(By.id("games")).click();
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		List<WebElement> gameSelectbox=driver.findElements(By.xpath("//ul[@id='games_options']/li"));
		for (WebElement game:gameSelectbox) {
			if (game.getText().contains(optionGames)) {
				je.executeScript("arguments[0].scrollIntoView(true);",game);
				Thread.sleep(2000);
				game.click();
				break;
			}
		}
		System.out.println(driver.findElement(By.xpath("//select[@id='games_hidden']/option")).getText());
		Assert.assertTrue(driver.findElement(By.id("games_hidden")).isEnabled());*/

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber(){
		Random rand=new Random();
		int number= rand.nextInt(10000);
		
		return number;
	}
	
	public void selectItemsinCustomDropDown(String expectedOption,By parentSelectBox, By allOptions) {
	//	String optionGames="Tennis";
	//	driver.findElement(By.id("games")).click();
		driver.findElement(parentSelectBox).click();
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		List<WebElement> Selectbox=driver.findElements(allOptions);
		for (WebElement option:Selectbox) {
			if (option.getText().contains(expectedOption)) {
				je.executeScript("arguments[0].scrollIntoView(true);",option);
			//	Thread.sleep(2000);
				option.click();
				break;
			}
		}
		
		/*	driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.findElement(By.id("number-button")).click();
		String option="19";
		JavascriptExecutor je = (JavascriptExecutor) driver;
		List <WebElement> numberSelectbox=driver.findElements(By.xpath("//ul[@id='number-menu']/li"));
		for(WebElement number:numberSelectbox) {
			if (number.getText().contains(option)) {
				je.executeScript("arguments[0].scrollIntoView(true);",number);
				Thread.sleep(2000);
				number.click();
				break;
			}
			System.out.println(number.getText());
		}*/
		
	}
	
	
}