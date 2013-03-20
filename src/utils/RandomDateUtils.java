package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class RandomDateUtils {
	public static DateTime getDate(String beginDateStr, String endDateStr) {          
        DateTime beginDate = new DateTime(beginDateStr);
        DateTime endDate = new DateTime(endDateStr);
        
        long date = random(beginDate.getMillis(),endDate.getMillis());

        return new DateTime(date);
	}

	private static long random(long begin, long end) {
		long rtnn = begin + (long) (Math.random() * (end - begin));
		if (rtnn == begin || rtnn == end) {
			return random(begin, end);
		}
		return rtnn;
	}

}
