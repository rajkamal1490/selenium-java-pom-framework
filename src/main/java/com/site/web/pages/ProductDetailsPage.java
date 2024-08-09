package com.site.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductDetailsPage extends BasePage{
	
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[@class='base']")
	private WebElement productTitle;
	
	@FindBy(xpath = "//span[@class='price']")
	private WebElement price;
	
	@FindBy(xpath = "//div[@class='swatch-attribute-options clearfix']//div[@option-label='XS']")
	private WebElement size;
	
	@FindBy(xpath = "//button[@id='product-addtocart-button']")
	private WebElement addToCartBtn;
	
	String sizeXpath =  "//div[@class='swatch-attribute-options clearfix']//div[@option-label='$SIZE']";
	String colorXpath = "//div[@class='swatch-option color' and @option-label='$COLOR']";
	
	public void verifyProductTitle(String productName) {
		//progressHelper.waitForElementToDisplay(productTitle);
		progressHelper.waitForPageLoad();
		assertHelpers.assertElementText(productTitle, productName);
	}
	
	public void selectProductSize(String size) {
	    String productSizeXpath = webActionHelper.dynamicXpathGenerator(sizeXpath, "$SIZE", size);
	    By productSizeElement = By.xpath(productSizeXpath);
		webActionHelper.click(productSizeElement);
	}
	
	public void selectProductColor(String color) {
		String productColorXpath = webActionHelper.dynamicXpathGenerator(colorXpath, "$COLOR", color);
	    By productColorElement = By.xpath(productColorXpath);
		webActionHelper.click(productColorElement);
	}
	
	public void addToCart() {
		webActionHelper.click(addToCartBtn);
	}

}
