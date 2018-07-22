package com.homecompany.testfortoh;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.Thread;
import java.util.concurrent.TimeUnit;
import java.util.List;


public class MainScenarios {

	private static WebDriver driver;
 
	@BeforeClass
	public static void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://localhost:4200");
	}

	@Test
	public void Test01() {	//_SearchHeroByTwoLetters
		WebElement FillSearchBox = driver.findElement(By.id("search-box"));
		FillSearchBox.sendKeys("Ma");
		try			
		{			  
			Thread.sleep (1000); 	// ExpectedConditions should be used instead, but versioning of dependencies killed all my time, therefore - leaving it as is
			List<WebElement>HeroesFound = driver.findElements(By.partialLinkText("Ma"));
			System.out.println(HeroesFound.size()+ " heroes found");
			Assert.assertTrue(HeroesFound.size()==4);
			FillSearchBox.clear();			
		}		  
		catch (InterruptedException interruptedException)		  
		{			  
			System.out.println( "Interrupted Exception. Beware of the dog" +interruptedException);		  
		}
	}
	
	@Test
	public void Test02() {	//_SearchHeroNotExist
		WebElement FillSearchBox = driver.findElement(By.id("search-box"));
		FillSearchBox.sendKeys("Magneto");
		try			
		{			  
			Thread.sleep (1000); 	// ExpectedConditions should be used instead, but versioning of dependencies killed all my time, therefore - leaving it as is
			List<WebElement>HeroesFound = driver.findElements(By.partialLinkText("Magneto"));
			System.out.println(HeroesFound.size()+ " heroes found");
			Assert.assertTrue(HeroesFound.size()==0);   
			FillSearchBox.clear();			
		}		  
		catch (InterruptedException interruptedException)		  
		{			  
			System.out.println( "Interrupted Exception. Beware of the dog" +interruptedException);		  
		}
	}
	
	@Test
	public void Test03() {	//_SearchHeroExist
		WebElement FillSearchBox = driver.findElement(By.id("search-box"));
		FillSearchBox.sendKeys("RubberMan");
		try			
		{			  
			Thread.sleep (1000); 	// ExpectedConditions should be used instead, but versioning of dependencies killed all my time, therefore - leaving it as is
			List<WebElement>HeroesFound = driver.findElements(By.partialLinkText("RubberMan"));
			System.out.println(HeroesFound.size()+ " heroes found");
			Assert.assertTrue(HeroesFound.size()==1);	  
		}		  
		catch (InterruptedException interruptedException)		  
		{			  
			System.out.println( "Interrupted Exception. Beware of the dog" +interruptedException);		  
		}
	}
	
	@Test
	public void Test04() {	//_CreateSuperHero
		WebElement HeroesButton = driver.findElement(By.xpath("//a[text()='Heroes']"));
		HeroesButton.click();
		WebElement NewHeroBox = driver.findElement(By.tagName("input"));
		NewHeroBox.sendKeys("Super Hero");
		WebElement NewHeroAddButton = driver.findElement(By.xpath("//button[text()=' add ']"));
		NewHeroAddButton.click();
		WebElement NewHeroDetails = driver.findElement(By.xpath("//a[@href='/detail/21']"));
		String CheckName = NewHeroDetails.getText();
		Assert.assertEquals("21 Super Hero", CheckName);
	}
	
	@Test
	public void Test05() {	//_ChangeSuperHero
		WebElement DashboardButton = driver.findElement(By.xpath("//a[text()='Dashboard']"));
		DashboardButton.click();
		WebElement HeroesButton = driver.findElement(By.xpath("//a[text()='Heroes']"));
		HeroesButton.click();
		WebElement NewHeroBox = driver.findElement(By.tagName("input"));
		NewHeroBox.sendKeys("New Super Hero");
		WebElement NewHeroAddButton = driver.findElement(By.xpath("//button[text()=' add ']"));
		NewHeroAddButton.click();
		WebElement HeroDetails = driver.findElement(By.xpath("//a[@href='/detail/22']"));
		HeroDetails.click();
		WebElement HeroChangeBox = driver.findElement(By.xpath("//label[text()='name: ']/input"));
		HeroChangeBox.clear();
		HeroChangeBox.sendKeys("New Super Duper Hero");
		WebElement SaveChangesButton = driver.findElement(By.xpath("//button[text()='save']"));
		SaveChangesButton.click();
		WebElement NewHeroDetails = driver.findElement(By.xpath("//a[@href='/detail/22']"));
		String CheckName = NewHeroDetails.getText();
		Assert.assertEquals("22 New Super Duper Hero", CheckName);
	}
	
	@Test
	public void Test06()	{	//_ClearLogs
		List<WebElement>HeroLogs = driver.findElements(By.xpath("//app-messages/div/div"));
		System.out.println(HeroLogs.size()+" elements in Hero logs");  
		Assert.assertTrue(HeroLogs.size()>0);
		WebElement ClearLogButton = driver.findElement(By.xpath("//button[text()='clear']"));
		ClearLogButton.click();
		HeroLogs = driver.findElements(By.xpath("//app-messages/div/div"));
		System.out.println(HeroLogs.size()+" elements in Hero logs");  
		Assert.assertTrue(HeroLogs.size()==0);
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}