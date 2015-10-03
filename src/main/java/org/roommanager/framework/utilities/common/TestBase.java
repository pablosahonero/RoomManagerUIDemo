package org.roommanager.framework.utilities.common;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.Listeners;
import org.testng.xml.XmlSuite;
@Listeners({org.roommanager.framework.utilities.common.TestBase.class})
public class TestBase implements ISuiteListener,IReporter{
	protected static WebDriver driver = null;
	@Override
	public void onStart(ISuite arg0) {
		driver = SeleniumDriverManager.getDriver(PropertiesReader.getBrowser());
		LogManager.info("Browser <" + PropertiesReader.getBrowser() + "> was opened");
    }
	@Override
    public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String arg2) {           
    	driver.quit();
    	LogManager.info("Browser <" + PropertiesReader.getBrowser() + "> was closed");
    }
	@Override
	public void onFinish(ISuite suite) {

	}
}
