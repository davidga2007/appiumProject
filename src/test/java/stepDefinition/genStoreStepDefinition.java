package stepDefinition;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import stepImplementation.stepImpl;



public class genStoreStepDefinition {
	@Steps
	stepImpl impl;
	
	@Given("start Appium server")
	public void start_appium_server() throws IOException {
		impl.setUpAppium();
	}

	@Then("navigate to app and login")
	public void navigate_to_app_and_login() {
		impl.enterAllDataOnmainPage();
	}

	@Then("add items into shopping cart")
	public void add_items_into_shopping_cart() {

	}

	@Then("verify sum of the items in shopping cart")
	public void verify_sum_of_the_items_in_shopping_cart() {

	}

}
