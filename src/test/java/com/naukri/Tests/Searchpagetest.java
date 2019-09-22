package com.naukri.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.naukri.Base.Baseclass;
import com.naukri.Pages.Homepage;
import com.naukri.Pages.Naukri_Datacollection_page;
import com.naukri.Utilities.Dataproviderdata;

public class Searchpagetest extends Baseclass{
	
	Homepage homepage;

	public Searchpagetest() throws Exception {
		super();
	}
	@BeforeMethod
	public void setup() throws Exception {
		initilization();
		homepage=new Homepage();
	}

	@Test
	public void searchtest() throws Exception {
		homepage.searchpage();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
