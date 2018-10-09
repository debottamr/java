package datetime;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.chrono.GregorianChronology;

public class JodaEx {

	public static void main(String[] args) {
		
	}
	public static void difference_between_two_dates_duration_Joda()
	 {
	     DateTime dateOfBirth = new DateTime(1988, 7, 4, 0, 0, GregorianChronology.getInstance());
	     DateTime currentDate = new DateTime();
	     Days diffInDays = Days.daysBetween(dateOfBirth, currentDate);
	     Hours diffInHours = Hours.hoursBetween(dateOfBirth, currentDate);
	     Minutes diffInMinutes = Minutes.minutesBetween(dateOfBirth, currentDate);
	     Seconds seconds = Seconds.secondsBetween(dateOfBirth, currentDate);
	 }
}
