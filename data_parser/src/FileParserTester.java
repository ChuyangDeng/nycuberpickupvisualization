import java.util.ArrayList;

/**
 * This is the tester class for FileParser.
 * @author Paula
 *
 */
public class FileParserTester {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileParser fp = new FileParser("uber-raw-data-apr-jul14.csv");
		
		// select a day and parse trips on that day
		ArrayList<String> d = fp.parseDay(4, 4, 2014);
		fp.parseTime(d);
		
		fp.parseCompany();
		ArrayList<String> google = fp.getGoogle();
		System.out.println(google.size());
		
		
	}

}
