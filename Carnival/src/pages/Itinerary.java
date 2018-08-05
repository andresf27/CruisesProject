package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functions.UsefulMethods;

public class Itinerary {


	
	UsefulMethods utilities;
	public Itinerary() {
		utilities = new UsefulMethods();

	}

	/**
	 * Valida si se encuentran todos los apratados de About para el crucero seleccionado
	 * @param driver
	 * @param valueDays
	 * @return
	 */
	public boolean validateDaysItinerary(WebDriver driver, int valueDays) {



		List<WebElement> aboutButton= driver.findElements(By.xpath("//a[@class='about-cta']"));		

		if ((aboutButton.size()==valueDays+2))return true;

		else return false;	

	}

	
	/**
	 * Retorna la cantidad de días del crucero seleccionado
	 * @param driver
	 * @return
	 * @throws InterruptedException 
	 */
	public int getDaysOfCruise(WebDriver driver)  {

		String valueDays = driver.findElement(By.xpath("//div[@id='section-itinerary']//div[@class='duration-title']//span")).getText();
		System.out.println("Value Days"+valueDays);
		return Integer.parseInt(driver.findElement(By.xpath("//div[@id='section-itinerary']//div[@class='duration-title']//span")).getText());	

	}
	
	public boolean clickEachAbouButton(WebDriver driver) throws InterruptedException {
		
		List<WebElement> aboutButton= driver.findElements(By.xpath("//a[@class='about-cta']"));		

		for(WebElement e : aboutButton) {	
			e.click();		
			Thread.sleep(4000);
			if (!utilities.waitForElementPresentByXpath(driver, "//div[@class='slide ccl-negative-outline ng-scope slick-slide slick-current slick-active']")) {
				return false;
			}

		}
		return true;
		
	}

	/**
	 * Valida si el botón Book Now se encuentra visible y hace Scroll hasta este.
	 * @param driver
	 * @return
	 * @throws InterruptedException 
	 */
	public boolean validateBookNowButton(WebDriver driver) throws InterruptedException {
	
		WebElement el = driver.findElement(By.xpath("//div[@class='ready']/a/span"));
		
		if (utilities.waitForElementPresentByXpath(driver, "//div[@class='ready']/a/span")) {
						
			    if (driver instanceof JavascriptExecutor) {
			        ((JavascriptExecutor) driver)
			            .executeScript("arguments[0].scrollIntoView(true);", el);
			    }			
			    
			   Thread.sleep(4000);
			
			return true;
			
		}
		
		else return false;
		
		
	}


}
