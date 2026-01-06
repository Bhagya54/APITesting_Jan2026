package BDD_NonBDD;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;


public class BasicTest_NonBDDStyle {
	String pincode;
	RequestSpecification req;
	Response res;
	ValidatableResponse vRes;
	
	@Test(priority=1)
	public void positiveTestcase() {
		pincode="500032";
		req=given();
		req.baseUri("https://api.zippopotam.us");
		req.basePath("/IN/"+pincode);
		
		
		res=req.when().get();
		
		vRes=res.then();
		vRes.statusCode(200);		

	}
	
	@Test(priority=2)
	public void negativeTestcase1() {
		pincode="-1";
		req=given();
		req.baseUri("https://api.zippopotam.us");
		req.basePath("/IN/"+pincode);
		
		res=req.when().get();
		
		vRes=res.then();
		vRes.statusCode(404);		

	}
	
	@Test(priority=3)
	public void negativeTestcase2() {
		pincode="dfdsfds$%$";
		req=given();
		req.baseUri("https://api.zippopotam.us");
		req.basePath("/IN/"+pincode);
		
		res=req.when().get();
		
		vRes=res.then();
		vRes.statusCode(404);				

	}
	
}
