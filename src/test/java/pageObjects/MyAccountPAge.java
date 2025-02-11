package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPAge extends BasePage {
	
	public  MyAccountPAge (WebDriver driver) {
		super(driver);
	}
	 
 @FindBy(xpath="//h2[text()='My Account']")
	WebElement msgHeading;
 
 public boolean isMyAccountPageExists()
 {
	  try
	  {
		  return (msgHeading.isDisplayed());
		  
	  }
	  catch(Exception e)
	  {
		  return false;
	  }
 }
	

}
