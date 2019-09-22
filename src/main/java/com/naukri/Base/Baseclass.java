package com.naukri.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Baseclass {
	public static WebDriver driver;
	public static Properties prop;
	public static File file;
	public static OutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	public Baseclass() throws Exception {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Saran\\Desktop\\kavin test materials\\Naukri_final\\src\\main\\java\\com\\naukri\\Proprties\\naukri.properties");
		prop.load(fis);
	}
	
	public static void initilization() {
		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Saran\\Downloads\\selenium\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} 
		else {
			System.out.println("There is no properties assigned for the browser");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void closepopups() {
		String parentwindow = driver.getWindowHandle();
		// handle second window or multiple popups
		Set<String> totalwindows = driver.getWindowHandles();
		Iterator<String> it = totalwindows.iterator();
		while (it.hasNext()) {
			String childwindow = it.next();
			if (!parentwindow.equalsIgnoreCase(childwindow)) {
				// closing child window
				driver.switchTo().window(childwindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentwindow);
	}
}
