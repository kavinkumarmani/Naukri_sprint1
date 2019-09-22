package com.naukri.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naukri.Base.Baseclass;

public class Homepage extends Baseclass {

	// pagefactory or object repository
	@FindBy(xpath = "//span[contains(text(),' Search Jobs ')]")
	WebElement Searchbar;

	public Homepage() throws Exception {
		PageFactory.initElements(driver, this);
	}

	public void searchpage() {

		Searchbar.click();
	}
}
