package utils;


import org.joda.time.DateTime;

public class TimeUtils {
	
	public static int compareMonths(String startDateStr, String endDateStr){
		DateTime startDate = new DateTime(startDateStr);
		DateTime endDate = new DateTime(endDateStr);
		int beginYear = startDate.getYear();
		int endYear = endDate.getYear();
		
		int beginMonth = startDate.getMonthOfYear();
		int endMonth = endDate.getMonthOfYear();		
		int months = 0;
		/**
		 * 20100301 - 2012-07-01
		 * (12-3+1)+7+12*(12-10-1)
		 * 
		 */
		if ((endYear-beginYear)>0 ){
			months = (12-beginMonth+1)+endMonth+12*(endYear-beginYear-1);
		}
		else if ((endYear-beginYear)==0){
			months = endMonth-beginMonth+1;
		}
		return months;
	}
	
}
