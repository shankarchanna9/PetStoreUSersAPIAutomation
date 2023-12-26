package com.qa.petstore.extentreports;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager implements ITestListener {

	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	String reportName;
	
	@BeforeTest
	public void onStart(ITestContext contest) {
		String simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report" + simpleDateFormat + ".html";

		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\" + reportName);
		extentSparkReporter.config().setDocumentTitle("PetStore_Users-Automation-Reports");
		extentSparkReporter.config().setReportName("Pet Store Users API");
		extentSparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();

		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Application", "Pet Store Users API");
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Username", System.getProperty("user.name"));
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("User", "QA Member");
	}
	
	public void onTestSuccess(ITestResult result) {
		extentTest=extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.PASS, "Test -- Passed");	
	}
	
	public void onTestFailure(ITestResult result) {
		extentTest=extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.FAIL, "Test -- Failed");
		extentTest.log(Status.FAIL,result.getThrowable().getMessage());
	}
	public void onTestSkipped(ITestResult result) {
		extentTest=extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.SKIP, "Test -- Skipped");
		extentTest.log(Status.SKIP, result.getThrowable().getMessage());
	}
	public void onFinish(ITestResult result) {
		extentReports.flush();
	}
}
