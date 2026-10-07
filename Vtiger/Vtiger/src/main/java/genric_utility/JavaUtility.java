package genric_utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class JavaUtility {

	public int generateRandomNumber(int limit) {
		Random zc = new Random();
		return zc.nextInt(limit);
	}
	public static String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hhmmss_ddMMMyyyy");
		String time = now.format(dtf);
		return time;
	}
}