package com.qa.opencart.tests;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.SearchPage;

public class ProductPageInfoTest extends BaseTest {
	
	
	@BeforeClass
	public void productInfoPageSetup() {
	accpage=loginpage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung","Samsung SyncMaster 941BW",1},
		};
	}
	
	@Test (dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey,String productName,int imagesCount) {
	searchpage =	accpage.performSearch(searchKey);
    productInfoPage =	searchpage.selectProduct(productName);
    int actImagesCount = productInfoPage.getProductImagesCount();
    Assert.assertEquals(actImagesCount, imagesCount);
	}
	
    @Test
	public void productinfoTest() {
		searchpage = accpage.performSearch("MacBook");
	    productInfoPage =	searchpage.selectProduct("MacBook Pro");
	  Map<String,String> actProductinfoMap=  productInfoPage.getProductInfo();
	  softAssert.assertEquals(actProductinfoMap.get("Brand"), "Apple");
	  softAssert.assertEquals(actProductinfoMap.get("Product Code"), "Product 18");
	  softAssert.assertEquals(actProductinfoMap.get("productname"), "MacBook Pro");
	  softAssert.assertEquals(actProductinfoMap.get("product price"), "$2,000.00");
	  softAssert.assertAll();
 }
    @Test
    public void addToCartTest() {
    	searchpage = accpage.performSearch("MacBook");
	    productInfoPage =	searchpage.selectProduct("MacBook Pro");
	    productInfoPage.enterQuantity(2);
	   String actCartMessg= productInfoPage.addProductToCart();
	   softAssert.assertTrue(actCartMessg.indexOf("Success")>=0);
	   softAssert.assertTrue(actCartMessg.indexOf("MacBook Pro")>=0);
	   softAssert.assertEquals(actCartMessg, "Success: You have added MacBook Pro to your shopping cart!");
	   softAssert.assertAll();
    }
}
