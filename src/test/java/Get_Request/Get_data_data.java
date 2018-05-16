package Get_Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
		if(code == 200){
			es.testmail();
		}
		System.out.println("Code obtained -->"+code);
		try{
		Assert.assertEquals(code, 201);
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
}
