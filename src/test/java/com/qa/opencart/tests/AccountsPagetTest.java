package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPagetTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
	accpage=loginpage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle=accpage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	@Test
	public void accPageURLTest() {
		String actURL=accpage.getAccPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accpage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageHeadersTest() {
	List<String> actualAccPageHeaderList=accpage.getAccountsPageHeadersList();
	System.out.println("Accounts Page Header List :"+actualAccPageHeaderList);
	Assert.assertEquals(actualAccPageHeaderList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	@Test
	public void accPageHeadersValueTest() {
	List<String> actualAccPageHeaderList=accpage.getAccountsPageHeadersList();
	System.out.println("Actual Accounts Page Header List :"+actualAccPageHeaderList);
	System.out.println("Expected Accounts Page  Header List :"+AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	Assert.assertEquals(actualAccPageHeaderList,AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"},
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searctProductCountTest(String serachkey) {
	searchpage=	accpage.performSearch(serachkey);
  Assert.assertTrue(searchpage.getSearchProductsCount()>0);
		
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"},

		};
	}
	
	@Test (dataProvider = "getProductTestData")
	public void searctProductTest(String searchkey,String productName) {
	searchpage=	accpage.performSearch(searchkey);
     if(searchpage.getSearchProductsCount()>0) {
    	productInfoPage= searchpage.selectProduct(productName);
    	String actualProductHeader=productInfoPage.getProductHeaderValue();
    	Assert.assertEquals(actualProductHeader, productName);
     }

}
	
	
	
	
}
