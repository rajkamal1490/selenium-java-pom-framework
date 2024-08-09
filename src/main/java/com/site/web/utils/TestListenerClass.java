package com.site.web.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.site.web.base.BaseTest;

public class TestListenerClass extends BaseTest implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		endReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		getTest().log(Status.PASS, MarkupHelper.createLabel("PASSED", ExtentColor.GREEN));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		getTest().log(Status.SKIP, MarkupHelper.createLabel("SKIPPED", ExtentColor.ORANGE));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
			getTest().log(Status.FAIL, MarkupHelper.createLabel("FAILED", ExtentColor.RED));
			getTest().log(Status.FAIL, result.getThrowable());
			getTest().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
