package datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatternEx {

	public static void main(String[] args) {
		String armisticeDate = "2016-04-04";
		 
		LocalDate aLD = LocalDate.parse(armisticeDate);
		System.out.println("Date: " + aLD);
		 
		String armisticeDateTime = "2016-04-04T11:50";
		 
		LocalDateTime aLDT = LocalDateTime.parse(armisticeDateTime);
		System.out.println("Date/Time: " + aLDT);
		
		String anotherDate = "04 Apr 2016";
		 
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDate random = LocalDate.parse(anotherDate, df);
		 
		System.out.println(anotherDate + " parses as " + random);
	}
}
