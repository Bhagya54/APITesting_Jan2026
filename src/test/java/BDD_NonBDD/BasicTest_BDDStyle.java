package BDD_NonBDD;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

/*
 * url : uri+basePath
 * https://api.zippopotam.us/IN/500032
 * given() --- baseUri/basePath/cookie/auth/contentType
 * when() - GET/POST/PUT/DELETE
 * then() - validation - status code-200/body /cookie/contentType
 * 
 * instance method - Object creation
 * static method - classname.methodname
 * 
 * given()
 * BDD format
 * 
 */
public class BasicTest_BDDStyle {
	String pincode;
	@Test
	public void positiveTestcase() {
		pincode="500032";
		given()
			.baseUri("https://api.zippopotam.us")
			.basePath("/IN/"+pincode)
		.when()
			.get()
		.then()
			.statusCode(200).log().all();			

	}
	
	@Test
	public void negativeTestcase1() {
		pincode="-1";
		given()
			.baseUri("https://api.zippopotam.us")
			.basePath("/IN/"+pincode)
		.when()
			.get()
		.then()
			.statusCode(404).log().all();			

	}
	
	@Test
	public void negativeTestcase2() {
		pincode="dfdsfds$%$";
		given()
			.baseUri("https://api.zippopotam.us")
			.basePath("/IN/"+pincode)
		.when()
			.get()
		.then()
			.statusCode(404).log().all();			

	}
	
}
