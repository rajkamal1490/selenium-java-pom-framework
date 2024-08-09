package com.site.web.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.site.web.utils.PropertyReader;

public class BaseTest {

	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	public static WebDriver driver;
	public static String browser = System.getProperty("BROWSER");

	// private static final String REPORT_FILE_PATH = System.getProperty("user.dir")
	// + "\\reports\\extentSparkReport.html";
	// private static final String CONFIG_FILE_PATH = System.getProperty("user.dir")
	// + "\\src\\test\\resources\\extent-config.xml";

	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	public String captureScreenshot(String methodName) {

		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "\\screenshots\\" + methodName + timestamp + ".png";
		File finalDestination = new File(destination);

		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to save screenshot: " + e.getMessage());
		}

		return destination;
	}

	@BeforeSuite
	public void initializeReport() {
		sparkReporter = new ExtentSparkReporter("reports/extentSparkReport.html");
		sparkReporter.config().setDocumentTitle("Selenium Web Testing");
		sparkReporter.config().setReportName("LUMA Web Automation Report ");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");
		extent = new ExtentReports();
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", browser);
		extent.attachReporter(sparkReporter);
		// extent = EmailUtil.createInstance(REPORT_FILE_PATH);
	}

	@BeforeMethod(alwaysRun = true)
	public void startDriver() {
		String configFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\test_data\\config.properties";
		String url = PropertyReader.getPropValue(configFilePath, "url");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		logger.info("url[{}]", url);
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().window().maximize();
	}

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) (Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (Thread.currentThread().getId()), test);
		return test;
	}

	public void endReport() {
		// EmailUtil.sendEmail(REPORT_FILE_PATH, CONFIG_FILE_PATH);
		extent.flush();
	}

	@AfterMethod
	public void endDriver() {
		driver.quit();
	}

}
