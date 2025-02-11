package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPAge;
import testbase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"sanity","master"})
	
	public void verify_login() throws InterruptedException
	
	{
		try
			{
		
		//HOmepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount page
		 MyAccountPAge mac=new  MyAccountPAge(driver);
		boolean targetPage= mac.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);
			}
		catch (Exception e)
		{
			Assert.fail();
		}
		
	}
	

	
}
