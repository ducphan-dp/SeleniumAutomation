package selenium.automation.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindByXpath {
	
	// Basic HTML
	public static String DOCTYPE_BASIC_ELEMENT = "DOCTYPE";
	public static String HTML_BASIC_ELEMENT = "html";
	public static String HEAD_BASIC_ELEMENT = "head";
	public static String TITLE_BASIC_ELEMENT = "title";
	public static String BODY_BASIC_ELEMENT = "body";
	public static String H1_BASIC_ELEMENT = "h1";
	public static String H2_BASIC_ELEMENT = "h2";
	public static String H3_BASIC_ELEMENT = "h3";
	public static String H4_BASIC_ELEMENT = "h4";
	public static String H5_BASIC_ELEMENT = "h5";
	public static String H6_BASIC_ELEMENT = "h6";
	public static String P_BASIC_ELEMENT = "p";
	public static String BR_BASIC_ELEMENT = "br";
	public static String HR_BASIC_ELEMENT = "hr";
	
	// Formatting
	public static String ACRONYM_FORMATTINGELEMENT = "html";
	
	private WebDriver driver;
	
	public FindByXpath(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement findByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	public WebElement findByTag(String tagName, String attribute, String value) {
		String tagXpath = "";
		
		return findByXpath(tagXpath);
	}
	
	public WebElement findByInput(String attriubte, String value) {
		String inputXpath = "";
		
		return findByXpath(inputXpath);
	}

}
