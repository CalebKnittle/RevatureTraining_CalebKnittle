package dev.knittle.runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dev.knittle.pages.TRMSMain;

@RunWith(Cucumber.class) //Allows connection to JUnit w/Cucumber in this class
@CucumberOptions(features = "src/test/resources", glue = "dev.knittle.steps")
public class TRMSRunner {

	public static WebDriver driver;
	public static TRMSMain trmsmain;
	
	//Run before all tests and start up driver
	@BeforeClass
	public static void setup() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
		trmsmain = new TRMSMain(driver);
	}
	
	//Run after all tests to quit out of driver
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
