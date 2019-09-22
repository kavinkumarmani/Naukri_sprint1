package com.naukri.Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.naukri.Base.Baseclass;
import com.naukri.Utilities.Dataproviderdata;
import com.naukri.Utilities.Excelwrite;

public class Naukri_Datacollection_page extends Baseclass {

	Excelwrite excelwrite;
	public static File file;
	public static OutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	// pagefactory or object repository
	@FindBy(name = "qp")
	WebElement Inputsearch;

	@FindBy(name = "ql")
	WebElement location;

	@FindBy(xpath = "(//input[@class='sdTxt w85'])[1]")
	WebElement experience;

	@FindBy(xpath = "(//li[@id='a3'])[1]")
	WebElement exp_years;

	@FindBy(id = "qsbFormBtn")
	WebElement searchbutton;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	WebElement Nextbutton;

	@FindBy(xpath = "//a[@id='jdUrl']")
	List<WebElement> totaljobs;

	@FindBy(xpath = "//div[@class='sortBy']")
	WebElement relevance;

	@FindBy(xpath = "//div[@class='drop']/ul/li[1]")
	WebElement Date;

	public Naukri_Datacollection_page() throws Exception {
		PageFactory.initElements(driver, this);
	}

	public void Technologywise_company_details(String tech, String loc) throws Exception {

		// search actions
		Inputsearch.sendKeys(tech);
		location.sendKeys(loc);
		Actions action = new Actions(driver);
		// action.click(experience).build().perform();
		// action.click(exp_years).build().perform();
		searchbutton.click();
		action.moveToElement(relevance).build().perform();
		action.click(Date).build().perform();

		// intialize excelwrite
		excelwrite = new Excelwrite(tech);
		do {
			for (int i = 0; i < totaljobs.size(); i++) {
				// getting all the required details from front page
				String jobtitle = driver.findElement(By.xpath("(//a[@id='jdUrl'])[" + (i + 1) + "]")).getText();
				String companyname = driver.findElement(By.xpath("(//span[@class='org'])[" + (i + 1) + "]")).getText();
				String requiredexperience = driver.findElement(By.xpath("(//span[@class='exp'])[" + (i + 1) + "]"))
						.getText();
				String location = driver.findElement(By.xpath("(//span[@class='loc'])[" + (i + 1) + "]")).getText();
				String postedday = driver.findElement(By.xpath("(//span[@class='date'])[" + (i + 1) + "]")).getText();
				LocalDate date1 = excelwrite.datefunction(postedday);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				String posteddate = date1.format(formatter);
				System.out.println(posteddate);
				// click on individual company walkins
				driver.findElement(By.xpath("(//a[@id='jdUrl'])[" + (i + 1) + "]")).click();
				// switching to child window
				Set<String> secondwindow = driver.getWindowHandles();
				Iterator<String> it1 = secondwindow.iterator();
				String parentwindow = it1.next();
				String childwindow = it1.next();
				driver.switchTo().window(childwindow);
				// clicking on contact details
				String contactinfo = "";
				try {
					driver.findElement(By.id("viewCont_trg")).click();
					contactinfo = driver.findElement(By.id("viewContact")).getText();
					driver.close();
					driver.switchTo().window(parentwindow);
				} catch (Exception e) {
					contactinfo = "Not available";
					driver.close();
					driver.switchTo().window(parentwindow);
				}
				excelwrite.excelwriteutil(jobtitle, companyname, requiredexperience, location, posteddate, contactinfo);
			}
			try {
				Nextbutton.click();
			} catch (Exception e) {
				break;
			}

		} while (true);
		excelwrite.write();
	}

}
