import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DayHourGeneratorTest {

	DayHourGenerater d;
	
	@Before
	public void setUp() throws Exception {
		d = new DayHourGenerater();
	}

	@Test
	public void testGetWeekday() {
		int weekday = 4;
		int weekdayValue = d.getWeekDay("\"4/2/2014 8:52:00\",40.7412,-74.0034,\"B02512\"");
		
		assertEquals("Weekday should be 4", weekday, weekdayValue);
	}
	
	@Test
	public void testGetHour(){
		int hour = 8; 
		int hourValue = d.getHour("\"4/2/2014 8:52:00\",40.7412,-74.0034,\"B02512\"");
		
		assertEquals("Hour should be 8", hour, hourValue);
	}

}
