package org.roommanager.framework.utilities.common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumDriverManager {

	private static SeleniumDriverManager instance = null;
	private static WebDriver driver = null;
	
	private SeleniumDriverManager(){
		
	}
	public static WebDriver getDriver(String browser){
		switch (browser) {
			case "CHROME":
				return chromeDriver();
			case "FIREFOX":
				return firefoxDriver();
			default:
				return chromeDriver();
		}
	}
	public static WebDriver firefoxDriver(){
		if(driver == null || driver.toString().contains("(null)")){
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
	public static WebDriver chromeDriver(){
		if(driver == null || driver.toString().contains("(null)")){
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
	public static SeleniumDriverManager getInstance(){
		if(instance == null)
			instance = new SeleniumDriverManager();
		return instance;
	}
}
