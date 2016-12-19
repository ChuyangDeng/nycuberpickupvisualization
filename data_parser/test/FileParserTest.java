import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class FileParserTest {
	
	private FileParser f;
	private final Location GOOGLE = new Location(40.741724, -74.004165);
	private final Location PALANTIR = new Location(40.740040, -74.006825);

	@Before
	public void setUp() throws Exception {
		f = new FileParser("uber-raw-data-apr-jul14.csv");
	}

	@Test
	public void testDate() {
		Date date = new Date(4, 4, 2014);
		Date dateValue = f.getDate("\"4/4/2014 0:29:00\",40.7145,-74.0105,\"B02512\"");
		
		assertEquals("Date should be April 4th, 2014", date, dateValue);
	}
	
	@Test
	public void testHour(){
		int hour = f.getHour("\"4/4/2014 0:29:00\",40.7145,-74.0105,\"B02512\"");
		
		assertEquals("Hour should be 0", 0, hour);
	}
	
	@Test
	public void testParseDay(){
		ArrayList<String> day = f.parseDay(4, 4, 2014);
		int t = day.size()/2;

		Date date = new Date(4, 4, 2014);
		Date dateValue = f.getDate(day.get(t));
		
		assertTrue("Date should be May 2nd, 2014", date.equals(dateValue));
	}
	
	@Test
	public void testLocation(){
		Location l = GOOGLE;
		Location lValue = f.getLocation("\"4/3/2014 20:40:00\",40.7422,-74.0045,\"B02512\"");

		assertTrue("Location should be Google", l.equals(lValue));
	}
	
	@Test
	public void testGoogle(){
		ArrayList<String> google = f.getGoogle();
		int t = google.size()/2;
		
		Location l = GOOGLE;
		Location lValue = f.getLocation(google.get(t));
		
		assertTrue("Location should be Google", l.equals(lValue));
	}
	
	@Test
	public void testPalantir(){
		ArrayList<String> palantir = f.getPalantir();
		int t = palantir.size()/2;
		
		Location l = PALANTIR;
		Location lValue = f.getLocation(palantir.get(t));
		
		assertTrue("Location should be Palantir", l.equals(lValue));
	}

}
