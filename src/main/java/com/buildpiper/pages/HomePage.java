package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Pause;

public class HomePage extends BasePage {
	
	@FindBy(xpath = "//button[@type='button' and @aria-controls='menu-appbar']//div//div")
     WebElement userMenuAppBar;
	
	@FindBy(xpath = "//li[@role='menuitem']//span[text()='Job Executors']")
    WebElement jobExecutionhistory;
	
	@FindBy(xpath = "//div[@class='heading-section-service']//div[1]")
    WebElement jobExecutionhistoryMainHeading;
	
	@FindBy(xpath = "//div[@class='heading-section-service']//div[2]")
    WebElement jobExecutionhistorySubHeading;
	
	@FindBy(xpath = "//table[@class='table table-responsive']//span")
    List<WebElement> jobExecutionStatusContainer;
	
	@FindBy(xpath = "//div//p[text()='Build Version: ']")
    WebElement version;
		
	public HomePage() {

	}

	public HomePage HealthQueue() {

			ui_click(userMenuAppBar, "userMenuAppBar");
			String versionNum = version.getText().trim();
			ui_click(jobExecutionhistory, "clicks job execution history");
			System.out.println(versionNum);
			SoftAssertion.assertEquals(jobExecutionhistoryMainHeading.getText().trim(), "Health Status Observation(Celery Queue)", "Validate Main Heading");
			SoftAssertion.assertEquals(jobExecutionhistorySubHeading.getText().trim(), "Displaying overall summary of the Celery", "Validate Sub Heading");
			for(int i = 0; i < jobExecutionStatusContainer.size(); i++) {
				Assert.assertEquals(jobExecutionStatusContainer.get(i).getText().trim(), "Online", "Validate Status Of Jobs");
			}
			
			return this;	

    }
	
	@FindBy(xpath = "//table[@class='table table-responsive']//tr")
	List<WebElement> workerRowContainer;
	
	public HomePage negativeHealthQueue(String adminDeployApiWorker, String adminPublicApiWorker,
			String bpBuildWorker, String bpDeployWorker, String deployApiWorker, String publicApiWorker,
			String schedulerWorker, String versioningWorker) {

		ui_click(userMenuAppBar, "userMenuAppBar");
		String versionNum = version.getText().trim();
		ui_click(jobExecutionhistory, "clicks job execution history");
		System.out.println(versionNum);
		SoftAssertion.assertEquals(jobExecutionhistoryMainHeading.getText().trim(), "Health Status Observation(Celery Queue)", "Validate Main Heading");
		SoftAssertion.assertEquals(jobExecutionhistorySubHeading.getText().trim(), "Displaying overall summary of the Celery", "Validate Sub Heading");
        int i = 0;
		if(jobExecutionStatusContainer.get(i).getText().trim()=="Offline") {
    		boolean queueStatus = true;
    		for (WebElement queueContainer : workerRowContainer) {
    			String workerText = queueContainer.getText();
    			System.out.println(workerText);
    			if (!(workerText.contains(adminDeployApiWorker) || workerText.contains(adminPublicApiWorker)
    					|| workerText.contains(bpBuildWorker) || workerText.contains(bpDeployWorker)
    					|| workerText.contains(deployApiWorker) || workerText.contains(publicApiWorker)
    					|| workerText.contains(schedulerWorker) || workerText.contains(versioningWorker))) {
    				queueStatus = false;
    				break;
    			}
    		}
    		Assert.assertTrue(queueStatus, "Unable to  validate the Worker queue container Table");
        }
		
		return this;	

}
	
	@FindBy(xpath = "//div[not(contains(@class,'jss'))]/ul/a[@class='subOptionChild']//span[@title='Service Overview']")
	WebElement serviceOverViewTab;

	@FindBy(xpath = "//li//button[contains(@class,'main-nav-1')]//span[@title]")
	List<WebElement> poc_qaProjectLink;
	
	@FindBy(xpath = "//input[@placeholder='Name' and @name='name']")
	WebElement servicesSearchBox;
	
	public HomePage SearchService(String appName, String serviceName) {
		
		boolean projectSelection = false;
		ui_IsElementDisplay(ui_waitForElementToDisplay(poc_qaProjectLink.get(0), Pause.MEDIUM));
		for(WebElement element:poc_qaProjectLink) {
			if(element.getText().trim().equalsIgnoreCase(appName)) {
				element.click();
				projectSelection = true;
				break;
			}
		}
		if(projectSelection) {
			ui_click(serviceOverViewTab, " serviceOverviewLink");
			ui_clearAndSetValue(servicesSearchBox, serviceName);
			servicesSearchBox.sendKeys(Keys.ENTER);

		}
		
		return this;	

       }
}
