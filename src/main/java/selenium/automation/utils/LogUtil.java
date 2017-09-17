package selenium.automation.utils;

import org.apache.log4j.Logger;

public class LogUtil {
	public static final String DEBUG = "DEBUG";
	public static final String INFO = "INFO";
	static Logger log = Logger.getLogger(LogUtil.class.getName());
	
	/**
	 * writeLog
	 * @param message
	 */
	public static void writeLog(String message) {
		log.error(message);
	}
}
