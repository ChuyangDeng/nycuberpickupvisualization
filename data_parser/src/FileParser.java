import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class parses the raw Uber pick-up data. 
 * It writes out a csv file of trips on a given day.
 * It also writes out csv files of trips starting at selected locations. 
 * @author Paula
 *
 */
public class FileParser {	
	FileReader f;
	
	private ArrayList<String> hour03 = new ArrayList<>();
	private ArrayList<String> hour36 = new ArrayList<>();
	private ArrayList<String> hour69 = new ArrayList<>();
	private ArrayList<String> hour912 = new ArrayList<>();
	private ArrayList<String> hour1215 = new ArrayList<>();
	private ArrayList<String> hour1518 = new ArrayList<>();
	private ArrayList<String> hour1821 = new ArrayList<>();
	private ArrayList<String> hour2124 = new ArrayList<>();
	
	ArrayList<String> tripsInADay = new ArrayList<>();
	
	private ArrayList<String> google = new ArrayList<>();
	private ArrayList<String> palantir = new ArrayList<>();
	
	/**
	 * This is the constructor.
	 * @param file
	 */
	public FileParser(String file){
		try {
			f = new FileReader(file);
		} catch (Exception e){
			System.out.println("Please enter a valid file name.");
		}
	}
	

	/**
	 * This method analyzes locations in the trip data set. 
	 * It saves trips starting near Google office or Palantir office in the corresponding arrayList.
	 */
	public void parseCompany() {
		final Location GOOGLE = new Location(40.741724, -74.004165);
		final Location PALANTIR = new Location(40.740040, -74.006825);
		
		for (String str:f.getLines()){
			// skip the first line
			if (str.equals(f.getLines().get(0))){
				continue;
			}
			
			Location l = getLocation(str);

			if (l.equals(GOOGLE)){
				google.add(str);
			} else if (l.equals(PALANTIR)) {
				palantir.add(str);
			}
		}
		
		try {
			writeCompany();
		} catch (Exception e){
			System.out.println("File doesn't exist");
		}

	}
	
	/**
	 * This method gets location in the input string.
	 * @param str input string
	 * @return Location object
	 */
	public Location getLocation(String str){

		String[] arr = str.split(",");
		double lat = Double.parseDouble(arr[1]);
		double lon = Double.parseDouble(arr[2]);
		
		Location l = new Location(lat, lon);
		
		return l;
	}
	
	/**
	 * @return the google arrayList
	 */
	public ArrayList<String> getGoogle() {
		parseCompany();
		return google;
	}

	/**
	 * @return the palantir arrayList
	 */
	public ArrayList<String> getPalantir() {
		parseCompany();
		return palantir;
	}
	
	/**
	 * This method parses input data according the time it happened.
	 * @param dayTrips an arrayList containing trips on a certain day
	 */
	public void parseTime(ArrayList<String> dayTrips) {
	
		for (String str:dayTrips){
			int hour = getHour(str);
			
			switch(hour){
			case 0:
			case 1:
			case 2: hour03.add(str); break;
			case 3:
			case 4:
			case 5: hour36.add(str); break;
			case 6:
			case 7:
			case 8: hour69.add(str); break;
			case 9:
			case 10:
			case 11: hour912.add(str); break;
			case 12:
			case 13:
			case 14: hour1215.add(str); break;
			case 15:
			case 16:
			case 17: hour1518.add(str); break;
			case 18:
			case 19:
			case 20: hour1821.add(str); break;
			case 21:
			case 22:
			case 23: hour2124.add(str); break;
			}	
		}
		
		try {
			writeHourTrip();
		} catch (Exception e){
			System.out.println("File doesn't exist");
		}
	}


	/**
	 * This method returns trips on a certain day
	 * @param month
	 * @param day
	 * @param year
	 * @return an arrayList of trips happened on the specified date
	 */
	public ArrayList<String> parseDay(int month, int day, int year){
		if (year != 2014 || month < 1 || month > 12 || day < 1 || day > 31){
			throw new IllegalArgumentException();
		}

		Date inputDate = new Date(month, day, year);

		for (int i = 0; i < f.getLines().size(); i++){
			String str = f.getLines().get(i);
			// skip the first line
			if (str.equals(f.getLines().get(0))){
				continue;
			}
			
			Date date = getDate(str);

			if (date.equals(inputDate)){
				tripsInADay.add(str);
			}
		}

		return tripsInADay;
	}
	
	/**
	 * This method gets date from an input string.
	 * @param str input string
	 * @return Date object
	 */
	public Date getDate(String str){
		Date date;

		// set empty date if exception occurs
		try {
		String[] arr = str.split(",");
		String[] arr1 = arr[0].split(" ")[0].split("\"")[1].split("/");
		int m = Integer.parseInt(arr1[0]);
		int d = Integer.parseInt(arr1[1]);
		int y = Integer.parseInt(arr1[2]);
		date = new Date(m, d, y);
		} catch (Exception e){
			date = new Date(0, 0, 0);
		}

		return date;
	}
	
	/**
	 * This method gets hour from an input string.
	 * @param str input string
	 * @return an integer between 0 to 23
	 */
	public int getHour(String str){
		int hour;
		
		// return -1 if exception occurs
		try{
		String[] arr = str.split(",");
		String[] arr1 = arr[0].split(" ")[1].split("\"")[0].split(":");
		hour = Integer.parseInt(arr1[0]);
		} catch (Exception e){
			return -1;
		}
		
		return hour;
	}
	
	/**
	 * This is a helper method that writes company data onto csv files.
	 * @throws FileNotFoundException
	 */
	private void writeCompany() throws FileNotFoundException{
	
		PrintWriter g = new PrintWriter("Google.csv");
		for (String str : google){
			g.println(str);
		}
		g.close();
		
		PrintWriter p = new PrintWriter("Palantir.csv");
		for (String str : palantir){
			p.println(str);
		}
		p.close();
		
	}
	
	/**
	 * This is a helper method that writes trips on a certain day onto a csv file.
	 * @throws FileNotFoundException
	 */
	private void writeDateTrip() throws FileNotFoundException{
		PrintWriter dateTrip = new PrintWriter("DateTrip.csv");
		for (String str : tripsInADay){
			dateTrip.println(str);
		}
		dateTrip.close();		
	}
	
	/**
	 * This is a helper method that writes hourly trips on a certain day onto csv files.
	 * @throws FileNotFoundException
	 */
	private void writeHourTrip() throws FileNotFoundException{
		PrintWriter hourTrip03 = new PrintWriter("Hour0-3.csv");
		for (String str : hour03){
			hourTrip03.println(str);
		}
		hourTrip03.close();
		PrintWriter hourTrip36 = new PrintWriter("Hour3-6.csv");
		for (String str : hour36){
			hourTrip36.println(str);
		}
		hourTrip36.close();
		PrintWriter hourTrip69 = new PrintWriter("Hour6-9.csv");
		for (String str : hour69){
			hourTrip69.println(str);
		}
		hourTrip69.close();
		PrintWriter hourTrip912 = new PrintWriter("Hour9-12.csv");
		for (String str : hour912){
			hourTrip912.println(str);
		}
		hourTrip912.close();
		PrintWriter hourTrip1215 = new PrintWriter("Hour12-15.csv");
		for (String str : hour1215){
			hourTrip1215.println(str);
		}
		hourTrip1215.close();
		PrintWriter hourTrip1518 = new PrintWriter("Hour15-18.csv");
		for (String str : hour1518){
			hourTrip1518.println(str);
		}
		hourTrip1518.close();
		PrintWriter hourTrip1821 = new PrintWriter("Hour18-21.csv");
		for (String str : hour1821){
			hourTrip1821.println(str);
		}
		hourTrip1821.close();
		PrintWriter hourTrip2124 = new PrintWriter("Hour21-24.csv");
		for (String str : hour2124){
			hourTrip2124.println(str);
		}
		hourTrip2124.close();
	}
	

	
}
