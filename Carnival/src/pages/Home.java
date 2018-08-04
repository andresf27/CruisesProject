package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import functions.UsefulMethods;




public class Home {

	UsefulMethods utilities;
	public Home() {
		utilities = new UsefulMethods();

	}

	public boolean evaluateDurationFilter(WebDriver driver, ExtentTest logger)

	{
		
		
		List<WebElement> myElements = driver.findElements(By.xpath("//span[@class='cgc-cruise-glance__days']"));
		for(WebElement e : myElements) {	
			
			System.out.println(e.getText());
			logger.log(Status.INFO,"Duration Value Found: "+ e.getText() + "Days");	
			if (  (Integer.parseInt(e.getText())>9) || (Integer.parseInt(e.getText())<6)  ) 
				return false;

		}
		return true;
	}

	public boolean evaluetViewActive(WebDriver driver, String text) {

		driver.findElement(By.xpath("//button[contains(text(), 'The Bahamas')]")).click();
		if (utilities.waitForElementPresentByXpath(driver,"//p[contains(text(), '"+text+"')]"))
			return true;
		else return false;
	}

	public void selectSailFrom(WebDriver driver, String location) throws InterruptedException {

		driver.findElement(By.id("cdc-destinations")).click();	
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), '"+location+"')]")).click();

	}


	public void selectDuration(WebDriver driver, String duration) {

		driver.findElement(By.id("cdc-durations")).click();
		driver.findElement(By.xpath("//button[contains(text(), '"+duration+"')]")).click();
		utilities.waitForElementPresentByXpath(driver,"//a[@class='sbsc-container__reset-selections']");		
	}

	public void selectAndSetPriceFilterMinimum(WebDriver driver, int i) throws InterruptedException {
		
		
		utilities.waitForElementPresentByXpath(driver, "//a[@title='Pricing']");
		driver.findElement(By.xpath("//a[@title='Pricing']")).click();	
		utilities.waitForElementPresentByXpath(driver,"//span[@class='rz-bar-wrapper']" );
		utilities.waitForElementPresentByXpath(driver,"//span[@class='rz-pointer rz-pointer-min rz-active']" );			
		WebElement slider = driver.findElement(By.xpath("//span[@class='rz-pointer rz-pointer-min rz-active']" ));
		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(slider, i, 0).build();
		action.perform();
		Thread.sleep(2000);
		
	}

	public boolean evaluatePrice(WebDriver driver, int min, int max) {
	
		List<WebElement> priceValues= driver.findElements(By.xpath("//span[@class='vrgf-price-box__price']"));		
	
				
		for(WebElement e : priceValues) {	
			String number = e.getText().replaceAll("[^\\.0123456789]",""); //Extraigo el valor de cada crucero
			System.out.println(number);
			if (  (Integer.parseInt(number)>max || (Integer.parseInt(number)<min) ) ) 
				return false;
		}
		return true;
		
		
	}

	
	public void selectSortPrice(WebDriver driver) {
		
		
		utilities.waitForElementPresentByXpath(driver, "//a[@class='sbsc-container__sort-options']");
		driver.findElement(By.xpath("//a[@class='sbsc-container__sort-options']")).click();
		utilities.waitForElementPresentByXpath(driver,"//a[@class='sbsc-container__reset-selections']");	
		
	}
	
	public boolean evaluatePriceSortDescending(WebDriver driver, ExtentTest logger) {
		
		List<WebElement> priceValues= driver.findElements(By.xpath("//span[@class='vrgf-price-box__price']"));		
		
		List<Integer> priceValue = new ArrayList<Integer>();
				
		for(WebElement e : priceValues) {	
			String number = e.getText().replaceAll("[^\\.0123456789]",""); //Extraigo el valor de cada crucero
			System.out.println(number);
			logger.log(Status.INFO,"Price found: "+ number + "$USD");		
			priceValue.add(Integer.parseInt(number));
			
		}
		
		if (!utilities.evaluateIsSortDescending(priceValue))
			return false;
		return true;
		
		
	}
}


