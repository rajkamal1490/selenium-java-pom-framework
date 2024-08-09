package com.site.web.pages;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.site.web.utils.JsonReader;


public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "firstname")
	private WebElement firstnameField;
	
	@FindBy(id = "lastname")
	private WebElement lastnameField;
	
	@FindBy(id = "email_address")
	private WebElement emailField;
	
	@FindBy(id = "password")
	private WebElement passwordField;
	
	@FindBy(id = "password-confirmation")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath = "//a[text()='Create an Account']")
	private WebElement createAccountLink;
	
	@FindBy(xpath = "//div[@class='blocks-promo']//span[contains(text(), 'Shop New Yoga')]")
	private WebElement shopNewYogaBtn;
	
	@FindBy(xpath = "//h1[@id='page-title-heading']//span")
	private WebElement searchPageTitle;
	
	String menuXpath = "//span[.='$MENU']";
	String productDetailsXpath = "//div[@class='block widget block-products-list grid']//a[@title='$PRODUCTNAME']";
	
	@SuppressWarnings("unchecked")
	public void createNewAccount() throws InterruptedException {
		String credentialsFilePath = ".\\src\\test\\resources\\test_data\\credentials.json";
		String password = "Raj@12";
		Object firstName, email = null;
		Object lastName = null;
		Map<String, Object> credentialsMap = null;
		try {
			credentialsMap = (Map<String, Object>) JsonReader.readJsonFileAsMap(credentialsFilePath).get("user_details");
			
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		firstName = credentialsMap.get("firstname");
		lastName = credentialsMap.get("lastname");
		email = credentialsMap.get("email");
		
		webActionHelper.click(createAccountLink);
		progressHelper.waitForPageLoad();
		webActionHelper.enterTextInTextField(firstnameField, String.valueOf(firstName));
		webActionHelper.enterTextInTextField(lastnameField, String.valueOf(lastName));
		webActionHelper.enterTextInTextField(emailField, String.valueOf(email));
		webActionHelper.enterTextInTextField(passwordField, password);
		webActionHelper.enterTextInTextField(confirmPasswordField, password);
				
	}
	
	public void goToProductDetailsPage(String productName) {
		String productXpath = webActionHelper.dynamicXpathGenerator(productDetailsXpath, "$PRODUCTNAME", productName);
		By productDetails = By.xpath(productXpath);
		webActionHelper.click(productDetails);
		progressHelper.waitForPageLoad();
	}
	
	public void clickOnShopNewYogaPromotionButton() {
		webActionHelper.click(shopNewYogaBtn);
		progressHelper.waitForPageLoad();
		assertHelpers.assertElementText(searchPageTitle, "New Luma Yoga Collection");
	}
	
	public void selectMenu(String menuName, String subMenu1Name, String subMenu2Name) {
		String mainMenuXpath = webActionHelper.dynamicXpathGenerator(menuXpath, "$MENU", menuName);
		WebElement menuElement = driver.findElement(By.xpath(mainMenuXpath));
		webActionHelper.moveToElement(menuElement);
		
		String subMenu1Xpath = webActionHelper.dynamicXpathGenerator(menuXpath, "$MENU", subMenu1Name);
		WebElement subMenu1Element = driver.findElement(By.xpath(subMenu1Xpath));
		webActionHelper.moveToElement(subMenu1Element);
		
		String subMenu2Xpath = webActionHelper.dynamicXpathGenerator(menuXpath, "$MENU", subMenu2Name);
		WebElement subMenu2Element = driver.findElement(By.xpath(subMenu2Xpath));
		webActionHelper.moveToElement(subMenu2Element);
		webActionHelper.click(subMenu2Element);
	}
}
