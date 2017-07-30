package pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Delta extends Base {

	String baseUrl = "http://www.delta.com/";
	By header =  By.cssSelector("body > div.header > div > header");
	By logo = By.cssSelector("#brand");
	By flightForm = By.cssSelector("#flightSearchForm");
	By fromAiportInput = By.cssSelector("#originCity");
	By toAirportInput = By.cssSelector("#destinationCity");
	By departDateInput = By.cssSelector("#departureDate");
	By returnDateInput = By.cssSelector("#returnDate");
	By numPassengersSelect = By.cssSelector("#paxCount");
	By flightFormSubmitButton = By.cssSelector("#findFlightsSubmit");
	By flightPageHeader = By.cssSelector("body > div.gradientfill > div.header");
	By bookATripHeading = By.cssSelector("#_tripSummaryHeader_tmplHolder > div.headerTitleWraper > h1");
	By flightsTable = By.cssSelector("#_fareDisplayContainer_tmplHolder");
	By priceRow = By.cssSelector(".contextRoot > div");
	By priceHolder = By.cssSelector(".priceHolder");
	
	public Delta(WebDriver driver) {
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
	
	public Boolean flightFormSubmitButtonDisplayed() {
		return waitForIsDisplayed(flightFormSubmitButton, 10);
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
		submit(flightFormSubmitButton);
	}
	
	public Boolean flightsTableDisplayed() {
		return waitForIsDisplayed(flightsTable, 10);
	}
	
	public Boolean flightPageHeaderDisplayed() {
		return waitForIsDisplayed(flightPageHeader, 10);
	}
	
	public Boolean bookATripHeadingDisplayed() {
		return waitForIsDisplayed(bookATripHeading, 10);
	}
	
	public int numRowsDisplayed() {
		return count(priceRow);
	}
	
	public int countPricesInRow(int row) {
		String rowString = "#contextRoot_" + row;
		String prices = rowString + " > div > table > tbody > tr:nth-child(2) > td.fourFareDisplayCol";
		int ct = count(By.cssSelector(prices));
		return ct;
	}
	
	public String getPrice(int row, int priceColumn, int pricesInRow) {	
		if (pricesInRow == 3) {
			String priceString = "#contextRoot_" + row + " > div > table > tbody > tr.fareRowContainer > td:nth-child(" + (priceColumn + 2) + ") > div.priceHolder > span > span.priceBfrDec";
			String centsString = "#contextRoot_" + row + " > div > table > tbody > tr.fareRowContainer > td:nth-child(" + (priceColumn + 2) + ") > div.priceHolder > span > sup";
			
			String price = getText(By.cssSelector(priceString));
			String cents = getText(By.cssSelector(centsString));
			
			return (price + cents);
		} else if (pricesInRow == 4) {
			String priceString = "#contextRoot_" + row + " > div > table > tbody > tr.fareRowContainer > td:nth-child(" + (priceColumn + 1) + ") > div.priceHolder > span > span.priceBfrDec";
			String centsString = "#contextRoot_" + row + " > div > table > tbody > tr.fareRowContainer > td:nth-child(" + (priceColumn + 1) + ") > div.priceHolder > span > sup";
			
			String price = getText(By.cssSelector(priceString));
			String cents = getText(By.cssSelector(centsString));
			
			return (price + cents);
		} else {
			return "0";
		}
	}
	
	public String getDepartTime(int row) {
		String timeString = "#contextRoot_" + row + " > div > table > tbody > tr.fareRowContainer > td:nth-child(1) > div > div.flightDateTime > div > span:nth-child(1)";
		String time = getText(By.cssSelector(timeString));
		return time;
	}
	
	public String getArrivalTime(int row) {
		String timeString = "#contextRoot_" + row + " > div > table > tbody > tr.fareRowContainer > td:nth-child(1) > div > div.flightDateTime > div > span:nth-child(3)";	
		String time = getText(By.cssSelector(timeString));
		return time;
	}
	
	public String getTotalTime(int row) {
		String timeString = "#contextRoot_" + row + " > div > table > tbody > tr.fareRowContainer > td:nth-child(1) > div > div.flightDateTime > div > span:nth-child(4)";
		String totalTime = getText(By.cssSelector(timeString));
		return totalTime;
	}

}
