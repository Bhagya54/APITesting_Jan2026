package AllHttpMethods;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Post_Token {

	RequestSpecification req;
	Response res;
	ValidatableResponse vRes;
	@Test
	public void createToken(ITestContext context) {
		String payload="{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}";
		req=given();
		req.baseUri("https://restful-booker.herokuapp.com");
		req.basePath("/auth");
		req.contentType(ContentType.JSON);
		req.body(payload);
		
		
		res=req.log().all().when().post();
		
		vRes=res.then().log().all();
		
		String token=vRes.extract().path("token").toString();
		System.out.println("Token generated: " +token);
		context.setAttribute("token_id", token);
		
	}
}
