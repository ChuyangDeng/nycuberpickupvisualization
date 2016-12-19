import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class finds pickups close to tech offices and break down the data by weekday and hour. 
 * It will also write out the data in tsv files.
 * @author smu
 *
 */
public class DayHourGenerater {

	FileParser fp = new FileParser("uber-raw-data-apr-jul14.csv");
	private ArrayList<String> google;
	private ArrayList<String> palantir;
	
	// create a 2D array to store data. three columns are : day, hour, count
	private int[][] googleDayHour;
	private int[][] palantirDayHour;
	
	/**
	 * This is the constructor
	 * @throws Exception
	 */
	public DayHourGenerater() throws Exception{
		getGoogleDayHour();
		getPalantirDayHour();
	}
	
	/**
	 * This is a helper method that writes company data onto tsv files.
	 * @param array company arrayList
	 * @throws FileNotFoundException
	 */
	private void writeFile(int[][] array) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter("output.tsv");
		for (int i = 0; i < array.length; i++){
			pw.println(array[i][0] + "\t" + array[i][1] + "\t" + array[i][2]);
		}
		pw.close();
	}
	
	/**
	 * @return the googleDayHour
	 */
	public int[][] getGoogleDayHour() {
		google = fp.getGoogle();
		googleDayHour = fillArray(google);
		
		try {
			writeFile(googleDayHour);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return googleDayHour;
	}
	
	/**
	 * @return the googleDayHour
	 */
	public int[][] getPalantirDayHour(){
		palantir = fp.getPalantir();
		palantirDayHour = fillArray(palantir);
		
		try {
			writeFile(palantirDayHour);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return palantirDayHour;
	}

	/**
	 * This is a helper method that fills in company array
	 * @param company arrayList
	 * @return 2D array
	 */
	private int[][] fillArray(ArrayList<String> company){
		int[][] day_hour = new int [7*24][3];
		initializeArray(day_hour);
		int day;
		int hour;
		int row;
		
		// loop through the google arrayList and fill in the day_hour 2d array
		
		for (String str: company){
			day = getWeekDay(str);
			hour = getHour(str);
			
			row = (day-1) * 24 + hour;
			day_hour[row][2] ++;
		}
		
		return day_hour;
		
	}
	
	/**
	 * This method returns the hour of the trip given a string
	 * @param str a string from the company arrayList
	 * @return an integer between 0 - 23
	 */
	public int getHour(String str){
		int hour;
		
		String[] arr = str.split(",");
		String[] arr1 = arr[0].split(" ")[1].split("\"")[0].split(":");
		
		hour = Integer.parseInt(arr1[0]);
		
		return hour;
	}
	
	/**
	 * This method returns the day of the week given a string
	 * @param str a string from the company arrayList
	 * @return an integer where 1 is Sunday and 7 is Saturday
	 */
	public int getWeekDay(String str){
		int day;
		
		String[] arr = str.split(",");
		String[] arr1 = arr[0].split(" ")[0].split("\"")[1].split("/");
		int m = Integer.parseInt(arr1[0]) - 1;
		int d = Integer.parseInt(arr1[1]);
		int y = Integer.parseInt(arr1[2]);
		
		Calendar c = new GregorianCalendar();
		c.set(y, m, d);
		
		day = c.get(Calendar.DAY_OF_WEEK);
		
		return day;
	}
	
	/**
	 * This is a helper method that initializes the array.
	 * @param array
	 */
	private void initializeArray(int[][] array){
		for(int i = 0; i < 7*24; i++){
			array[i][0] = i/24 + 1;
			array[i][1] = i % 24;
		}
	}
}
