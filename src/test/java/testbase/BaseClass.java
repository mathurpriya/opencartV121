package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.Browser;


public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	
	@BeforeClass(groups= {"sanity","Regression","master"})
	@Parameters({"os","browser"})
	
	public void setup(String os,String br) throws IOException
	{
		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		//below peace of code is when we want to run our execution on remote machines
		
		//to setup os
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			 
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WINDOWS);
				
			}
			
			else if (os.equalsIgnoreCase("linux"))
			{
		
				capabilities.setPlatform(Platform.LINUX);
			}
		
			else
			{
				System.out.println("no matching os");
				return;
			}
			
			//to setup browswer
			
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox": capabilities.setBrowserName("firefox");break;
			default: 
				System.out.println("no matching browser");return;
			
			}
			 driver =new RemoteWebDriver(new URL("http://192.168.1.73:4444"),capabilities );
			 }
		
			//this is normal set up to run execution on local environment
			
			if(p.getProperty("execution_env").equalsIgnoreCase("local"))
			{
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;	
		case "firefox" : driver=new FirefoxDriver(); break;
		default : System.out.println("Invalid browser"); return; //return will help to exit from entire statements if browser is invalid because there is no point to execute rest of the statements if browser itself is not valid if we dont put return then it will try to execute rest of the statement
		
		}}
			
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); //reading url from properties file, instead of hardcoding 
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"sanity","Regression","master"})
	
	public void tearDown()
	{
		 driver.quit();
	}
	
	public  String randomString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(0);
		return generatedstring;
		
		
	}
	

	public  String randomNumber() // for random number
	{
		String generatednumber=RandomStringUtils.randomNumeric(10); 
		return generatednumber;
		
		
	}
	
	
	public  String randomAlphaNumberic() // for password becoz pass is combination of number and numerics
	{
		String generatednumber=RandomStringUtils.randomNumeric(3); 
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		return (generatednumber+"@"+generatedstring);
		
		
	}
	
	/*public  String captureScreen(String tname) {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=
	}*/
	

}
