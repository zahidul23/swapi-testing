package restAssuredTests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import io.restassured.http.ContentType;

public class RequestValidation {
	@Test
	public void test_all_vehicles_status() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles").
	
	then().
			statusCode(200);
	}
	
	@Test
	public void test_existing_vehicle_status() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/4").
	
	then().
			statusCode(200);
	} 
	
	@Test
	public void test_nonexisiting_vehicle_status() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/2").
	
	then().
			statusCode(404);
	}
	
	@Test
	public void test_vehicles_schema() throws MalformedURLException {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/4").
	
	then().
			assertThat().body(matchesJsonSchema(new URL("https://swapi.co/api/vehicles/schema")));
	} 
}
