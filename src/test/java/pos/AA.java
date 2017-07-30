package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pos.Base;

public class AA extends Base {

	String baseUrl = "https://www.aa.com/homePage.do";
	By header =  By.cssSelector("#aa-lang-en > header");
	By logo = By.cssSelector("#main-navigation > h1 > a");
	By flightForm = By.cssSelector("#reservationFlightSearchForm");
	By fromAiportInput = By.xpath("//*[@id='reservationFlightSearchForm.originAirport']");
	By toAirportInput = By.xpath("//*[@id='reservationFlightSearchForm.destinationAirport']");
	By departDateInput = By.cssSelector("#aa-leavingOn");
	By returnDateInput = By.cssSelector("#aa-returningFrom");
	By numPassengersSelect = By.cssSelector("#flightSearchForm.adultOrSeniorPassengerCount");
	By flightFormSubmitInput = By.xpath("//*[@id='flightSearchForm.button.reSubmit']");
	By flightFormSubmitButton = By.xpath("//*[@id='flightSearchForm.button.vacationSubmit']");

	public AA(WebDriver driver) {
		super(driver);
		setBaseUrl(baseUrl);
	}
	
	public void goToHomepage() {
		visit("");
	}

	public Boolean headerDisplayed() {
		return waitForIsDisplayed(header, 10);
	}
	
	public By getHeader() {
		return header;
	}
	
	public Boolean logoDisplayed() {
		return waitForIsDisplayed(logo, 10);
	}
	
	public Boolean flightFormDisplayed() {
		return waitForIsDisplayed(flightForm, 10);
	}
	
	public Boolean fromAiportInputDisplayed() {
		return waitForIsDisplayed(fromAiportInput, 10);
	}
	
	public Boolean toAirportInputDisplayed() {
		return waitForIsDisplayed(toAirportInput, 10);
	}
	
	public Boolean departDateInputDisplayed() {
		return waitForIsDisplayed(departDateInput, 10);
	}
	
	public Boolean returnDateInputDisplayed() {
		return waitForIsDisplayed(returnDateInput, 10);
	}
	
	public Boolean flightFormSubmitInputDisplayed() {
		return waitForIsDisplayed(flightFormSubmitInput, 10);
	}
	
	public void clearFlightForm() {
		clearField(fromAiportInput);
		clearField(toAirportInput);
		clearField(departDateInput);
		clearField(returnDateInput);
	}
	
	public void fillFlightForm(String fromAirport, String toAiport, String departDate, String returnDate) {
		type(fromAirport, fromAiportInput);
		type(toAiport, toAirportInput);
		type(departDate, departDateInput);
		type(returnDate, returnDateInput);
	}
	
	public void clickFlightFormSubmitButton() {
		click(flightFormSubmitButton);
	}
	
	public void submitFlightForm() {
		submit(flightFormSubmitInput);
	}
}
