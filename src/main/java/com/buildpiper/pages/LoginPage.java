package com.buildpiper.pages;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.buildpiper.base.BasePage;
import com.buildpiper.report.Log;
import com.buildpiper.utils.Configuration;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.Pause;

/**
 * @author sagarT
 *
 */
public class LoginPage extends BasePage {
	
	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);


	@FindBy(name = "email")
	WebElement username_txt;

	@FindBy(name = "password")
	WebElement password_txt;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement SignIn_btn;

	@FindBy(xpath = "//main//div[contains(text(),'Apps Summary')]")
	WebElement HomePageAppSummary;

	/**
	 * 
	 */
	public LoginPage() {

		ui_launch(config.url());
		ui_getUIDriver().navigate().refresh();
		ui_wait(2);
		ui_waitForPageLoad();

	}

	/**
	 * This method will login into application
	 * 
	 * @param username_val
	 * @param password_val
	 * @return
	 */
	public HomePage login(String username_val, String password_val) {
		

		ui_setvalue(username_txt, "USER_NAME", username_val);
		ui_setvalue(password_txt, "PASSWORD", password_val);
		ui_click(SignIn_btn, "LOGIN_BTN");
		validatePageLoad();
		return PageFactory.initElements(ui_getUIDriver(), HomePage.class);
	}

	/**
	 * This method will login into application
	 * 
	 * @param user
	 * @return
	 */
	public HomePage login(String user) {
		String username = Configuration.getEnvironmentVariable("project.user." + user);
		String password = Configuration.getEnvironmentVariable("project.password." + user);
		return login(username, password);

	}

	/**
	 * This method will validate successful page load
	 * 
	 * @return
	 */
	public LoginPage validatePageLoad() {
		//ui_IsElementDisplay(ui_waitForElementToDisplay(HomePageAppSummary, Pause.MEDIUM));
		Log.info("Successful navigation is validated for " + this.getClass().getSimpleName());
		return this;
	}

}
