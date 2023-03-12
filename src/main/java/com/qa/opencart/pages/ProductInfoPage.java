package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeader=By.tagName("h1");
	private By productImages =By.cssSelector("ul.thumbnails img");
	private By productMetaData =By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData =By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private Map<String,String> productinfoMap;
	private By quantity =By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	private By cartSucessMessg=By.cssSelector("div.alert.alert");

	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
 public String getProductHeaderValue() {
	String productHeaderVal= eleUtil.doElementGetText(productHeader);
	System.out.println("product header : "+productHeaderVal);
	return productHeaderVal;
 }
 
 public void enterQuantity(int qty) {
	 System.out.println("product quantity :" + qty);
	 eleUtil.doSendKeys(quantity,String.valueOf(qty));
 }
 
 public String addProductToCart() {
	 eleUtil.doClick(addToCartBtn);
	String sucessMessg= eleUtil.waitForElementVisible(cartSucessMessg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
	StringBuilder sb=new StringBuilder(sucessMessg);
	String mesg=sb.substring(0, sucessMessg.length()-1).replace("\n", "").toString();
	System.out.println("Cart Sucess Message :"+mesg);
	return mesg;
 }
 
 
 public int getProductImagesCount() {
	int imagesCount = eleUtil.waitForElementsPresence(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	System.out.println("product images count: "+imagesCount);
	return imagesCount;
 }
 public Map<String, String> getProductInfo() {
	 //productinfoMap= new HashMap<String,String>();
	  productinfoMap= new LinkedHashMap<String,String>();
	 //productinfoMap= new TreeMap<String,String>();


	 //header:
	 productinfoMap.put("productname", getProductHeaderValue());
	 getProductMetaData();
	 getProductPriceData();
	  System.out.println(productinfoMap);

	 return productinfoMap;
  
 }
 //fetching the meta data :
 private void getProductMetaData() {
	 //meta data
	 List<WebElement> metaList= eleUtil.getElements(productMetaData);
	 for(WebElement e:metaList) {
	String meta= e.getText();
	String metaInfo[]=	meta.split(":");
	String key=metaInfo[0].trim();
	String value=metaInfo[1].trim();
	productinfoMap.put(key, value);
	 }
 }
 //fetching price data :
 private void getProductPriceData() {
	//price data 
		 List<WebElement> priceList= eleUtil.getElements(productPriceData);
		String price= priceList.get(0).getText();
		String exTax=priceList.get(1).getText();
	     String exTaxVal=	exTax.split(":")[1].trim();
	    productinfoMap.put("product price", price);
	    productinfoMap.put("exTax", exTaxVal);

 }
}
