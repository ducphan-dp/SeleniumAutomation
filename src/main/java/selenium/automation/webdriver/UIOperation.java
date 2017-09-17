package selenium.automation.webdriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import selenium.automation.utils.SnapShotUtil;

public class UIOperation {
	private WebDriver driver;
	private Properties properties;
	
	/**
	 * UIOperation
	 * @param driver
	 * @param properties
	 */
	public UIOperation(WebDriver driver, Properties properties) {
		this.driver = driver;
		this.properties = properties;
	}
	
	/**
	 * UIOperation
	 * @param driver
	 */
	public UIOperation(WebDriver driver) {
		this.driver = driver;
		try (InputStream in = getClass().getClassLoader().getResourceAsStream("object.properties")) {
			properties = new Properties();
			properties.load(in);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * performance
	 * @param operation
	 * @param objectName
	 * @param objectType
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Object performance(String operation, String objectName, String objectType, String value) throws Exception {
		return performance(operation, objectName, objectType, value, false);
	}
	
	/**
	 * performance
	 * @param operation
	 * @param objectName
	 * @param objectType
	 * @param value
	 * @param simple
	 * @return
	 * @throws Exception
	 */
	public Object performance(String operation, String objectName, String objectType, String value, boolean simple) throws Exception {
		Object result = null;
		By byElement = getObject(objectName, objectType, simple);
		switch (operation.toUpperCase()) {
		case "CLICK":
			driver.findElement(byElement).click();
			break;
		
		case "SUBMIT":
			driver.findElement(byElement).submit();
			break;
			
		case "SEND_KEYS":
			driver.findElement(byElement).sendKeys(value);
			break;
			
		case "SELECT":
			Select select = new Select(driver.findElement(byElement));
			select.selectByVisibleText(value);
			break;
			
		case "CLEAR":
			driver.findElement(byElement).clear();
			break;
			
		case "GO_URL":
			driver.get(simple ? value : properties.getProperty(value));
			break;
		
		case "SCREEN_SHOT":
			SnapShotUtil.takeSnapShot(driver, value);
			break;
			
		case "GET_TEXT":
			result = driver.findElement(byElement).getText();
			break;
			
		case "GET_ATTRIBUTE":
			result = driver.findElement(byElement).getAttribute(value);
			break;
			
		case "GET_TAG_NAME":
			result = driver.findElement(byElement).getTagName();
			break;
			
		case "IS_ENABLED":
			result = driver.findElement(byElement).isEnabled();
			break;
		
		case "IS_DISPLAYED":
			result = driver.findElement(byElement).isDisplayed();
			break;
			
		case "IS_SELECTED":
			result = driver.findElement(byElement).isSelected();
			break;
			
		default:
			break;
		}
		
		return result;
	}
	
	/**
	 * getObject
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(String objectName, String objectType, boolean simple) throws Exception {
		String element = simple ? objectName : properties.getProperty(objectName);
		switch (objectType.toUpperCase()) {
		case "XPATH":
			return By.xpath(element);

		case "CLASS_NAME":
			return By.className(element);
		
		case "CSS_SELECTOR":
			return By.cssSelector(element);
			
		case "NAME":
			return By.name(element);
			
		case "ID":
			return By.id(element);
			
		case "LINK":
			return By.linkText(element);
			
		case "PARTIAL_LINK":
			return By.partialLinkText(element);
			
		default:
			return null;
		}
	}

}
