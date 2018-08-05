package functions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * Contiene funciones útiles trasversales
 * @author Andres
 *
 */
public class UsefulMethods {


	/**
	 * Manejo de tiempos Explicitos
	 * @param driver
	 * @param idelement
	 */
	public void waitForElementPresentByID(WebDriver driver, String idelement){


		try {
			WebDriverWait wait = new WebDriverWait(driver,  30,  2000);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(idelement)));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(idelement)));
			System.out.println("The element : "+idelement+" is present.");

		}
		catch (Exception ex ){

			System.out.println("The element: "+idelement+"is not present.");
		}



	}


	/**
	 * Manejo de tiempos Explicitos
	 * @param driver
	 * @param idelement
	 */
	public boolean waitForElementPresentByXpath(WebDriver driver, String xpathelement){

		try {
			WebDriverWait wait = new WebDriverWait(driver,  30,  2000);

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathelement))); 
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathelement)));
			System.out.println("The element : "+xpathelement+" is present." );
			return true;

		}
		catch (Exception ex ){
			System.out.println("The element: "+xpathelement+"is not present.");
			return false;
		}



	}


	/**
	 * Evalúa un arreglo de valores enternos y determina si está ordenado descendentemente
	 * @param priceValue
	 * @return
	 */
	public boolean evaluateIsSortDescending (List<Integer> priceValue) {
	
			  for(int i=0; i < priceValue.size()-1; i++) {
			    if(priceValue.get(i) < priceValue.get(i+1)) {
			       return false;
			    }
			  }
			  return true;
		}
		
			
	




}

