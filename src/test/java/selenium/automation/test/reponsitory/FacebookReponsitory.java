package selenium.automation.test.reponsitory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FacebookReponsitory {
	private Properties properties;
	
	public Properties getObjectReponsitory() {
		try (InputStream in = getClass().getClassLoader().getResourceAsStream("facebook.properties")) {
			properties = new Properties();
			properties.load(in);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return properties;
	}

}
