package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class BaseClass {
	
public static WebDriver driver;
public Logger log;
public Properties prop;
	
	@BeforeClass(groups= {"sanity","master","regression"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException
	{
		
		//Loading config.properties File
		FileReader file = new FileReader(".//src/test//resources//config.properties");
		prop = new Properties();
		prop.load(file);

		log = LogManager.getLogger(this.getClass()); //create log
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			 DesiredCapabilities cap = new DesiredCapabilities();
			 //os
			 if(os.equalsIgnoreCase("windows"))
			 {
				 cap.setPlatform(Platform.WIN10);
			 }
			 else if(os.equalsIgnoreCase("linux"))
			 {
				 cap.setPlatform(Platform.LINUX);
			 }
			 else if(os.equalsIgnoreCase("mac"))
			 {
				 cap.setPlatform(Platform.MAC);
			 }
			 else
			 {
				 System.out.println("NO Match OS");
				 return;
			 }
			 
		    //Browser
			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox": cap.setBrowserName("firefox"); break;
			default : System.out.println("no matching broswer"); return; //return exit from this method not execute after code
			}
			URL hubURL = new URL("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(hubURL, cap);
		}
		
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			//case "firefox": driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name"); return; //return exit from this method not execute after code
			}
			
		}
		
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"sanity","master","regression"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String captureScreen(String tname)  throws IOException
	{
		
		String timestamp =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		//1) Full page screenshot
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourcefile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetfilePath = System.getProperty("user.dir")+ "\\screenshots\\"+tname+"_"+timestamp+".png";
		File targetfile = new File(targetfilePath);
		sourcefile.renameTo(targetfile);
		
		return targetfilePath;

	}
	
	
	public String generateRandomString() 
	{

	    RandomStringGenerator generator = new RandomStringGenerator.Builder()
	            .withinRange('a', 'z')   // alphabets only
	            .get();

	    return generator.generate(5);    // generate 5 characters
	}
	
	public String generateRandomNumber() {

	    RandomStringGenerator generator = new RandomStringGenerator.Builder()
	            .withinRange('0', '9')   // digits 0 to 9
	            .get();

	    return generator.generate(10);   // generate 10 digit number
	}
	
	
	public String generateRandomStringNumber()
	{
		 RandomStringGenerator generatorString = new RandomStringGenerator.Builder()
		            .withinRange('a', 'z')   // alphabets only
		            .get();
		 
		 RandomStringGenerator generatorNumber = new RandomStringGenerator.Builder()
		            .withinRange('0', '9')   // digits 0 to 9
		            .get();
		 String randomLetters = generatorString.generate(3);
		 String randomDigits  = generatorNumber.generate(3); 
		 
		 String finalvalue = randomLetters + randomDigits;
		 return finalvalue;
	}		

			
	
	/*public String rendomeString()
	{
		String Genstring = RandomStringUtils.randomAlphabetic(5);
		return Genstring;
	}*/
	
	/*public String rendomeNumber()
	{
		String GenNumber = RandomStringUtils.randomNumeric(10);
		return GenNumber;
	}*/
	
	

}
