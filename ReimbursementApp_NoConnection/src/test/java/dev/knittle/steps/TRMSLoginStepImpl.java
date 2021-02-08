package dev.knittle.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dev.knittle.pages.TRMSMain;
import dev.knittle.runners.TRMSRunner;

public class TRMSLoginStepImpl {
	
	public static TRMSMain trmsmain = TRMSRunner.trmsmain;
	public static WebDriver driver = TRMSRunner.driver;

	
	@Given("^The Guest is on the TRMS Home Page$")
	public void the_Guest_is_on_the_TRMS_Home_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		driver.get("http://localhost:8080/ReimbursementApp/");
	}

	@When("^The Guest enters valid credentials and clicks login$")
	public void the_Guest_enters_valid_credentials_and_clicks_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		trmsmain.username.sendKeys("Empl13");
		trmsmain.password.sendKeys("emplpass13");
		trmsmain.button.click();
	}

	@Then("^The Guest should be on their Employee Home Page$")
	public void the_Guest_should_be_on_their_Employee_Home_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		try {
			Thread.sleep(2000); //May need delay in order to load the page
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Assert.assertEquals("Employee Menu", driver.getTitle());
	}

}
