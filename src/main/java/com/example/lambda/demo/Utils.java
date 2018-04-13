package com.example.lambda.demo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Utils {

	public static void main(String[] args) {
		//TODO

	}
	
	String getCurrentTime() {
		LocalDateTime localtDateAndTime = LocalDateTime.now();
	    ZoneId zoneId = ZoneId.of("America/New_York");
	    ZonedDateTime dateAndTimeInNY  = ZonedDateTime.of(localtDateAndTime, zoneId);
	    String currentTimewithTimeZone = dateAndTimeInNY.getHour()+":"+dateAndTimeInNY.getMinute()+":"+dateAndTimeInNY.getSecond();
		return currentTimewithTimeZone;
	}

}
