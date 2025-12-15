package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import base.BaseTest;

public class DeveloperMastercardTest extends BaseTest {
	

	@Test
	public void loginMastercardTest() throws InterruptedException {
		
		Browser browser = getBrowser("chrome");
		navigate(browser, getData("LOGIN_URL") );
		
		acceptCookies();
		login(getData("LOGIN_USERNAME"), getData("LOGIN_PASSWORD"));
		System.out.println("Page title: " + getPage().title());
		
		openProductsPage();

		
		String expectedProduct= getData("SEARCH_PRODUCT_NAME");
		type("SEARCH_XPATH", expectedProduct);
		
		boolean found = isElementPresent("SEARCH_RESULT_PRODUCT_XPATH");
		Assert.assertTrue(found, "Expected product was not found in search results");
		
}
	
	private void acceptCookies() {
		click("ACCEPT_Cookie_CSS");
	}
	
	private void login(String username, String password) {
		type("Mastercard_Username_XPATH",  getData("LOGIN_USERNAME"));
		type("Mastercard_Password_XPATH",  getData("LOGIN_PASSWORD"));
		click("Mastercard_Login_XPATH");
	}
	
	 private void openProductsPage() {
	        hover("PRODUCTS_XPATH");
	        click("VIEW_PRODUCTS_XPATH");
	    }
	 
	 private void searchProduct(String productName) {
	        type("SEARCH_XPATH", productName);
	    }
}