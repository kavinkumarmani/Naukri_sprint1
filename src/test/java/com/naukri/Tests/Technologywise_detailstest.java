package com.naukri.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.naukri.Base.Baseclass;
import com.naukri.Pages.Homepage;
import com.naukri.Pages.Naukri_Datacollection_page;
import com.naukri.Utilities.Dataproviderdata;

public class Technologywise_detailstest extends Baseclass {

	Naukri_Datacollection_page naukri_datacollection_page;
	Homepage homepage;

	public Technologywise_detailstest() throws Exception {
		super();
	}

	@BeforeMethod
	public void setup() throws Exception {
		initilization();
		naukri_datacollection_page = new Naukri_Datacollection_page();
		homepage = new Homepage();
		homepage.searchpage();
	}

	@DataProvider
	public Object[][] getdata() throws Exception {
		Object[][] data = Dataproviderdata.getexceldata();
		return data;
	}

	@Test(dataProvider = "getdata")
	public void searchtest(String Technologies, String Location) throws Exception {
		closepopups();
		naukri_datacollection_page.Technologywise_company_details(Technologies, Location);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
