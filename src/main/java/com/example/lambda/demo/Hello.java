package com.example.lambda.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Hello implements RequestHandler<Object, String> {

	Utils utils = new Utils();
	private String city = "08902,us";
	@Override
	public String handleRequest(Object input, Context context) {
	context.getLogger().log("\nMethod Entry: handleRequest() @ "+utils.getCurrentTime());
	String callOutcome = "failure";
	if (input != null && input.toString() != "{}")
		city = input.toString()+",us";
	
	context.getLogger().log("\nCalling callWeatherAPI() with city as: "+city);
	String	weatherData = callWeatherAPI(city, context);
	if(weatherData != "")
		callOutcome = "success";
	
	context.getLogger().log("\nMethod Exit: handleRequest() @ "+utils.getCurrentTime()+"\n");
		return callOutcome;
	}

	private String callWeatherAPI(String city, Context context) {
		context.getLogger().log("\nMethod Entry: callWeatherAPI() @ "+ utils.getCurrentTime());

		String weatherOutput = "";
		String zip = city;
		String apiKey = "01308c369f778cdb0daa07f0dec3a848";
		String units = "imperial";
		String baseURL = "http://api.openweathermap.org/data/2.5/weather?";
		try {
		        URL url = new URL(baseURL + "apiKey="+apiKey+"&zip="+zip+"&units="+units);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Accept", "application/json");

		        if (conn.getResponseCode() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : "
		                    + conn.getResponseCode());
		        }

		        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		        System.out.println("\nOutput from Server .... \n");
		        while ((weatherOutput = br.readLine()) != null) {
		            System.out.println(weatherOutput);
					//weatherData.setResponse(output);
		        }
		        conn.disconnect();
			
		      } catch (MalformedURLException e) {
		        e.printStackTrace();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		context.getLogger().log("\nMethod Exit: callWeatherAPI() @ "+utils.getCurrentTime());
		return weatherOutput;
	}

}
