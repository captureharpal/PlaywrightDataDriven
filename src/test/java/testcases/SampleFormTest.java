package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import base.BaseTest;

public class SampleFormTest extends BaseTest{
	
	@Test
	public void sampleFormRegistration () {
		
		Browser browser = getBrowser("chrome");
		navigate(browser, "https://www.way2automation.com/angularjs-protractor/banking/#/login");
		click("sampleform_CSS");
		System.out.println(isElementPresent("sampleform_CSS"));
		//Assert.assertTrue(isElementPresent("sampleform_CSS"), "Sample form navigated to Registration page");
		//Thread.sleep(3000);
		type("regFirstName_CSS", "testfirstname");
		type("regLastName_CSS", "testlastname");
		type("regEmail_CSS", "test@way2automation.com");
		type("regPassword_CSS", "testpwd");
		
	}

}
