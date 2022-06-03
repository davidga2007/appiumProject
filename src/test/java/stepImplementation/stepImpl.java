package stepImplementation;

import java.io.IOException;

import generalStorePages.GeneralStoreLandingPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import util.systemProperty;

public class stepImpl extends systemProperty{
	
	@Steps
	systemProperty sys;
	
	@Steps
	GeneralStoreLandingPage generalStoreLandingPage;

	@Step
	public void setUpAppium() throws IOException{
		AndroidDriver<AndroidElement> driver = capability();
//		systemProperty.capability();
	}
	
	public void enterAllDataOnmainPage() {
		
		//generalStoreLandingPage.isGeneralStorePageHeaderVisible();
		generalStoreLandingPage.selectCountryFronDropDown();
		generalStoreLandingPage.enterName();
		generalStoreLandingPage.selectGender();
		generalStoreLandingPage.clickOnLetsShopButton();
		
		
		
	}
	
	
	
	
	
	
	
	
}


