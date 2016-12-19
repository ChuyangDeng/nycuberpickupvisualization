import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DateTest {
	
	Date d;

	@Before
	public void setUp() throws Exception {
		d= new Date(12, 19, 2016);
	}

	@Test
	public void testYear() {
		int year = 2016;
		int yearValue = d.getYear();
		
		assertEquals("Year should be 2016", year, yearValue);
	}
	
	@Test
	public void testEquals(){
		Date date = new Date(12, 19, 2015);
		
		assertFalse("Year should not be 2015", date.equals(d));
	}

}
