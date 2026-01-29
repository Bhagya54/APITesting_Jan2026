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

public class POJO_payload {
	RequestSpecification req;
	Response res;
	ValidatableResponse vRes;
	@Description("Assert create Booking using Hamcrest")
	@Test
	public void createBooking() {
		CreateBooking_POJO payload = new CreateBooking_POJO();
		payload.setFirstname("Amar");
		payload.setLastname("Jain");
		payload.setTotalprice(123);
		payload.setAdditionalneeds("lunch");
		payload.setDepositpaid(false);
		
		Bookingdates bd = new Bookingdates();
		bd.setCheckin("2018-01-01");
		bd.setCheckout("2019-01-01");
		
		payload.setBookingdates(bd);
		
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
