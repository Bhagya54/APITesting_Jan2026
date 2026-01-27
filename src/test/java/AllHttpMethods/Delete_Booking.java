package AllHttpMethods;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Delete_Booking {
	RequestSpecification req;
	Response res;
	ValidatableResponse vRes;
	
	//https://restful-booker.herokuapp.com/booking/:id
	@Test
	public void getBookingTest(ITestContext context) {
		req=given();
		req.baseUri("https://restful-booker.herokuapp.com");
		req.basePath("/booking/"+context.getAttribute("booking_id"));
		req.cookie("token",context.getAttribute("token_id"));
		
		res=req.log().all().when().delete();
		
		vRes=res.then().log().all();
		vRes.statusCode(201);
		
	}
}
