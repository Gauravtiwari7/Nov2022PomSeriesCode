package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil=new JavaScriptUtil(driver);
	}
	
	public  WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)){
			jsUtil.flash(element);
		}
		return element;
		
	}
	public  void doSendKeys(By locator,String value) {
		WebElement element=getElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	public  void doClick(By locator) {
		driver.findElement(locator).click();
	}
	public void doActionsCick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	public  boolean doElementisDispalyed (By locator) {
		return getElement(locator).isDisplayed();
	}

	public List<WebElement> getElements(By locator) {
	return driver.findElements(locator);
	}
	public String getElementAttribute(By locator,String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	public void getElementAttributes(By locator,String attrName) {
		List<WebElement> eleList=getElements(locator);
		for(WebElement e:eleList) {
			e.getAttribute(attrName);
			System.out.println(attrName);
		}
	}
	public int getTotalElementsCount(By locator) {
	int eleCount=getElements(locator).size();
	System.out.println("totsal elements for :"+ locator +"--->"+eleCount);
	return eleCount;
	
	}
	public  List<String> getElementsTextList(By locator) {
		List<String> eleTextList=new ArrayList<String>();
		List <WebElement> eleList=getElements(locator);	 
		for(WebElement e: eleList) {
			String text=e.getText();
			eleTextList.add(text);
		}
		return eleTextList;

		}
	//***********************Select based dropdown utils*******************************//
	public  void doSelectDropdownByIndex(By locator,int index) {
	Select select=new Select(getElement(locator));
	select.selectByIndex(index);
	}
	public  void doSelectDropdownByValue(By locator,String value) {
		Select select=new Select(getElement(locator));
		select.selectByValue(value);
		}
	public  void doSelectDropdownByVisibleText(By locator,String text) {
		Select select=new Select(getElement(locator));
		select.selectByVisibleText(text);
		}
	public  List<WebElement> getDropDownOptionsList(By locator) {
		Select select =new Select(getElement(locator));
		return select.getOptions();
	}
	public  List<String> getDropDownOptionsTextList(By locator) {
		List<WebElement>optionsList=getDropDownOptionsList(locator);
		List<String> optionsTextList=new ArrayList<String>();
		for(WebElement e: optionsList) {
			String text=e.getText();
			optionsTextList.add(text);
			
		}
		return optionsTextList;
		
	}
	public  int getTotalDropDownOptions(By locator) {
		
		int optionsCount= getDropDownOptionsList(locator).size();
		System.out.println("total options --->"+optionsCount);
		return optionsCount;
		
	}
	public  void selectDropDownValue(By locator,String expValue) {
		List<WebElement>optionsList=getDropDownOptionsList(locator);
		for(WebElement e: optionsList) {
			String text=e.getText();
			System.out.println(text);
			if(text.equals(expValue)) {
				e.click();
				break;
			}
		}
	}
	public  void  selectDropDownwithoutSelect(By locator,String option) {
		List<WebElement>dropdownList=getElements(locator);
		for(WebElement e:dropdownList) {
			String text=e.getText();
			System.out.println(text);
			if(text.equals(option)) {
				e.click();
				break;
			}
		}
		
	}
	public  void doSearch(By suggListLocator,String suggName) {
		 List<WebElement> suggeList=getElements(suggListLocator);
	        for(WebElement e:suggeList) {
	        	String text=e.getText();
	        	if(text.length()>0) {
		        	System.out.println(text);
	        	}
	        	if(text.contains(suggName)) {
	        		e.click();
	        		break;
	        	}
	        	
	        }
	}
	// ************************Wait Utils **********************//
		/**
		 * An expectation for checking that an element is present on the DOM of a page.
		 * This does not necessarily mean that the element is visible.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

		/**
		 * An expectation for checking that all elements present on the web page that
		 * match the locator are visible. Visibility means that the elements are not
		 * only displayed but also have a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}

		/**
		 * An expectation for checking that there is at least one element present on a
		 * web page.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}

		/**
		 * 
		 * @param timeOut
		 * @return
		 */
		public Alert waitForAlertPresence(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());
		}

		public String getAlertText(int timeOut) {
			return waitForAlertPresence(timeOut).getText();
		}

		public void acceptAlert(int timeOut) {
			waitForAlertPresence(timeOut).accept();
		}

		public void dismissAlert(int timeOut) {
			waitForAlertPresence(timeOut).dismiss();
		}

		public void alertSendKeys(int timeOut, String value) {
			waitForAlertPresence(timeOut).sendKeys(value);
		}

		public String waitForTitleContainsAndFetch(int timeOut, String titleFractionValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.titleContains(titleFractionValue));
			return driver.getTitle();
		}

		public String waitForTitleIsAndFetch(int timeOut, String titleValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.titleIs(titleValue));
			return driver.getTitle();
		}

		public String waitForURLContainsAndFetch(int timeOut, String urlFractionValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.urlContains(urlFractionValue));
			return driver.getCurrentUrl();
		}

		public String waitForURLIsAndFetch(int timeOut, String urlValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.urlToBe(urlValue));
			return driver.getCurrentUrl();
		}

		public boolean waitForURLContains(int timeOut, String urlFractionValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.urlContains(urlFractionValue));

		}

		public void waitForFrameAndSwitchToItByIDOrName(int timeOut, String idOrName) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
		}

		public void waitForFrameAndSwitchToItByIndex(int timeOut, int frameIndex) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}

		public void waitForFrameAndSwitchToItByFrameElement(int timeOut, WebElement frameElement) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		}

		public void waitForFrameAndSwitchToItByFrameLoctor(int timeOut, By frameLocator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}


}
