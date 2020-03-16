package restAssuredTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.http.ContentType;

public class DataContentVerification {

	
	@Test
	public void test_sithspeeder_length() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/?search={name}", "Sith speeder").
	
	then().
			body("results.length[0]", equalTo("1.5"));
	}
	
	@Test
	public void test_tribubblebongo_capacity() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/?search={name}", "Tribubble bongo").
	
	then().
			body("results[0].cargo_capacity", equalTo("1600"));
	}
	
	@Test
	public void test_armoredassaulttank_cost() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/?search={name}", "Armored Assault Tank").
	
	then().
			body("results[0].cost_in_credits", equalTo("unknown"));
	}
	
	@Test
	public void test_landspeeder_attributes() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/{id}", "7").
	
	then().
			body("name", equalTo("X-34 landspeeder")).
			body("vehicle_class", equalTo("repulsorcraft")).
			body("films", hasItem("https://swapi.co/api/films/1/"));
	}
	
	@Test
	public void test_find_large_vehicles() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles").
	
	then().
			body("results.findAll {Double.parseDouble(it.length) > 20}.name", hasItems("Sand Crawler", "Sail barge"));
	}
}
