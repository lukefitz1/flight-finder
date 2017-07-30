package lukefitz;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pos.Expedia;

public class ExpediaFlights {

	WebDriver driver;
	private Expedia exp;
	String tripType = "roundtrip";
	String fromAirport = "CHS";
	String toAirport = "MCI";
	String departDate = "06/16/2017";
	String returnDate = "06/19/2017";
	String numPassengers = "1";
	String fromCity = "Charleston";
	String fromSt = "SC";
	String toCity = "Kansas%20City";
	String toSt = "MO";
	
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
		
		exp = new Expedia(driver);
	}
	
	@Test(groups = { "functional" }, priority=0)
	public void goToDelta() {
		exp.goToRequestPage(tripType, fromAirport, fromCity, fromSt, toAirport, toCity, toSt, departDate, returnDate, numPassengers);
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void statusCheck() throws InterruptedException {
		//Assert.assertTrue(del.headerDisplayed(), "Header is displayed");
		//Assert.assertTrue(del.logoDisplayed(), "Logo is displayed");
		System.out.println("Sleeping");
		Thread.sleep(5000);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
