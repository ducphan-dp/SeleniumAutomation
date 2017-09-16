package selenium.automation.test.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookLogin {
	
	WebDriver driver;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="pass")
	WebElement password;
	
	@FindBy(xpath = "//input[@value='Log In']")
	WebElement btnLogin;
	
	public FacebookLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setEmail(String strEmail) {
		email.sendKeys(strEmail);
	}
	public void setPassword(String strPassword) {
		password.sendKeys(strPassword);
	}
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void login(String strEmail, String strPassword) {
		setEmail(strEmail);
		setPassword(strPassword);
		clickLogin();
	}
}
