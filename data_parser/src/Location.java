/**
 * This class creates location object which has a latitude and a longitude.
 * @author Paula
 *
 */
public class Location {
	private double lon;
	private double lat;
	private final double E = 0.001;
	
	public Location(double lat, double lon){
		this.lon = lon;
		this.lat = lat;
	}
	
	/**
	 * Override the comparison method for two objects.
	 */
	@Override
	public boolean equals(Object that) {
		Location l = (Location) that;
		boolean equals = Math.abs(l.getLat()-this.lat) < E && Math.abs(l.getLon()-this.lon) < E;
		return equals;
	}

	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	
}
