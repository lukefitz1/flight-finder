package lukefitz;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pos.Delta;

public class DeltaFlights {

	WebDriver driver;
	private Delta del;
	String fromAirport = "CHS";
	String toAirport = "MCI";
	String departDate = "06/16/2017";
	String returnDate = "06/19/2017";
	int numPassengers = 1;

	@BeforeClass(alwaysRun = true)
	public void setUpTests() throws MalformedURLException {		
		//Set up desired capabilities for test
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setPlatform(Platform.MAC);
		cap.setBrowserName("chrome");
		cap.setVersion("55");

		//create new driver, set window size and timeout
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		driver.manage().window().setSize(new Dimension(1280, 750));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		del = new Delta(driver);
	}

	@Test(groups = { "functional" }, priority=0)
	public void goToDelta() {
		del.goToHomepage();
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void statusCheck() {
		Assert.assertTrue(del.headerDisplayed(), "Header is displayed");
		Assert.assertTrue(del.logoDisplayed(), "Logo is displayed");
	}
	
	@Test(groups = { "functional" }, priority=6)
	public void formCheck() {
		Assert.assertTrue(del.flightFormDisplayed(), "Flight form is displayed");
		Assert.assertTrue(del.fromAiportInputDisplayed(), "From aiport input is displayed");
		Assert.assertTrue(del.toAirportInputDisplayed(), "To airport input is displayed");
		Assert.assertTrue(del.departDateInputDisplayed(), "Depart date input is displayed");
		Assert.assertTrue(del.returnDateInputDisplayed(), "Return date input is displayed");
		Assert.assertTrue(del.flightFormSubmitButtonDisplayed(), "Flight form submit button is displayed");
	}

	@Test(groups = { "functional" }, priority=9)
	public void fillForm() {
		del.clearFlightForm();
		del.fillFlightForm(fromAirport, toAirport, departDate, returnDate);
	}
	
	@Test(groups = { "functional" }, priority=12)
	public void submitForm() {
		del.clickFlightFormSubmitButton();
	}

	@Test(groups = { "functional" }, priority=15)
	public void waitForFlightsPage() {
		Assert.assertTrue(del.flightPageHeaderDisplayed(), "Flight page header is displayed");
		Assert.assertTrue(del.bookATripHeadingDisplayed(), "Book a trip heading is displayed");
	}
	
	@Test(groups = { "functional" }, priority=18)
	public void getFlightDetails() {
		Assert.assertTrue(del.flightsTableDisplayed(), "Flight table is displayed");
		int numRows = del.numRowsDisplayed();

		for (int i = 0; i < numRows; i++) {
			int numPricesInRow = del.countPricesInRow(i);
			
			String departTime = del.getDepartTime(i);
			String arrivalTime = del.getArrivalTime(i);
			String totalTime = del.getTotalTime(i);
			
			System.out.println("Depart time: " + departTime);
			System.out.println("Arrival time: " + arrivalTime);
			System.out.println("Total travel time: " + totalTime);
			
			for (int j = 1; j <= numPricesInRow; j++) {
				String price  = del.getPrice(i, j, numPricesInRow);
				System.out.println("Price: " + price);
			}
		}
		
		//TODO Log flight info to data storage device
		//TODO Evaluate prices of flights, send notification if really cheap
	}

	@Test(groups = { "functional" }, priority=21)
	public void logFlights() {

	}
	
	@Test(groups = { "functional" }, priority=30)
	public void waitUp() throws InterruptedException {
		//Thread.sleep(5000);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		//driver.quit();
	}
}
