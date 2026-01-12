package AllHttpMethods;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Post_CreateBooking {

	RequestSpecification req;
	Response res;
	ValidatableResponse vRes;
	@Test
	public void createBookingTest() {
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
		
		
		  Object bookingid=vRes.extract().path("bookingid");
		  String firstname=vRes.extract().path("booking.firstname");
		  System.out.println("Booking id generated: " +bookingid);
		  System.out.println(firstname);
		  
		  Assert.assertNotNull(bookingid);
		  Assert.assertEquals(firstname, "Amar");
		 
		
	}
}
