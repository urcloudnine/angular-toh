package com.homecompany.testfortoh;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.List;


public class MainScenarios	{

	private static WebDriver driver;
 
	@BeforeClass
	public static void setup()	{
	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
		driver.get("http://localhost:4200");
		
	}

	@Test
	public void Test01()	{	//_CreateAndSearchHeroes
	
		// Creating 4 heroes with different "cu"-patterns (one of them with uppercase "C")
	
		WebElement HeroesButton = driver.findElement(By.xpath("//a[text()='Heroes']"));
		HeroesButton.click();
		WebElement NewHeroBox = driver.findElement(By.tagName("input"));
		NewHeroBox.sendKeys("Cutereal");
		WebElement NewHeroAddButton = driver.findElement(By.xpath("//button[text()=' add ']"));
		NewHeroAddButton.click();
	
		HeroesButton = driver.findElement(By.xpath("//a[text()='Heroes']"));
		HeroesButton.click();
		NewHeroBox = driver.findElement(By.tagName("input"));
		NewHeroBox.sendKeys("Realcute");
		NewHeroAddButton = driver.findElement(By.xpath("//button[text()=' add ']"));
		NewHeroAddButton.click();
	
		HeroesButton = driver.findElement(By.xpath("//a[text()='Heroes']"));
		HeroesButton.click();
		NewHeroBox = driver.findElement(By.tagName("input"));
		NewHeroBox.sendKeys("Realcu");
		NewHeroAddButton = driver.findElement(By.xpath("//button[text()=' add ']"));
		NewHeroAddButton.click();
		
		HeroesButton = driver.findElement(By.xpath("//a[text()='Heroes']"));
		HeroesButton.click();
		NewHeroBox = driver.findElement(By.tagName("input"));
		NewHeroBox.sendKeys("Real cute");
		NewHeroAddButton = driver.findElement(By.xpath("//button[text()=' add ']"));
		NewHeroAddButton.click();
		
		// Search all heroes with "cu"-pattern (case-sensitive check)
		
		WebElement DashboardButton = driver.findElement(By.xpath("//a[text()='Dashboard']"));
		DashboardButton.click();
	
		WebElement FillSearchBox = driver.findElement(By.id("search-box"));
		FillSearchBox.sendKeys("cu");
		List<WebElement>HeroesFound = driver.findElements(By.partialLinkText("cu"));
		Assert.assertTrue(HeroesFound.size()==3);
		FillSearchBox.clear();	
	
		// Search not existing hero
	
		FillSearchBox.sendKeys("curl");
		HeroesFound = driver.findElements(By.partialLinkText("curl"));
		Assert.assertTrue(HeroesFound.size()==0);
		FillSearchBox.clear();	
		
		// Search existing hero
		
		FillSearchBox.sendKeys("Cutereal");
		HeroesFound = driver.findElements(By.partialLinkText("Cutereal"));
		Assert.assertTrue(HeroesFound.size()==1);
		FillSearchBox.clear();	
		
	}
	
	@Test
	public void Test02()	{	//_CreateAndChangeSuperHero
	
		// Extract number of the last Hero
		
		WebElement HeroesButton = driver.findElement(By.xpath("//a[text()='Heroes']"));
		HeroesButton.click();
		WebElement HeroNumber = driver.findElement(By.xpath("//app-heroes/ul/li[last()]/a/span"));
		String RawNumber = HeroNumber.getText();
		int CheckNumber = Integer.parseInt(RawNumber);
		CheckNumber++;
		
		// Create new Super Hero
				
		WebElement NewHeroBox = driver.findElement(By.tagName("input"));
		NewHeroBox.sendKeys("Super Hero");
		WebElement NewHeroAddButton = driver.findElement(By.xpath("//button[text()=' add ']"));
		NewHeroAddButton.click();
				
		// Change Super Hero name to Super Duper Hero
		
		String XpathStringForSearch = "//a[@href='/detail/"+CheckNumber+"']";
		WebElement HeroDetails = driver.findElement(By.xpath(""+XpathStringForSearch));
		HeroDetails.click();
		WebElement HeroChangeBox = driver.findElement(By.xpath("//label[text()='name: ']/input"));
		HeroChangeBox.clear();
		HeroChangeBox.sendKeys("Super Duper Hero");
		WebElement SaveChangesButton = driver.findElement(By.xpath("//button[text()='save']"));
		SaveChangesButton.click();
		
		// Check that new name has been saved
		
		WebElement NewHeroDetails = driver.findElement(By.xpath(""+XpathStringForSearch));
		String CheckName = NewHeroDetails.getText();
		Assert.assertEquals(CheckNumber+" Super Duper Hero", CheckName);
				
	}
	
	@Test
	public void Test03()	{	//_ClearLogs
	
		// Check that there are some log lines
	
		List<WebElement>HeroLogs = driver.findElements(By.xpath("//app-messages/div/div"));
		Assert.assertTrue(HeroLogs.size()>0);
		
		// Clear logs using "Clear" button
		
		WebElement ClearLogButton = driver.findElement(By.xpath("//button[text()='clear']"));
		ClearLogButton.click();
		HeroLogs = driver.findElements(By.xpath("//app-messages/div/div"));
		Assert.assertTrue(HeroLogs.size()==0);
		
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
