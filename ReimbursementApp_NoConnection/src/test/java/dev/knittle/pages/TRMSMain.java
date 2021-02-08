package dev.knittle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TRMSMain {
	public WebDriver driver;
	
	@FindBy(id = "username")
	public WebElement username;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(id = "loginButton")
	public WebElement button;
	
	public TRMSMain(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
