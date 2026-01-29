package payloadManagement;
/*
 * Serialization: java object into json string
 * Deserialization: json string into java object
 * 
 * 
 */
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class HashMap_payload {

		RequestSpecification req;
		Response res;
		ValidatableResponse vRes;
		@Description("Assert create Booking using Hamcrest")
		@Test
		public void createBooking() {
			HashMap<String,Object> payload=new HashMap<>();
			payload.put("firstname", "raju");
			payload.put("lastname", "karan");
			payload.put("totalprice", 123);
			payload.put("depositpaid", true);
			
			HashMap<String,Object> bookingDates = new HashMap<String, Object>();
			bookingDates.put("checkin", "2018-01-01");
			bookingDates.put("checkout", "2019-01-01");
			
			payload.put("bookingdates", bookingDates);
			payload.put("additionalneeds", "breakfast");
			
			
			
		
			
			req=given();
			req.baseUri("https://restful-booker.herokuapp.com");
			req.basePath("/booking");
			req.contentType(ContentType.JSON);
			req.body(payload);
			
			res=req.log().all().when().post();
			
			vRes=res.then().log().all();
			vRes.statusCode(200);
			vRes.body("booking.firstname",equalTo("raju"));
			vRes.body("booking.lastname",containsString("karan"));
			vRes.body("bookingid",notNullValue());
			vRes.header("Content-Type", equalTo("application/json; charset=utf-8"));
			vRes.time(greaterThan(200L));

	}
}


