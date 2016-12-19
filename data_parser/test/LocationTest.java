import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {

	Location l;
	
	@Before
	public void setUp() throws Exception {
		l = new Location(40.741724, -74.004165);
	}

	@Test
	public void testGetLat() {
		double lat = 40.741724;
		double latValue = l.getLat();
		
		assertEquals(lat, latValue, 0.0001);
	}
	
	@Test
	public void testGetLon() {
		double lon = -74.004165;
		double lonValue = l.getLon();
		
		assertEquals(lon, lonValue, 0.0001);
	}

}
