package web.scenarios;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import functions.ReportMethods;
import functions.UsefulMethods;
import pages.Home;
import pages.Itinerary;





public class SmokeTestUserStory01And02 { 


	private static WebDriver driver;
	static ExtentReports report;
	ExtentHtmlReporter htmlReporter;
	ExtentTest logger;
	static ReportMethods reportUtility;
	static String currenTime;
	static UsefulMethods utilities;
	static String url;
	static Home home;
	static Itinerary itinerary;


	@BeforeClass 
	public static void configureBrowser () throws IOException{

		//Seteo de path para Driver de Chrome
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		url = "https://www.carnival.com/";
		report = new ExtentReports();		
		reportUtility = new ReportMethods();
		utilities = new UsefulMethods();
		home = new Home();
		itinerary= new Itinerary();
	}

	@Before
	public void configBrowser() throws Exception{


		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		ChromeOptions options =  new ChromeOptions();
		DesiredCapabilities cap;
		cap = DesiredCapabilities.chrome();		
		options.addArguments("--disable-extensions");
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		cap.acceptInsecureCerts();
		cap.setJavascriptEnabled(true);

		String currentReportPath = reportUtility.createReport();
		htmlReporter = new ExtentHtmlReporter(currentReportPath);

		report.attachReporter(htmlReporter);
		report.setSystemInfo("Host Name", "Chrome Versi�n 63");
		report.setSystemInfo("Env", "Automation Testing");
		report.setSystemInfo("User", "Andres Valenzuela");
		htmlReporter.config().setDocumentTitle("Resultado de Ejecuci&oacuten");
		htmlReporter.config().setReportName("UserStory01");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setEncoding("UTF-8");


	}

		/**
		 * Valida la funcionalidad del Filtro de Duraci�n del Crucero
		 * @throws IOException
		 * @throws InterruptedException
		 */
		@Test
		public void UserStory01_01() throws IOException, InterruptedException {
	
			try {
	
				logger = report.createTest("Test Case: UserStory01_01 Validate duration filter.");			
				logger.log(Status.INFO,"The browser load correctly");				
				driver.get(url);
				home.selectSailFrom(driver, "The Bahamas");
				home.selectDuration(driver, "6 - 9 Days");							
				if (!home.evaluateDurationFilter(driver, logger)) {			
					logger.log (Status.FAIL, MarkupHelper.createLabel("The results by filter are not correct with the previus filter selected",  ExtentColor.RED));
					Assert.fail("The results by filter are not correct with the previus filter selected");  
				}
				else logger.log (Status.PASS, MarkupHelper.createLabel("The results by filter are correct with the previus filter selected",  ExtentColor.GREEN));
						
	
			}
	
			catch (Exception ex){
	
				{
	
					
					System.out.println(ex.toString());
					ex.printStackTrace();			
					logger.fail(ex.toString());
					logger.log(Status.FAIL, MarkupHelper.createLabel("Fail" + " - Test Case Failed", ExtentColor.RED));		
					logger.fail("Last Screen").addScreenCaptureFromPath("imagen");			
					Assert.fail();
	
				}
	
	
			}
	
		}
	
		/**
		 * Eval�a que la vista por defecto de resultados sea una grilla y no una lista
		 * @throws IOException
		 * @throws InterruptedException
		 */
		@Test
		public void UserStory01_02() throws IOException, InterruptedException {
	
			try {
	
				logger = report.createTest("Test Case: UserStory01_02 Search Cruises and validate default view Grid of results.");			
				logger.log(Status.INFO,"The browser load correctly");				
				driver.get(url);
				home.selectSailFrom(driver, "The Bahamas");			
				if (!home.evaluetViewActive(driver, "Grid view active")) {			
					logger.log (Status.FAIL, MarkupHelper.createLabel("The default view is not a Grid",  ExtentColor.RED));
					Assert.fail("The default view is not a Grid");  
	
				}
				else logger.log (Status.PASS, MarkupHelper.createLabel("The default view is a Grid",  ExtentColor.GREEN));		
								
	
			}
	
			catch (Exception ex){
	
				{
	
					System.out.println(ex.toString());
					ex.printStackTrace();			
					logger.fail(ex.toString());
					logger.log(Status.FAIL, MarkupHelper.createLabel("Fail" + " - Test Case Failed", ExtentColor.RED));		
					logger.fail("Last Screen").addScreenCaptureFromPath("imagen");				
					Assert.fail();
	
				}
	
	
			}
	
		}
	
		/**
		 * Eval�a que el filtro de barra funcione y los resultados sean acordes
		 * @throws IOException
		 * @throws InterruptedException
		 */
		@Test
		public void UserStory01_03() throws IOException, InterruptedException {
	
			try {
	
				logger = report.createTest("Test Case: UserStory01_03 Validate price filter with slider bar.");			
				logger.log(Status.INFO,"The browser load correctly");				
				driver.get(url);
				home.selectSailFrom(driver, "The Bahamas");			
				home.selectAndSetPriceFilterMinimum(driver, 450);		
				if (!home.evaluatePrice(driver, 500, 2000 )) {			
					logger.log (Status.FAIL, MarkupHelper.createLabel("The slider Bar filter is not working correctly",  ExtentColor.RED));
					Assert.fail("The slider Bar filter is not working correctly");  
	
				}
				else logger.log (Status.PASS, MarkupHelper.createLabel("The slider Bar filter is working correctly",  ExtentColor.GREEN));		
							
	
			}
	
			catch (Exception ex){
	
				{
	
					System.out.println(ex.toString());
					ex.printStackTrace();			
					logger.fail(ex.toString());
					logger.log(Status.FAIL, MarkupHelper.createLabel("Fail" + " - Test Case Failed", ExtentColor.RED));		
					logger.fail("Last Screen").addScreenCaptureFromPath("imagen");				
					Assert.fail();
	
				}
	
	
			}
	
		}
		
		
		
		
		/**
		 * Eval�a que la funcionalidad de ordenamiento de precios est� correcta
		 * @throws IOException
		 * @throws InterruptedException
		 */
		@Test
		public void UserStory01_04() throws IOException, InterruptedException {
	
			try {
	
				logger = report.createTest("Test Case: UserStory01_04 Validate sort price functionality");			
				logger.log(Status.INFO,"The browser load correctly");				
				driver.get(url);
				home.selectSailFrom(driver, "The Bahamas");			
				home.selectSortPrice(driver);			
				if (!home.evaluatePriceSortDescending(driver, logger)) {			
					logger.log (Status.FAIL, MarkupHelper.createLabel("The prices are not in descending order",  ExtentColor.RED));
					Assert.fail("The prices are not in descending order");  
	
				}
				else logger.log (Status.PASS, MarkupHelper.createLabel("The prices are in descending order",  ExtentColor.GREEN));		
							
	
			}
			
			
	
			catch (Exception ex){
	
				{
	
					System.out.println(ex.toString());
					ex.printStackTrace();			
					logger.fail(ex.toString());
					logger.log(Status.FAIL, MarkupHelper.createLabel("Fail" + " - Test Case Failed", ExtentColor.RED));		
					logger.fail("Last Screen").addScreenCaptureFromPath("imagen");				
					Assert.fail();
	
				}
	
	
			}
	
		}
	
	
		/**
		 * Flujo completo o Feliz de la Historia de Usuario 1
		 * @throws IOException
		 * @throws InterruptedException
		 */
		@Test
		public void UserStory01_05() throws IOException, InterruptedException {
	
			try {
	
	
				logger = report.createTest("Test Case: UserStory01_05 Complete a search of cruises with a destination, Price slide bar filter, and Sort ");
				String url = "https://www.carnival.com/";
				logger.log(Status.INFO,"The browser load correctly");				
				driver.get(url);
				home.selectSailFrom(driver, "The Bahamas");			
				if (!home.evaluetViewActive(driver, "Grid view active")) {			
					logger.log (Status.FAIL, MarkupHelper.createLabel("The default view is not a Grid",  ExtentColor.RED));
					Assert.fail("The default view is not a Grid");  
	
				}
				else logger.log (Status.PASS, MarkupHelper.createLabel("The default view is a Grid",  ExtentColor.GREEN));				
				
				home.selectDuration(driver, "6 - 9 Days");							
				if (!home.evaluateDurationFilter(driver, logger)) {			
					logger.log (Status.FAIL, MarkupHelper.createLabel("The results by filter are not correct with the previus filter selected",  ExtentColor.RED));
					Assert.fail("The results by filter are not correct with the previus filter selected");  
				}
				else logger.log (Status.PASS, MarkupHelper.createLabel("The results by filter are correct with the previus filter selected",  ExtentColor.GREEN));
						
				
				
				home.selectAndSetPriceFilterMinimum(driver, 450);		
				if (!home.evaluatePrice(driver, 500,2000 )) {			
					logger.log (Status.FAIL, MarkupHelper.createLabel("The slider Bar filter is not working correctly",  ExtentColor.RED));
					Assert.fail("The slider Bar filter is not working correctly");  
	
				}
				else logger.log (Status.PASS, MarkupHelper.createLabel("The slider Bar filter is working correctly",  ExtentColor.GREEN));		
						
				
				
				home.selectSortPrice(driver);			
				if (!home.evaluatePriceSortDescending(driver, logger)) {			
					logger.log (Status.FAIL, MarkupHelper.createLabel("The prices are not in descending order",  ExtentColor.RED));
					Assert.fail("The prices are not in descending order");  
	
				}
				else logger.log (Status.PASS, MarkupHelper.createLabel("The prices are in descending order",  ExtentColor.GREEN));		
							
	
	
			}
	
			catch (Exception ex){
	
				{
	
					System.out.println(ex.toString());
					ex.printStackTrace();			
					logger.fail(ex.toString());
					logger.log(Status.FAIL, MarkupHelper.createLabel("Fail" + " - Test Case Failed", ExtentColor.RED));		
					logger.fail("Last Screen").addScreenCaptureFromPath("imagen");				
					Assert.fail();
	
				}
	
	
			}
	
		}

	/**
	 * Eval�a que el Crucero seleccionado muestre el itinerario por d�a correctamente
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void UserStory02_01() throws IOException, InterruptedException {

		try {

			logger = report.createTest("Test Case: UserStory02_01 Validate Itinerary of one offer");			
			logger.log(Status.INFO,"The browser load correctly");				
			driver.get(url);
			home.selectSailFrom(driver, "The Bahamas");			
			home.selecCruiseByIndex(driver,1);					
			if (!itinerary.validateDaysItinerary(driver, itinerary.getDaysOfCruise(driver))) {			
				logger.log (Status.FAIL, MarkupHelper.createLabel("The itinerary is incomplete",  ExtentColor.RED));
				Assert.fail("The itinerary is incomplete");  
			}
			else logger.log (Status.PASS, MarkupHelper.createLabel("The itinerary is complete",  ExtentColor.GREEN));		


		}



		catch (Exception ex){

			{

				System.out.println(ex.toString());
				ex.printStackTrace();				
				logger.fail(ex.toString());
				logger.log(Status.FAIL, MarkupHelper.createLabel("Fail" + " - Test Case Failed", ExtentColor.RED));		
				logger.fail("Last Screen").addScreenCaptureFromPath("imagen");				
				Assert.fail();

			}


		}

	}

	/**
	 * Eval�a si cada d�a despliega la informaci�n correcta en cada button "About"
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void UserStory02_02() throws IOException, InterruptedException {

		try {

			logger = report.createTest("Test Case: UserStory02_02 Validate each information in �About� Button over each day");			
			logger.log(Status.INFO,"The browser load correctly");				
			driver.get(url);
			home.selectSailFrom(driver, "The Bahamas");			
			home.selecCruiseByIndex(driver,1);			

			if (!itinerary.clickEachAbouButton(driver)) {			
				logger.log (Status.FAIL, MarkupHelper.createLabel("There was a problen with one Day and doesnt show Slide of Information",  ExtentColor.RED));
				Assert.fail("There was a problen with one Day and doesnt show Slide of Information");  
			}
			else logger.log (Status.PASS, MarkupHelper.createLabel("All days are correct Displayed",  ExtentColor.GREEN));		


		}



		catch (Exception ex){

			{

				System.out.println(ex.toString());
				ex.printStackTrace();				
				logger.fail(ex.toString());
				logger.log(Status.FAIL, MarkupHelper.createLabel("Fail" + " - Test Case Failed", ExtentColor.RED));		
				logger.fail("Last Screen").addScreenCaptureFromPath("imagen");				
				Assert.fail();

			}


		}

	}

	/**
	 * Valida que el bot�n Book Now se encuentre en el itinerario y realiza movimiento de p�gina hasta el mismo
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void UserStory02_03() throws IOException, InterruptedException {

		try {

			logger = report.createTest("Test Case: UserStory02_03 Validate Book Now Button");			
			logger.log(Status.INFO,"The browser load correctly");				
			driver.get(url);
			home.selectSailFrom(driver, "The Bahamas");			
			home.selecCruiseByIndex(driver,1);			

			if (!itinerary.validateBookNowButton(driver)) {			
				logger.log (Status.FAIL, MarkupHelper.createLabel("The BookNow button is not Present",  ExtentColor.RED));
				Assert.fail("The BookNow button is not Present");  
			}
			else logger.log (Status.PASS, MarkupHelper.createLabel("The BookNow button is Present",  ExtentColor.GREEN));		


		}



		catch (Exception ex){

			{

				System.out.println(ex.toString());
				ex.printStackTrace();				
				logger.fail(ex.toString());
				logger.log(Status.FAIL, MarkupHelper.createLabel("Fail" + " - Test Case Failed", ExtentColor.RED));		
				logger.fail("Last Screen").addScreenCaptureFromPath("imagen");				
				Assert.fail();

			}


		}

	}

	/**
	 * Flujo completo o Feliz de la Historia de Usuario 2
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	public void UserStory02_04() throws IOException, InterruptedException {

		try {

			logger = report.createTest("Test Case: UserStory02_04 Validate itinerary, information per day and Validate Book Now Button");			
			logger.log(Status.INFO,"The browser load correctly");				
			driver.get(url);
			home.selectSailFrom(driver, "The Bahamas");			
			home.selecCruiseByIndex(driver,1);			

			if (!itinerary.validateDaysItinerary(driver, itinerary.getDaysOfCruise(driver))) {			
				logger.log (Status.FAIL, MarkupHelper.createLabel("The itinerary is incomplete",  ExtentColor.RED));
				Assert.fail("The itinerary is incomplete");  
			}
			else logger.log (Status.PASS, MarkupHelper.createLabel("The itinerary is complete",  ExtentColor.GREEN));	

			if (!itinerary.clickEachAbouButton(driver)) {			
				logger.log (Status.FAIL, MarkupHelper.createLabel("There was a problen with one Day and doesnt show Slide of Information",  ExtentColor.RED));
				Assert.fail("There was a problen with one Day and doesnt show Slide of Information");  
			}
			else logger.log (Status.PASS, MarkupHelper.createLabel("All days are correct Displayed",  ExtentColor.GREEN));	  

			if (!itinerary.validateBookNowButton(driver)) {			
				logger.log (Status.FAIL, MarkupHelper.createLabel("The BookNow button is not Present",  ExtentColor.RED));
				Assert.fail("The BookNow button is not Present");  
			}
			else logger.log (Status.PASS, MarkupHelper.createLabel("The BookNow button is Present",  ExtentColor.GREEN));		


		}



		catch (Exception ex){

			{

				System.out.println(ex.toString());
				ex.printStackTrace();				
				logger.fail(ex.toString());
				logger.log(Status.FAIL, MarkupHelper.createLabel("Fail" + " - Test Case Failed", ExtentColor.RED));		
				logger.fail("Last Screen").addScreenCaptureFromPath("imagen");				
				Assert.fail();

			}


		}

	}


	@After
	public void closeTest() {

		driver.close();
	}

	@AfterClass
	public static void finalizacion(){

		report.flush();
	}
}
