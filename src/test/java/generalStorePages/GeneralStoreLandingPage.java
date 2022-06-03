package generalStorePages;


import org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.PogoCachedMethodSiteNoUnwrapNoCoerce;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import util.CommonLibrary;



@Slf4j
public class GeneralStoreLandingPage extends PageObject{
	
	CommonLibrary commonLibrary;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElementFacade generalStorePageHeader;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElementFacade selectCountryDropDown;
	
	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\\\"Austria\\\").instance(0))\")")
	private WebElementFacade scrollToCountry;
	
	@AndroidFindBy(xpath="//*[@text='Austria']")
	private WebElementFacade country;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElementFacade nameField;
	
	@AndroidFindBy(xpath = "//*[@text='Male']")
	private WebElementFacade genderRadiobutton;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/btnLetsShop")
	private WebElementFacade letsShopBtn;
	
	
	@Step
	public boolean isGeneralStorePageHeaderVisible() {
		log.info("LOG INFO: Verify header is displyed");
		try{
			//waitABit(10000);
			waitFor(ExpectedConditions.visibilityOf(generalStorePageHeader));  
			commonLibrary.assertthatValidation(generalStorePageHeader, generalStorePageHeader.getText());
			
		}
		catch(Exception e) {
			log.info("LOG INFO: Header not verified");
		}
		return generalStorePageHeader.isVisible();
	}
	
	
	@Step
	public void selectCountryFronDropDown() {
		element(selectCountryDropDown).waitUntilClickable().click();		
		getDriver().findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"Austria\").instance(0))"));
		element(country).waitUntilPresent().click();	
	}
	
	
	@Step
	public void enterName() {
		waitFor(ExpectedConditions.visibilityOf(nameField));
		element(nameField).sendKeys("Test");
	}
	
	
	@Step
	public void selectGender() {
		element(genderRadiobutton).click();
	}
	
	
	@Step
	public void clickOnLetsShopButton() {
		waitFor(ExpectedConditions.visibilityOf(letsShopBtn));
		element(letsShopBtn).click();
	}

}
