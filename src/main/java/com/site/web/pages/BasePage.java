package com.site.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.site.web.helpers.ProgressHelper;
import com.site.web.helpers.AssertHelpers;
import com.site.web.helpers.BrowserHelper;
import com.site.web.helpers.WebActionHelper;

public class BasePage {
	
	protected WebDriver driver;
	static WebActionHelper webActionHelper;
	static BrowserHelper browserHelper;
	static ProgressHelper progressHelper;
	static AssertHelpers assertHelpers;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webActionHelper = new WebActionHelper(driver);
		browserHelper = new BrowserHelper(driver);
		progressHelper = new ProgressHelper(driver);
		assertHelpers = new AssertHelpers();
	}

}
