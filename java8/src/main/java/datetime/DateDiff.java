package datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateDiff {

	public static void main(String[] args) {
		LocalDate endofCentury = LocalDate.of(2014, 01, 01);
		LocalDate now = LocalDate.now();
		 
		Period diff = Period.between(endofCentury, now);
		 
		System.out.printf("Difference is %d years, %d months and %d days old",
		                    diff.getYears(), diff.getMonths(), diff.getDays());
	}

	public void difference_between_two_dates_java8()
	{
	     LocalDate dateOfBirth = LocalDate.of(1980, Month.JULY, 4);
	     LocalDate currentDate = LocalDate.now();
	     long diffInDays = ChronoUnit.DAYS.between(dateOfBirth, currentDate);
	     long diffInMonths = ChronoUnit.MONTHS.between(dateOfBirth, currentDate);
	     long diffInYears = ChronoUnit.YEARS.between(dateOfBirth, currentDate);
	}
	//You can use ChronoUnit to know the difference in smaller time units e.g. milliseconds, minutes etc. But in that case, you will have to use LocalDateTime in place of LocalDate as used in first example.


	public void difference_between_two_dates_duration()
	 {
	     LocalDateTime dateTime = LocalDateTime.of(1988, 7, 4, 0, 0);
	     LocalDateTime dateTime2 = LocalDateTime.now();
	     long diffInNano = ChronoUnit.NANOS.between(dateTime, dateTime2);
	     long diffInSeconds = ChronoUnit.SECONDS.between(dateTime, dateTime2);
	     long diffInMilli = ChronoUnit.MILLIS.between(dateTime, dateTime2);
	     long diffInMinutes = ChronoUnit.MINUTES.between(dateTime, dateTime2);
	     long diffInHours = ChronoUnit.HOURS.between(dateTime, dateTime2);
	 }
	
	//2.3. java.time.Duration example to know the difference in millis/seconds/minutes etc

	
	public void difference_between_two_dates_duration1()
	 {
	     LocalDateTime dateTime = LocalDateTime.of(1988, 7, 4, 0, 0);
	     LocalDateTime dateTime2 = LocalDateTime.now();
	    int diffInNano = java.time.Duration.between(dateTime, dateTime2).getNano();
	     long diffInSeconds = java.time.Duration.between(dateTime, dateTime2).getSeconds();
	     long diffInMilli = java.time.Duration.between(dateTime, dateTime2).toMillis();
	     long diffInMinutes = java.time.Duration.between(dateTime, dateTime2).toMinutes();
	     long diffInHours = java.time.Duration.between(dateTime, dateTime2).toHours();
	 }
}
