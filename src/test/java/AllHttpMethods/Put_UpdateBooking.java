package AllHttpMethods;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Put_UpdateBooking {

	RequestSpecification req;
	Response res;
	ValidatableResponse vRes;
	@Test
	public void createBookingTest(ITestContext context) {
		String payload="{\r\n"
				+ "    \"firstname\" : \"James\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		req=given();
		req.baseUri("https://restful-booker.herokuapp.com");
		req.basePath("/booking/"+context.getAttribute("booking_id"));
		req.contentType(ContentType.JSON);
		req.body(payload);
		req.cookie("token",context.getAttribute("token_id"));
		
		
		res=req.log().all().when().put();
		
		vRes=res.then().log().all();
		vRes.statusCode(200);
		
		
		
		  
		  String firstname=vRes.extract().path("firstname");
		  System.out.println(firstname);
		  		 
		  Assert.assertEquals(firstname, "James");
		  
		 
		 
		
	}
}
