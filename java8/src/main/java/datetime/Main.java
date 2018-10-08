package datetime;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAdjusters;
import java.time.zone.ZoneRules;

public class Main {

	public static void main(String[] args) {
		
		//The LocalDate class represents a date. There is no representation of a time or time-zone.
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate.toString());                //2013-05-15
		System.out.println(localDate.getDayOfWeek().toString()); //WEDNESDAY
		System.out.println(localDate.getDayOfMonth());           //15
		System.out.println(localDate.getDayOfYear());            //135
		System.out.println(localDate.isLeapYear());              //false
		System.out.println(localDate.plusDays(12).toString());   //2013-05-27
		
		//The LocalTime class represents a time. There is no representation of a date or time-zone.
		//LocalTime localTime = LocalTime.now();     //toString() in format 09:57:59.744
		LocalTime localTime = LocalTime.of(12, 20);
		System.out.println(localTime.toString());    //12:20
		System.out.println(localTime.getHour());     //12
		System.out.println(localTime.getMinute());   //20
		System.out.println(localTime.getSecond());   //0
		System.out.println(localTime.MIDNIGHT);      //00:00
		System.out.println(localTime.NOON);          //12:00
		
		
		//The LocalDateTime class represents a date-time. There is no representation of a time-zone.

		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.toString());      //2013-05-15T10:01:14.911
		System.out.println(localDateTime.getDayOfMonth()); //15
		System.out.println(localDateTime.getHour());       //10
		System.out.println(localDateTime.getNano());       //911000000
		
		
		
		//If you want to use the date functionality with zone information, then Lambda provide you extra 3 classes similar to above one i.e. OffsetDate, OffsetTime and OffsetDateTime. Timezone offset can be represented in “+05:30” or “Europe/Paris” formats. This is done via using another class i.e. ZoneId.


		
		OffsetDateTime offsetDateTime = OffsetDateTime.now();
		System.out.println(offsetDateTime.toString());              //2013-05-15T10:10:37.257+05:30
		 
		offsetDateTime = OffsetDateTime.now(ZoneId.of("+05:30"));
		System.out.println(offsetDateTime.toString());              //2013-05-15T10:10:37.258+05:30
		 
		offsetDateTime = OffsetDateTime.now(ZoneId.of("-06:30"));
		System.out.println(offsetDateTime.toString());              //2013-05-14T22:10:37.258-06:30
		 
		ZonedDateTime zonedDateTime =
		                ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		System.out.println(zonedDateTime.toString());               //2013-05-15T06:45:45.290+02:00[Europe/Paris]
		
		
		
		//For representing the specific timestamp ant any moment, the class needs to be used is Instant. The Instant class represents an instant in time to an accuracy of nanoseconds. Operations on an Instant include comparison to another Instant and adding or subtracting a duration.


		
		
		Instant instant = Instant.now();
		System.out.println(instant.toString());                                 //2013-05-15T05:20:08.145Z
		System.out.println(instant.plus(Duration.ofMillis(5000)).toString());   //2013-05-15T05:20:13.145Z
		System.out.println(instant.minus(Duration.ofMillis(5000)).toString());  //2013-05-15T05:20:03.145Z
		System.out.println(instant.minusSeconds(10).toString());                //2013-05-15T05:19:58.145Z
		
		//Duration class is a whole new concept brought first time in java language. It represents the time difference between two time stamps.


		
		Duration duration = Duration.ofMillis(5000);
		System.out.println(duration.toString());     //PT5S
		 
		duration = Duration.ofSeconds(60);
		System.out.println(duration.toString());     //PT1M
		 
		duration = Duration.ofMinutes(10);
		System.out.println(duration.toString());     //PT10M
		 
		duration = Duration.ofHours(2);
		System.out.println(duration.toString());     //PT2H
		 
		duration = Duration.between(Instant.now(), Instant.now().plus(Duration.ofMinutes(10)));
		System.out.println(duration.toString());  //PT10M
		
		
		//To interact with human, you need to get bigger durations which are presented with Period class.
		Period period = Period.ofDays(6);
		System.out.println(period.toString());    //P6D
		 
		period = Period.ofMonths(6);
		System.out.println(period.toString());    //P6M
		 
		period = Period.between(LocalDate.now(),
		            LocalDate.now().plusDays(60));
		System.out.println(period.toString());   //P1M29D

		//The current Java SE platform uses int constants for months, day-of-week and am-pm etc. Now a lot of extra utility classes have been added which work on top of these enums. I am taking an example such a class DayOfWeek. This class is a wrapper of day enums and can be used consistently with other classes also.
		
		//day-of-week to represent, from 1 (Monday) to 7 (Sunday)
		System.out.println(DayOfWeek.of(2));                    //TUESDAY
		 
		DayOfWeek day = DayOfWeek.FRIDAY;
		System.out.println(day.getValue());                     //5
		 
		LocalDate localDate1 = LocalDate.now();
		System.out.println(localDate1.with(DayOfWeek.MONDAY));  //2013-05-13  i.e. when was monday in current week ?
		
		
		
		//Date adjusters are another beautiful and useful addition in date handling tools. It easily solves the problems like : How do you find last day of the month? Or the next working day? Or a week on Tuesday?
		LocalDate date = LocalDate.of(2013, Month.MAY, 15);                     //Today
        
		LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(endOfMonth.toString());                              //2013-05-31
		 
		LocalDate nextTue = date.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
		System.out.println(nextTue.toString());                                 //2013-05-21
		
		
		//Creating date objects

		//Builder pattern used to make date object
		 OffsetDateTime date1 = Year.of(2013)
		                        .atMonth(Month.MAY).atDay(15)
		                        .atTime(0, 0)
		                        .atOffset(ZoneOffset.of("+03:00"));
		 System.out.println(date1);                                     //2013-05-15T00:00+03:00
		 
		//factory method used to make date object
		OffsetDateTime date2 = OffsetDateTime.
		                        of(2013, 5, 15, 0, 0, 0, 0, ZoneOffset.of("+03:00"));
		System.out.println(date2);                                      //2013-05-15T00:00+03:00
		
		
		
		
		//New class to simulate system/machine clock
		//A new class Clock is proposed in new release. This simulates the system clock functionality. I loved this feature most of all others. The reason is while doing unit testing. you are often required to test a API in future date. For this we had been forwarding the system clock for next date, and then again restart the server and test the application.
		
		Clock clock = Clock.systemDefaultZone();
		System.out.println(clock);                      //SystemClock[Asia/Calcutta]
		System.out.println(clock.instant().toString()); //2013-05-15T06:36:33.837Z
		System.out.println(clock.getZone());            //Asia/Calcutta
		 
		Clock anotherClock = Clock.system(ZoneId.of("Europe/Tiraspol"));
		System.out.println(anotherClock);                       //SystemClock[Europe/Tiraspol]
		System.out.println(anotherClock.instant().toString());  //2013-05-15T06:36:33.857Z
		System.out.println(anotherClock.getZone());             //Europe/Tiraspol
		 
		Clock forwardedClock  = Clock.tick(anotherClock, Duration.ofSeconds(600));
		System.out.println(forwardedClock.instant().toString());  //2013-05-15T06:

		
		//Timezone Changes
		//Timezone related handling is done by 3 major classes. These are ZoneOffset, TimeZone, ZoneRules.
		//Zone rules
		System.out.println(ZoneRules.of(ZoneOffset.of("+02:00")).isDaylightSavings(Instant.now()));
		System.out.println(ZoneRules.of(ZoneOffset.of("+02:00")).isFixedOffset());
		
		//Date Formatting
		//Date formatting is supported via two classes mainly i.e. DateTimeFormatterBuilder and DateTimeFormatter. DateTimeFormatterBuilder works on builder pattern to build custom patterns where as DateTimeFormatter provides necessary input in doing so.

		DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder();
		formatterBuilder.append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
		                .appendLiteral("-")
		                .appendZoneOrOffsetId();
		DateTimeFormatter formatter = formatterBuilder.toFormatter();
		System.out.println(formatter.format(ZonedDateTime.now()));

		

	}
}
