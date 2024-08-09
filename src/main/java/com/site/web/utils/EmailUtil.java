package com.site.web.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class EmailUtil {

	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation Test Results");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", System.getProperty("BROWSER"));

		return extent;
	}

	public static void sendEmail(String reportFilePath, String configFile) {
//		Properties properties = new Properties();

//		try (FileInputStream input = new FileInputStream(configFile)) {
//			properties.load(input);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		String host = "smtp.gmail.com";
		String port = "587";
		final String username = "rajkamal@riverstonetech.com";
		final String password = "qrgr quva ilis ecou";
		String from = "rajkamal@riverstonetech.com";
		String to = "rajkamal@riverstonetech.com";
		String cc = "";
		String subject = "Extent Report - Test Execution";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			message.setSubject(subject);

			String content = "Hi,<br><br>Please find the attached report for the test execution.<br><br>Best Regards,<br>Your Automation Team";
			message.setContent(content, "text/html");

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(content, "text/html");

			Multipart multipart = new javax.mail.internet.MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			MimeBodyPart attachPart = new MimeBodyPart();
			attachPart.attachFile(new File(reportFilePath));
			multipart.addBodyPart(attachPart);

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Email sent successfully.");

		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	}

}
