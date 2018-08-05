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

	/**
	 * Evaluúa si el filtro de duración del Crucero es correcto
	 * @param driver
	 * @param logger
	 * @return
	 */
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

	/**
	 * Evaluá si por defecto los resultados son en grilla
	 * @param driver
	 * @param text
	 * @return
	 */
	public boolean evaluetViewActive(WebDriver driver, String text) {

		
		if (utilities.waitForElementPresentByXpath(driver,"//p[contains(text(), '"+text+"')]"))
			return true;
		else return false;
	}

	/**
	 * Selecciona un destino
	 * @param driver
	 * @param location
	 * @throws InterruptedException
	 */
	public void selectSailFrom(WebDriver driver, String location) throws InterruptedException {

		driver.findElement(By.id("cdc-destinations")).click();	
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(), '"+location+"')]")).click();

	}


	/**
	 * Selecciona una draución del crucero
	 * @param driver
	 * @param duration
	 */
	public void selectDuration(WebDriver driver, String duration) {

		driver.findElement(By.id("cdc-durations")).click();
		driver.findElement(By.xpath("//button[contains(text(), '"+duration+"')]")).click();
		utilities.waitForElementPresentByXpath(driver,"//a[@class='sbsc-container__reset-selections']");		
	}

	
	/**
	 * Mueve el cursor minimo de la barra desplazable del filtro del precio
	 * @param driver
	 * @param i
	 * @throws InterruptedException
	 */
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

	/**
	 * Evalua si el filtro de la barra deslizante es correcto 
	 * @param driver
	 * @param min
	 * @param max
	 * @return
	 */
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

	
	/**
	 * Selecciona la opción de ordenar el precio
	 * @param driver
	 */
	public void selectSortPrice(WebDriver driver) {
		
		
		utilities.waitForElementPresentByXpath(driver, "//a[@class='sbsc-container__sort-options']");
		driver.findElement(By.xpath("//a[@class='sbsc-container__sort-options']")).click();
		utilities.waitForElementPresentByXpath(driver,"//a[@class='sbsc-container__reset-selections']");	
		
	}
	
	/**
	 * Evalúa si los cruceros se ordenaron por precio de forma descendente
	 * @param driver
	 * @param logger
	 * @return
	 */
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

	/**
	 * Selecciona un crucero por posición
	 * @param driver
	 * @param i
	 */
	public void selecCruiseByIndex(WebDriver driver, int i) {
				
		
		for (int i1 = 0; i1<=i; i1++) {
			
			if (i1==i) driver.findElement(By.xpath("//ccl-route-map")).click();
			
		}
		utilities.waitForElementPresentByXpath(driver, "//div[@class='itinerary-menu']");
	}
	
	
}


