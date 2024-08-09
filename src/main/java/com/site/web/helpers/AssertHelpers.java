package com.site.web.helpers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertHelpers {

private static final Logger logger = LogManager.getLogger(AssertHelpers.class);
	
	public void assertElementText(WebElement element, String expText) {
		String errorMsg = "Element text is not expected";
		logger.info("Expected text : {}", expText);
		assertEquals(element.getText(), expText, errorMsg);
	}
	
	/* Verify the string is in alpha numeric format */
	public void assertStringIsAlphaNumeric(String string) {
		assertTrue(string.matches("[A-Za-z0-9]+"), "String[" + string + "] is not in alpha-numeric format");
	}

	/* Verify the actuals string contains expected string */
	public void assertActualContainsExpected(String actualValue, String expectedValue) {
		assertTrue(actualValue.contains(expectedValue), null);
	}

	public void assertTrue(boolean match) {
		Assert.assertTrue(match);
	}

	public void assertTrue(boolean match, String errorMsg) {
		Assert.assertTrue(match, errorMsg);
	}

	public void assertEquals(Object[] actualVal, Object[] expectedVal) {
		String errorMsg = "Expected and actual arrays are not matching";
		Assert.assertEquals(actualVal, expectedVal, errorMsg);
		logger.info("Actual array: {}", actualVal);
		logger.info("Expected array: {}", expectedVal);
	}

	public void assertEquals(Object actualVal, Object expectedVal) {
		String errorMsg = "Expected and actual objects are not matching";
		assertEquals(actualVal, expectedVal, errorMsg);
	}

	public void assertEquals(String actualVal, String expectedVal) {
		String errorMsg = "Expected[" + expectedVal + "] and actual[" + actualVal + "] strings are not matching";
		assertEquals(actualVal, expectedVal, errorMsg);
	}

	public void assertEquals(Boolean actualVal, Boolean expectedVal) {
		String errorMsg = "Expected[" + expectedVal + "] and actual[" + actualVal + "] booleans are not matching";
		Assert.assertEquals(actualVal, expectedVal, errorMsg);
	}

	public void assertActualExpected(List<String> actual, List<String> expected) {
		String errorMsg = "Expected[" + expected + "] and actual[" + actual + "] lists are not matching";
		Assert.assertEquals(actual, expected, errorMsg);
	}

	public void assertEquals(String actualVal, String expectedVal, String errorMsg) {
		Assert.assertEquals(actualVal, expectedVal, errorMsg);
	}

	public void assertEquals(Object actualVal, Object expectedVal, String errorMsg) {
		Assert.assertEquals(actualVal, expectedVal, errorMsg);
		logger.info("Actual object: {}", actualVal);
		logger.info("Expected object: {}", expectedVal);
	}

	public void assertEquals(int actualVal, int expectedVal, String errorMsg) {
		Assert.assertEquals(actualVal, expectedVal, errorMsg);
	}
}
