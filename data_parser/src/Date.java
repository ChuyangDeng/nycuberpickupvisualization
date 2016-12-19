/**
 * This class creates a date object which has a year, a month and a day. 
 * @author Paula
 *
 */
public class Date {
	int year;
	int month;
	int day;
	
	/**
	 * This is the constructor.
	 * @param month
	 * @param day
	 * @param year
	 */
	public Date(int month, int day, int year){
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	/**
	 * Override the comparison method for two objects.
	 */
	@Override
	public boolean equals(Object that) {
		Date d = (Date) that;
		return d.getYear() == this.getYear() && d.getMonth() == this.getMonth() && d.getDay() == this.getDay();
	}
	
	
	/**
	 * This method represents the date object as a string.s
	 */
	public String toString(){
		String d = String.valueOf(month) + "/" + String.valueOf(day) + "/" + String.valueOf(year);
		return d;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	
	
}
