package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	ExtentSparkReporter sparkReporter; //ui of the report
	ExtentReports extend;//populate common info of the report
	ExtentTest test;//create test case entries in the report and update the status of the test method
	String repName;
	
	public void onStart(ITestContext testContext) 
	{
		
		
		String timestamp =  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName ="Test-Report-"+timestamp+".html";
		sparkReporter = new ExtentSparkReporter("./reports/" + repName);
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report");//Title of the Report
		sparkReporter.config().setReportName("opencart Functional Testing");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extend = new ExtentReports();
		extend.attachReporter(sparkReporter);
		extend.setSystemInfo("Applaction", "opencart");
		extend.setSystemInfo("Module", "Admin");
		extend.setSystemInfo("Sub Module", "Customers");
		extend.setSystemInfo("user Name", System.getProperty("user.name"));
		extend.setSystemInfo("Environement", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extend.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extend.setSystemInfo("browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extend.setSystemInfo("Groups", includedGroups.toString());
		}
		
    }
	
	public void onTestSuccess(ITestResult result) 
	{
		test = extend.createTest(result.getTestClass().getName());//create a new enty in the report
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.PASS,result.getName() + "got sucessfully executed" );//update the p/f/s
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test = extend.createTest(result.getTestClass().getName());//create a new enty in the report
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.FAIL, result.getName()+"got Failed");//update the p/f/s
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try
		{
			String imgpath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
			
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		test = extend.createTest(result.getTestClass().getName());//create a new enty in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"Got Skipped");//update the p/f/s
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) 
	{
		extend.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
    }

}
