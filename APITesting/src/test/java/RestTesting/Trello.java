package RestTesting;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;

import static io.restassured.RestAssured.given;
import  io.restassured.RestAssured;
import  io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Trello {
	public static String baseurl ="https://api.trello.com";
	public static String id;
	@Test(priority = 0)
	
	public void createBoard()
	{
		RestAssured.baseURI = baseurl;
		
		
		Response response =	 given()
				.queryParam("name", "Alok board")
		.queryParam("key","53c362aed5b47e7daadcbfbd7d1ae7d5")
		.queryParam("token","6639ae18841d0232e27c165ea6b22bfc439238e430eed59a54b16adf5b491ae9")
		.header("content-Type","application/json")
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		//this is to fetch the response as string
		String jsonresponse = response.asString();
		//I WANT TO CONVERT THE RESPONSE IN JSON FORMAT
		JsonPath js = new JsonPath(jsonresponse);
		//now i have to fetch the id
		id = js.get("id");
		System.out.println(id);
	}
	//if i make any @Test method enabled =false/ that method will not execute
	
		@Test(priority = 1)
		public void getBoard()
		{
			RestAssured.baseURI = baseurl;
			
			
		given()
			.queryParam("key", "53c362aed5b47e7daadcbfbd7d1ae7d5")
			.queryParam("token", "6639ae18841d0232e27c165ea6b22bfc439238e430eed59a54b16adf5b491ae9")
			.header("Content-Type","application/json")
			
			.when()
			.get("/1/boards/"+id)
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
		 
		}
		
		@Test(priority = 2)
		public void DeleteBoard()
		{
			RestAssured.baseURI = baseurl;
			
			
		Response response =	given()
			.queryParam("key", "53c362aed5b47e7daadcbfbd7d1ae7d5")
			.queryParam("token", "6639ae18841d0232e27c165ea6b22bfc439238e430eed59a54b16adf5b491ae9")
			.header("Content-Type","application/json")
			
			.when()
			.delete("/1/boards/"+id)
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
			String jsonresponse = response.asString();
			
			System.out.println(jsonresponse);
		}
	}
	
	


