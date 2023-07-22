package test;
import static org.testng.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class WeatherAPI{

	@Test
	public void getWeather() {

		String response=RestAssured.given().log().all()
				.baseUri("https://weather.visualcrossing.com")
				.headers("Content-Type","application/json")
				.queryParam("key", "6XCXU66WDUPB74X5D86U9MZKU")

				.when()
				.get("/VisualCrossingWebServices/rest/services/timeline/delhi")

				.then().log().all()
				.statusCode(200)
				.extract().response().asString();

		JsonPath js= new JsonPath(response);
		String address=js.getString("address");

		String datetime=js.getString("days[0].datetime");
		SimpleDateFormat st=new SimpleDateFormat("yyyy-MM-dd");

		assertEquals(address, "delhi");
		assertEquals(datetime, st.format(new Date()));
	}
}
