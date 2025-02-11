 package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	
	
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	
	//@FindBy(xpath="//a[normalize-space()='Register']")
	@FindBy(xpath="//a[normalize-space(text())='Register' and contains(@href, 'account/register')]")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement lnkLogin;
	
	
	public void clickMyAccount() throws InterruptedException {
	    // Use JavaScript Executor to click the My Account link
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Execute the JavaScript to click on the element directly
	    js.executeScript("arguments[0].click();", lnkMyaccount);
	}
	
	
	
	public void clickRegisterWithJS() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // Wait until element is present and visible
	    wait.until(ExpectedConditions.visibilityOf(lnkRegister));

	    // Use JavaScript to click if element is still not intractable directly
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lnkRegister);


	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}

}
