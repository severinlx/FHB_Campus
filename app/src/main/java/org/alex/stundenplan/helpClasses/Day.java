package org.alex.stundenplan.helpClasses;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


//A class to help Day Format for Mensa

public class Day {

	// String today = "today";
	// String day = "day";

	protected static int dayNumber;
	protected static String[] week={ 
		       "mon",
		       "tue",
		       "wed",
		       "thu",
		       "fri",
		       "sat",
		       "sun"
	      };
	 protected static String[] weekDE={ 
		       "Montag",
		       "Dienstag",
		       "Mittwoch",
		       "Donnerstag",
		       "Freitag",
		       "Samstag",
		       "Sontag"
	    };
		public static void setDayAsToday() {

			SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.US);
			Calendar calendar = Calendar.getInstance();
			String today = dayFormat.format(calendar.getTime()).toLowerCase(Locale.US);
			dayNumber= getDayNumber(today) ;
			if(dayNumber==6) dayNumber=0;

		}
		
		public static void increaseDay(){
			dayNumber = dayNumber+1;
			// after samstag, next is mon:
			if(dayNumber==6)dayNumber=0;
		}
		
		public static void decreaseDay(){
			dayNumber = dayNumber-1;
			//befor mon, ist sat:
			if(dayNumber==-1)dayNumber=5;
		}
		
		public int getDayNumber(){
			return this.dayNumber;
		}
		
		
		public static String fineDate(String date){
            System.out.println(date);

            if(date.length()<7){

                return "in Menu: ";
            }

			//date = "01.04.2015";
			Calendar calender = Calendar.getInstance();
			Integer weekDay;
			String dayName = null, monthString, monthDay;
			//parse day, month, year from string:
			monthDay = date.substring(0, 2);
			Integer day = Integer.parseInt(monthDay);
			Integer month = Integer.parseInt(date.substring(3, 5));
			Integer year = Integer.parseInt(date.substring(6, 10));
			
			//set calender, dont forget that moths are from  0 -> 11 ...
			calender.set(year, month-1, day);
			
			//obtain day as string:
			weekDay = calender.get(Calendar.DAY_OF_WEEK);
			switch(weekDay){
			case Calendar.MONDAY:    dayName= "Montag";     break;
			case Calendar.TUESDAY:   dayName= "Dienstag";   break;
			case Calendar.WEDNESDAY: dayName= "Mittwoch";   break;
			case Calendar.THURSDAY:  dayName= "Donnerstag"; break;
			case Calendar.FRIDAY:    dayName= "Freitag";    break;
			case Calendar.SATURDAY:  dayName= "Samstag";    break;
			case Calendar.SUNDAY:    dayName= "Sonntag";    break;
			}
			
			//obtain month as string:
			switch (month) {
			case 1:
				monthString = "Januar";
				break;
			case 2:
				monthString = "Februar";
				break;
			case 3:
				monthString = "März";
				break;
			case 4:
				monthString = "April";
				break;
			case 5:
				monthString = "Mai";
				break;
			case 6:
				monthString = "Juni";
				break;
			case 7:
				monthString = "Juli";
				break;
			case 8:
				monthString = "August";
				break;
			case 9:
				monthString = "September";
				break;
			case 10:
				monthString = "Oktober";
				break;
			case 11:
				monthString = "November";
				break;
			case 12:
				monthString = "Dezember";
				break;
			default:
				monthString = "Invalid month";
				break;
			}
			
			//obtain the output string for date display:
			String result = dayName+", der "+monthDay+". "+monthString;
			return result;
		}
		
		
		
		
		public static int getDayNumber(String day){
			for (int i = 0; i < week.length; i++) {
				if(day.equals(week[i])) return i;
			}
			 return -1;
		}

		public static String getToday() {
			setDayAsToday();
			return weekDE[dayNumber];
		}

		public static String getDay() {
			// TODO Auto-generated method stub
			return weekDE[dayNumber];
		}
		
		public static String getEnglishDay() {
			// TODO Auto-generated method stub
			return week[dayNumber];
		}

}
