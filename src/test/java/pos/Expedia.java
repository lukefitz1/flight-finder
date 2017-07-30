package pos;

import org.openqa.selenium.WebDriver;

public class Expedia extends Base {

	String baseUrl = "https://www.expedia.com/";
	
	public Expedia(WebDriver driver) {
		super(driver);
		setBaseUrl(baseUrl);
	}
	
	public String buildRequest(String tripType, String departAirport, String departCity, String departSt, String toAirport, String toCity, String toSt, String departDate, String returnDate, String numPassengers) {
		String request = "Flights-Search?trip=" + tripType + "&leg1=from:" + departCity + ",%20" + departSt + "%20(CHS-Charleston%20Intl.),to:" + toCity + ",%20" + toSt + "%20(MCI-Kansas%20City%20Intl.),departure:" + departDate + "TANYT&leg2=from:" + toCity + ",%20" + toSt + "%20(MCI-Kansas%20City%20Intl.),to:" + departCity + ",%20" + departSt + "%20(CHS-Charleston%20Intl.),departure:" + returnDate + "TANYT&passengers=children:0,adults:" + numPassengers + ",seniors:0,infantinlap:Y&mode=search";
		return request;
	}
	
	public void goToRequestPage(String tripType, String departAirport, String departCity, String departSt, String toAirport, String toCity, String toSt, String departDate, String returnDate, String numPassengers) {
		//System.out.println("Request string: " + buildRequest(tripType, departAirport, departCity, departSt, toAirport, toCity,toSt, departDate, returnDate, numPassengers));
		visit(buildRequest(tripType, departAirport, departCity, departSt, toAirport, toCity,toSt, departDate, returnDate, numPassengers));
		//visit("Flights-Search?trip=roundtrip&leg1=from:Charleston,%20SC%20(CHS-Charleston%20Intl.),to:Kansas%20City,%20MO%20(MCI-Kansas%20City%20Intl.),departure:06/16/2017TANYT&leg2=from:Kansas%20City,%20MO%20(MCI-Kansas%20City%20Intl.),to:Charleston,%20SC%20(CHS-Charleston%20Intl.),departure:06/19/2017TANYT&passengers=children:0,adults:1,seniors:0,infantinlap:Y&mode=search");
	}

}
