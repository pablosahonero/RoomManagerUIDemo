package org.roommanager.framework.utilities.common;

import java.io.File; 
import java.io.IOException; 
import java.text.DateFormat; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
import org.apache.commons.io.FileUtils; 
import org.openqa.selenium.OutputType; 
import org.openqa.selenium.TakesScreenshot; 
import org.openqa.selenium.WebDriver; 

public class ScreenShotManager { 
	
	public static String takeScreenShot(String testName){
		testName = testName.replace(" ", "_");
		String filePath = null;
		WebDriver driver = SeleniumDriverManager.getDriver(PropertiesReader.getBrowser());	 
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa"); /**/
		String destDir = "./reports/screenshots"; 
		new File(destDir).mkdirs(); 
		String destFile = testName +"-"+dateFormat.format(new Date()) + ".png"; 
		saveFile(scrFile, destFile, destDir);
		
		filePath ="..\\screenshots\\" + destFile;
		
		return filePath;
	}
	
	public static void saveFile(File sourceFile, String destinyFile, String destinyPath){
		try { 
			FileUtils.copyFile(sourceFile, new File(destinyPath + "/" + destinyFile)); 
		} 
		catch (IOException e) { 
			e.printStackTrace();
		} 
	}
}
