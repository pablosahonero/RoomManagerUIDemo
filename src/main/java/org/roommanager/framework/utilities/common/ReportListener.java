package org.roommanager.framework.utilities.common;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ReportListener extends TestListenerAdapter{
	  
	  @Override
	  public void onTestSuccess(ITestResult tr){
		  String testName = tr.getTestContext().getName();
		  LogManager.info("Test : \"" + testName + "\" PASSED" );
		  ReportManager.appendTestCaseName(testName);
	  }
	  
	  @Override
	  public void onTestFailure(ITestResult tr){
		  String testName = tr.getTestContext().getName();
		  
		  String errorMessage = tr.getThrowable().getMessage();
		  LogManager.info("Test : \"" + testName + "\" FAILED" );
		  String filePath = ScreenShotManager.takeScreenShot(testName);
		  if(tr.getThrowable().getClass().isAssignableFrom(AssertionError.class)){
			  ReportManager.appendTestCaseErrorMessage(testName, errorMessage);
		  }
		  else{
			  ReportManager.appendTestCaseName(testName);
		  }
		  ReportManager.appendImageHyperLink(filePath);
	  }
	  
	  @Override
	  public void onTestStart(ITestResult tr){
		  String testName = tr.getTestContext().getName();
		  LogManager.info("Test : \"" + testName + "\" STARTED" );
		  ReportManager.appendTestCaseName(testName);
	  }
}
