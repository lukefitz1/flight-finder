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

import pos.AA;

public class AAFlights {
	
	WebDriver driver;
	private AA aa;
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
		
		aa = new AA(driver);
	}

	@Test(groups = { "functional" }, priority=0)
	public void goToAA() {
		aa.goToHomepage();
	}
	
	@Test(groups = { "functional" }, priority=3)
	public void statusCheck() {
		Assert.assertTrue(aa.headerDisplayed(), "Header is displayed");
		Assert.assertTrue(aa.logoDisplayed(), "Logo is displayed");
	}
	
	@Test(groups = { "functional" }, priority=6)
	public void formCheck() {
		Assert.assertTrue(aa.flightFormDisplayed(), "Flight form is displayed");
		Assert.assertTrue(aa.fromAiportInputDisplayed(), "From aiport input is displayed");
		Assert.assertTrue(aa.toAirportInputDisplayed(), "To airport input is displayed");
		Assert.assertTrue(aa.departDateInputDisplayed(), "Depart date input is displayed");
		Assert.assertTrue(aa.returnDateInputDisplayed(), "Return date input is displayed");
		Assert.assertTrue(aa.flightFormSubmitInputDisplayed(), "Flight form submit button is displayed");
	}

	@Test(groups = { "functional" }, priority=9)
	public void fillForm() {
		aa.clearFlightForm();
		aa.fillFlightForm(fromAirport, toAirport, departDate, returnDate);
	}
	
	@Test(groups = { "functional" }, priority=12)
	public void submitForm() {
		//aa.submit(By.xpath("//*[@id='flightSearchForm.button.reSubmit']"));
		//aa.submit(By.cssSelector("#flightSearchFormSubmitButton"));
		WebElement ele = driver.findElement(By.xpath("//*[@id='flightSearchForm.button.vacationSubmit']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", ele);
	}

	@Test(groups = { "functional" }, priority=15)
	public void waitUp() throws InterruptedException {
		Thread.sleep(5000);
	}
//
//	@Test(groups = { "functional" }, priority=18)
//	public void fillForm() {

//	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		//driver.quit();
	}
	
}