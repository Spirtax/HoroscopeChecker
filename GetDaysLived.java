import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;

public class GetDaysLived {
	
	public static int leapYearsLived(int birthdayDay, int birthdayMonth, int birthdayYear) throws DateTimeException {
		int leapDays = 0; //Number of leap days lived
		
		LocalDate localDate = LocalDate.of(birthdayYear, birthdayMonth, birthdayDay);
		for (int i = 0; i <= Calendar.getInstance().get(Calendar.YEAR) - birthdayYear; i++) {

			localDate = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR) - i, birthdayMonth, birthdayDay);
			if (localDate.isLeapYear() == true){
				leapDays++;
			}
		}
		return leapDays;
	}
		
	
	public static void getDaysLived(int birthdayDay, int birthdayMonth, int birthdayYear, int leapDays) {
	    
		int addDay = 0;
		
		if (birthdayMonth == 2 && birthdayDay == 29) {
			birthdayDay--;
			addDay++;
		}
		
		int daysLived = 0;
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		LocalDate localDate = LocalDate.of(birthdayYear, birthdayMonth, birthdayDay);
		
		int dayOfYearBorn = localDate.getDayOfYear();
		mainMethod.dayOfYearBorn = dayOfYearBorn;

		
		int daysOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		mainMethod.daysOfYear = daysOfYear;
		
		int yearsLivedToDays = (((currentYear - birthdayYear)) * 365 - dayOfYearBorn + leapDays - addDay); //subtract the months from time born (if born in June, subtract 6 months)
		
		daysLived = (yearsLivedToDays + daysOfYear); //FACTOR IN LEAP YEARS!
		mainMethod.daysLived = daysLived;
		
	}	
}
