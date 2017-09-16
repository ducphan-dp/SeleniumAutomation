package selenium.automation.webdriver;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import selenium.automation.utils.SnapShotUtil;

public class UIOperation {
	private WebDriver driver;
	private Properties properties;
	
	public UIOperation(WebDriver driver, Properties properties) {
		this.driver = driver;
		this.properties = properties;
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
		Object result = null;
		switch (operation.toUpperCase()) {
		case "CLICK":
			driver.findElement(getObject(objectName, objectType)).click();
			break;
		
		case "SUBMIT":
			driver.findElement(getObject(objectName, objectType)).submit();
			break;
			
		case "SET_TEXT":
			driver.findElement(getObject(objectName, objectType)).sendKeys(value);
			break;
			
		case "CLEAR":
			driver.findElement(getObject(objectName, objectType)).clear();
			break;
			
		case "GO_URL":
			driver.get(properties.getProperty(value));
			break;
		
		case "SCREEN_SHOT":
			SnapShotUtil.takeSnapShot(driver, value);
			break;
			
		case "GET_TEXT":
			result = driver.findElement(getObject(objectName, objectType)).getText();
			break;
			
		case "GET_ATTRIBUTE":
			result = driver.findElement(getObject(objectName, objectType)).getAttribute(value);
			break;
			
		case "GET_TAG_NAME":
			result = driver.findElement(getObject(objectName, objectType)).getTagName();
			break;
			
		case "IS_ENABLED":
			result = driver.findElement(getObject(objectName, objectType)).isEnabled();
			break;
		
		case "IS_DISPLAYED":
			result = driver.findElement(getObject(objectName, objectType)).isDisplayed();
			break;
			
		case "IS_SELECTED":
			result = driver.findElement(getObject(objectName, objectType)).isSelected();
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
	private By getObject(String objectName, String objectType) throws Exception {
		switch (objectType.toUpperCase()) {
		case "XPATH":
			return By.xpath(properties.getProperty(objectName));

		case "CLASS_NAME":
			return By.className(properties.getProperty(objectName));
		
		case "CSS_SELECTOR":
			return By.cssSelector(properties.getProperty(objectName));
			
		case "NAME":
			return By.name(properties.getProperty(objectName));
			
		case "ID":
			return By.id(properties.getProperty(objectName));
			
		case "LINK":
			return By.linkText(properties.getProperty(objectName));
			
		case "PARTIAL_LINK":
			return By.partialLinkText(properties.getProperty(objectName));
			
		default:
			throw new Exception("Wrong object type");
		}
	}

}
