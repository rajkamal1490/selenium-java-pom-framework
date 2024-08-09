package com.site.web.helpers;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActionHelper {
	private static final Logger logger = LogManager.getLogger(WebActionHelper.class);
	
	private WebDriver driver;
	private WebDriverWait webDriverWait;
	
	public WebActionHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public String dynamicXpathGenerator(String xpathToChange, String textToReplace, String actualText) {
		String xpath = xpathToChange.replace(textToReplace, actualText);
		return xpath;
	}
	
	/** Button Helpers  **/
	public boolean click(WebElement element) {
		element.click();
		return true;
	}
	
	public void click(By locator) {
		logger.info("locator[{}]", locator);
		driver.findElement(locator).click();
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public boolean VerifyObjectExists(WebElement element) {
        if (element.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    } 
	
	/** Link Helpers **/
	
	public void clickLink(String data) {
        driver.findElement(By.linkText(data)).click();
    }
	
	public void clickPartialLink(String partialLinkText) {
		driver.findElement(By.partialLinkText(partialLinkText)).click();
	}
	
	public void waitForElementToDisplay(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public String getObjectText(WebElement element) {
		waitForElementToDisplay(element);
        return element.getText();
    }
	
	/** TextField Helpers **/
	
	public void enterTextInTextField(WebElement element, String text) {
		clearTextField(element);
		element.sendKeys(text);
	}
	
	public void clearTextField(WebElement element) {
		element.click();
		element.clear();
	}
	
	public void enterTextInTextFieldCharWise(WebElement element, String value) {
		element.clear();
		for (int i = 0; i < value.length(); i++) {
			char charValue = value.charAt(i);
			element.sendKeys("" + charValue);
		}
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void uploadFile(WebElement element, String filepath) {
		File file = new File(filepath);
		element.sendKeys(file.getAbsolutePath());
	}

	public void moveToElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		actions.build();
	}
	
	public String getAttributeValue(WebElement element, String attribute) {
		String value = element.getAttribute(attribute);
		return value;
	}
	
	//** Check box and Radio button Helpers **//
	
	public boolean isIselected(WebElement element) {
		boolean isSelected = element.isSelected();
		logger.info("isSelected[{}]", isSelected);
		return isSelected;
	}
	
	public void selectCheckBox(WebElement element) {
		logger.info("element[{}]", element);
		if (!isIselected(element))
			element.click();
	}
	
	public void unSelectCheckBox(WebElement element) {
		logger.info("element[{}]", element);
		if (isIselected(element))
			element.click();
	}
}
