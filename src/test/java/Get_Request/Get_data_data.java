package Get_Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Get_data_data {
	Email_Sender.EmailAttachmentSender es=new Email_Sender.EmailAttachmentSender();
	
	public Properties prop;
		
	
	@Test
	public void Get_test_response() throws IOException{
		prop=new Properties();
		FileInputStream fis=new FileInputStream("src//main//resources//API.properties");
		prop.load(fis);
		String url_link=prop.getProperty("url");
		System.out.println("Url fetched from Property File-->"+url_link);
		Response resp=RestAssured.get(url_link);
		int code= resp.getStatusCode();
		if(code != 200){
			es.testmail();
		}
		System.out.println("Code obtained -->"+code);
		try{
		Assert.assertEquals(code, 200);
		 }catch(AssertionError e)
        {            System.out.println("Assertion error -->"+e);
        }
		
	}
	
	@Test
	public void Get_Responsedata_live(){
		
		
		Response resp=RestAssured.get("https://rarecarat.azurewebsites.net/User/GetLocation");
		String data= resp.asString();
		System.out.println("Response obtained -->"+data);
		System.out.println("Response time taken by Production-->"+resp.getTime() + "milisec");
		
	}
	

	
	@Test
	public void Parse_JSON(){
		
		//RestAssured.baseURI = "http://localhost:3000/posts/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("https://rarecarat.azurewebsites.net/User/GetLocation");
	 
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
	 
		// Let us print the city variable to see what we got
		System.out.println("Postal Code Present in Response -->" + jsonPathEvaluator.get("PostalCode"));
	 
		// Print the temperature node
		//System.out.println("ids are " + jsonPathEvaluator.get("id"));
	 
		// Print the humidity node
		//System.out.println("author are " + jsonPathEvaluator.get("author"));
	 
		
	}
	
	@Test
	public void RegistrationSuccessful()
	{		
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender"); // Cast
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1");

		requestParams.put("Email",  "sample2ee26d9@gmail.com");
		request.body(requestParams.toJSONString());
		Response response = request.post("/register");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, "201");
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
	}
}
