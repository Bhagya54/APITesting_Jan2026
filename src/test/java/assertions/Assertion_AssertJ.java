package assertions;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.*;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Assertion_AssertJ {
	RequestSpecification req;
	Response res;
	ValidatableResponse vRes;
	@Description("Assert create Booking using AssertJ")
	@Test
	public void createBooking() {
		String payload="{\r\n"
				+ "    \"firstname\" : \"Amar\",\r\n"
				+ "    \"lastname\" : \"Jain\",\r\n"
				+ "    \"totalprice\" : 123,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		req=given();
		req.baseUri("https://restful-booker.herokuapp.com");
		req.basePath("/booking");
		req.contentType(ContentType.JSON);
		req.body(payload);
		
		res=req.log().all().when().post();
		
		vRes=res.then().log().all();
		//extract method
		int bookingId=vRes.extract().path("bookingid");
		assertThat(bookingId).isNotNull().isPositive();
		
		String fname=vRes.extract().path("booking.firstname");
		assertThat(fname).isEqualTo("Amar").isNotNull().isNotEmpty().isNotBlank().isAlphabetic();
		//isEmpty - "" - lenght=0 , isNotEmpty
		//isBlank - "    " - 
		//isNull - null
		String checkin=vRes.extract().path("booking.bookingdates.checkin");
		assertThat(checkin).isEqualTo("2018-01-01").isNotNull().isNotEmpty().isNotBlank();
		
		
		
	}
}
