package restAssuredTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.http.ContentType;

public class SearchValidation {
	
	@Test
	public void test_vulturedroid_search() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/?search={name}", "Vulture Droid").
	
	then().
			body("results.name[0]", equalTo("Vulture Droid"));
	}
	
	@Test
	public void test_troop_search() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/?search={name}", "troop").
	
	then().
			body("results.name", hasItems("Multi-Troop Transport","Single Trooper Aerial Platform"));
	}
	
	@Test
	public void test_spaceship_search() {
	given().
			baseUri("https://swapi.co/api/").
			contentType(ContentType.JSON).
	
	when().
			get("vehicles/?search={name}", "spaceship").
	
	then().
			statusCode(200).body("count", equalTo(0));
	}
	
}
