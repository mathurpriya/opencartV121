package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testbase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","master"})
	public void verify_account_registration() throws InterruptedException
	{
		logger.info("**Starting TC001_AccountRegistrationTest** ");
		
		try 
		{
		HomePage hp=new HomePage(driver);
		Thread.sleep(5000);
		hp.clickMyAccount();
		logger.info("clicked on my account link"); 
		Thread.sleep(5000);
		
		hp.clickRegisterWithJS();
		logger.info("clicked on my Register link"); 
	

	AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
	
	logger.info("Providing customer details "); 
	regpage.setFirstName(randomString().toUpperCase());
	regpage.setLAstName(randomString().toUpperCase());
	regpage.setEmail(randomString()+"@gmail.com"); //randomly generated email
	
	regpage.setTelephone(randomNumber());
	
	String password=randomAlphaNumberic() ;
	
	regpage.setPassword(password);
	regpage.setConfirmPassword(password);
	
	regpage.setPrivacyPolicy();
	regpage.clickContinue();
	
	logger.info("Validating expected msg ");
	String confmg=regpage.getConfirmationMsg();
	if(confmg.equals("Your Account Has Been Created!"))
	{
		Assert.assertTrue(true);
	}
	else {
		logger.error("Test failed");
		logger.debug("Debug logs"); 
		Assert.assertTrue(false );
	}
	//Assert.assertEquals(confmg,"Your Account Has Been Created!");
	
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info("**Starting TC001_AccountRegistrationTest** ");
		
	}

	
	

}
	

