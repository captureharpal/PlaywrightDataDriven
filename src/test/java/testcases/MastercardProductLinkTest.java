package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;

import base.BaseTest;

public class MastercardProductLinkTest extends BaseTest {
	

	@Test
	public void ProductLinkMastercardTest() throws InterruptedException {
		
		Browser browser = getBrowser("chrome");
		navigate(browser, getData("LOGIN_URL") );
		
		acceptCookies();
		
		login(getData("LOGIN_USERNAME"), getData("LOGIN_PASSWORD"));
		System.out.println("Page title: " + getPage().title());
		
		openProductsPage();
		
		waitForElement("CATEGORY_ITEMS_CSS");
		
		clickCategoryByName("Data Services");
		
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
	 
	 
	 private void clickCategoryByName(String categoryName) {
		    Locator categoryItems = getPage().locator(getData("CATEGORY_ITEMS_CSS"));
		    int count = categoryItems.count();

		    System.out.println("Total categories found: " + count);

		    int targetIndex = -1;

		    // 1) Print all categories and remember the index of the one we want
		    for (int i = 0; i < count; i++) {
		        String name = categoryItems.nth(i)
		                                   .locator("span.text-ellipsis-21m7A")
		                                   .first()
		                                   .innerText()
		                                   .trim();
		        System.out.println("Category " + i + " -> " + name);

		        if (name.equalsIgnoreCase(categoryName)) {
		            targetIndex = i;
		        }
		    }

		    // 2) After printing all, click the target if found
		    if (targetIndex != -1) {
		        String selectedName = categoryItems.nth(targetIndex)
		                                           .locator("span.text-ellipsis-21m7A")
		                                           .first()
		                                           .innerText()
		                                           .trim();
		        System.out.println("Clicking category: " + selectedName);
		        categoryItems.nth(targetIndex).click();
		    } else {
		        Assert.fail("Category with name '" + categoryName + "' was not found in the left pane.");
		    }
		}
}