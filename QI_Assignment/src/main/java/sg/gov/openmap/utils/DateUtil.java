package sg.gov.openmap.utils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static String getStringDate(String dateFormat) {
		SimpleDateFormat dtf = new SimpleDateFormat(dateFormat);  
	    Date localDate = new Date();  
		return dtf.format(localDate).toString();
	}
	
	public static Boolean isTimeIsInRangeFor5Minute(String timeString) {
        String timePortion = timeString.split(",")[0].trim();

        // Parse the time string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
        LocalTime time = LocalTime.parse(timePortion, formatter);

        // Get the current time
        LocalTime currentTime = LocalTime.now();
       
        // Calculate the time 5 minutes ago and 5 minutes from now
        LocalTime fiveMinutesAgo = currentTime.minusMinutes(5);
        LocalTime fiveMinutesLater = currentTime.plusMinutes(5);

        // Check if the time lies within the 5-minute range
        boolean withinFiveMinutesRange = time.isAfter(fiveMinutesAgo) && time.isBefore(fiveMinutesLater);

        return withinFiveMinutesRange;
    }
}
