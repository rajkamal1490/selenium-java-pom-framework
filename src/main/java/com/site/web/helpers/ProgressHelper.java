package com.site.web.helpers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProgressHelper {

	private static final Logger logger = LogManager.getLogger(ProgressHelper.class);
	private WebDriver driver;
	private WebDriverWait webDriverWait;
	private long implicitWait;
	
	public ProgressHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitForElementToDisplay(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementsToDisplay(List<WebElement> elements) {
		webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	/**
	 * @param element
	 */
	public void waitForElementToInvisible(WebElement element) {
		webDriverWait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementToInvisible(WebElement element, Duration timeOutInSeconds) {
		WebDriverWait wdWait = new WebDriverWait(driver, timeOutInSeconds);
		wdWait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * @param element
	 */
	public void waitForElementToBeClickable(WebElement element) {
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * @param locator
	 */
	public void waitForPresenceOfElement(By locator) {
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	private WebDriverWait getWait(Duration timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		Duration pollingDuration = Duration.of(pollingEveryInMiliSec, ChronoUnit.MILLIS);
		wait.pollingEvery(pollingDuration);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	@SuppressWarnings("deprecation")
	public void setImplicitWait(long timeout, TimeUnit unit) {
		logger.info("timeout[{}]", timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}
	
	public void waitForElementVisible(By locator, Duration timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.info("locator[{}]", locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
		setImplicitWait(implicitWait, TimeUnit.SECONDS);
	}
	
	public void waitForElementAttributeValueToChanged(WebElement element, String attribute, String attributeValue) {
		logger.info("Wait for Attribute '" + attribute + "' value to change to" + attributeValue);
		webDriverWait.until(ExpectedConditions.attributeContains(element, attribute, attributeValue));
	}
	
	public void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState")
				.equals("complete");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(pageLoadCondition);
	}
	
	public void waitForIframe(By locator, Duration timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.info("locator[{}]", locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		driver.switchTo().defaultContent();
		setImplicitWait(implicitWait, TimeUnit.SECONDS);
	}

	/**
	 * @param locator
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void waitForIframeAndSwitchToIt(By locator, Duration timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.info("locator[{}]", locator);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
}
