package payloadManagement;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class String_payload {
	RequestSpecification req;
	Response res;
	ValidatableResponse vRes;
	@Description("Assert create Booking using Hamcrest")
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
		vRes.statusCode(200);
		vRes.body("booking.firstname",equalTo("Amar"));
		vRes.body("booking.lastname",containsString("Jain"));
		vRes.body("bookingid",notNullValue());
		vRes.header("Content-Type", equalTo("application/json; charset=utf-8"));
		vRes.time(greaterThan(200L));
	}
}
