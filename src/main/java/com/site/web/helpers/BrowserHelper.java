package com.site.web.helpers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrowserHelper {

	private static final Logger logger = LogManager.getLogger(BrowserHelper.class);
	private WebDriver driver;
	
	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void goBack() {
		driver.navigate().back();
	}

	public void goForward() {
		driver.navigate().forward();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		logger.info("get current window name");
		return driver.getWindowHandle();
	}

	public void switchToWindow(int index) {
		logger.info("index[{}]", index);
		List<String> windowsId = new LinkedList<String>(getWindowHandles());

		if (index < 0 || index > windowsId.size())
			throw new IllegalArgumentException("Invalid Index : " + index);

		driver.switchTo().window(windowsId.get(index));
	}

	public void switchToSpecificWindow(String windowId) {
		driver.switchTo().window(windowId);
		logger.info("Switched to window[{}]", windowId.toString());
	}

	public void switchToParentWindow() {
		List<String> windowsId = new LinkedList<String>(getWindowHandles());
		driver.switchTo().window(windowsId.get(0));
	}

	public void switchToParentWithChildClose() {
		switchToParentWindow();

		List<String> windowsIds = new LinkedList<String>(getWindowHandles());
		for (int i = 1; i < windowsIds.size(); i++) {
			String windowsId = windowsIds.get(i);
			logger.info(windowsId);
			driver.switchTo().window(windowsId);
			driver.close();
		}

		switchToParentWindow();
	}

	/** Switch to IFrame Helpers **/
	
	public void switchToFrame(By locator) {
		logger.info("locator[{}]", locator);
		driver.switchTo().frame(driver.findElement(locator));
	}

	public void switchToFrame(String nameOrId) {
		logger.info("nameOrId[{}]", nameOrId);
		driver.switchTo().frame(nameOrId);
	}

	public void switchToFrame(int id) {
		logger.info("id[{}]", id);
		driver.switchTo().frame(id);
	}

	public String getCurrentURL() {
		String currentUrl = driver.getCurrentUrl();
		logger.info("Current URL is {}", currentUrl);
		return currentUrl;
	}

	public String getCurrentPageTitle() {
		String title = driver.getTitle();
		logger.info("Current Title is '{}'" + title);
		return title;
	}
	
	/**
	 * Method to navigate to a particular URL
	 *
	 * @param url
	 */
	public void navigateToURL(String url) {
		logger.info("Navigating to URL[{}]", url);
		driver.navigate().to(url);
	}

	/**
	 * Method to switch to default content for iframe or windows
	 */
	public void switchToDefaultContent() {
		logger.info("Switching to default content");
		driver.switchTo().defaultContent();
	}
}
