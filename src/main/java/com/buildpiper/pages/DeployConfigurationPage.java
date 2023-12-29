package com.buildpiper.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.buildpiper.base.BasePage;
import com.buildpiper.utils.Pause;

public class DeployConfigurationPage extends BasePage {
	
//	@FindBy(xpath = "//*[text()='Deploy Details']")
//	WebElement validateDeployDetails;
	
	@FindBy(xpath = "//*[text()='Env Deploy Details']")
	WebElement validateDeployDetails;

	@FindBy(xpath = "//button[@class='btn btn-submit']")
	WebElement buildPiperUIDeployConfigBtn;
	
	@FindBy(xpath = "//input[@placeholder='Image Name']")
	WebElement accessLevelImageName;
	
	@FindBy(xpath = "//span//input[@type='radio' and @value='PRIVATE']")
	WebElement privateAccess;
	
	@FindBy(xpath = "//span//input[@type='radio' and @value='PUBLIC']")
	WebElement publicAccess;
	
	@FindBy(xpath = "//span//input[@type='radio' and @value='PROTECTED']")
	WebElement protectedAccess;
	
	@FindBy(xpath = "//button//span[text()=' Add Access Level']")
	WebElement addAcessLevel;

	@FindBy(xpath = "//input[@placeholder='name'][@name='name']")
	WebElement accessName;
	
	@FindBy(xpath = "//input[@placeholder='4001'][@name='port']")
	WebElement port;
	
	@FindBy(xpath = "//input[@placeholder='4001'][@name='target_port']")
	WebElement targetPort;
	
	@FindBy(xpath = "//button[@class='btn btn-v2-primary btn-sm']")
	WebElement addAccessBtn;
	
	@FindBy(xpath = "//button[@class='btn btn-submit']")
	WebElement continueBtn;
	
	@FindBy(xpath = "//div[@class='btn-group btn-icon-group']//button[@title]")
	List<WebElement> serviceBtnList;

	@FindBy(xpath = "//div[@class='heading'][contains(.,'No Cache Use')]")
	WebElement deployDetailsText1;
	
	@FindBy(xpath = "//button[contains(@class,'btn btn-primary-v2')][contains(.,'Trigger Build')]")
	WebElement triggerBuildBtn;
	
	@FindBy(xpath = "//*[text()='Stable Configuration ']")
	WebElement deployStableConfigCheck;
	
	@FindBy(partialLinkText = "Build #")
	WebElement buildHyperLink;

	@FindBy(xpath = "//div[@class='md-step active md-step-log']//div[@class='md-step-circle success']/..//div[contains(.,'Cloning Repository')]" )
	WebElement cloningRepositorySuccessLink;
	
	@FindBy(xpath = "//div[@class='md-step active md-step-log']//div[@class='md-step-circle success']/..//div[contains(.,'Pre Hooks Executing')]" )
	WebElement preHooksExecuingSuccessLink;
	
	@FindBy(xpath = "//div[@class='md-step active md-step-log']//div[@class='md-step-circle success']/..//div[contains(.,'TRIVY_IMAGE_SCANNING')]" )
	WebElement trivyImageScanningSuccessLink;
	
	@FindBy(xpath = "//div[@class='md-step active md-step-log']//div[@class='md-step-circle success']/..//div[contains(.,'Build Docker Image')]" )
	WebElement buildDockerImageSuccessLink;
	
	@FindBy(xpath = "//div[@class='d-grid grid-temp-log-line']//pre[contains(.,'docker build command')]" )
	WebElement dockerBuildCommandLogLine;
	
	@FindBy(xpath = "//div[@class='d-grid grid-temp-log-line']//pre[contains(.,'Start executing:')]" )
	WebElement preHooksCommandLogLine;
	
	@FindBy(xpath = "//label[@class='switch']//input[@name='configMap_file_path']" )
	WebElement addConfigToDeploy;
	
	@FindBy(xpath = "//select[@name='configMap_name' and @class='select']" )
	WebElement selectDropDownConfigNames;
	
	@FindBy(xpath = "//button[contains(@class,'btn-round')]//span[contains(@class,'refresh-button')]" )
	WebElement refreshBuildStatus;
	
	@FindBy(xpath = "//div[contains(@class,'round-chip bg-round-green status')][text()='SUCCESS']" )
	WebElement buildSuccessStatus;
	
	public DeployConfigurationPage() {

	}
	

	public DeployConfigurationPage CreateAndValidateDeployConfig(String AccessType,String AccessName,String portNumber,String TargetPort,ArrayList<String> ServiceButton, String configName) {
		
		//String imageNameValidateText = ServiceName+"/"+EnvName+"/"+ProjectName;
		//System.out.println(imageNameValidateText);

		ui_IsElementDisplay(ui_waitForElementToDisplay(validateDeployDetails, Pause.MEDIUM));
		ui_click(validateDeployDetails, "Poc_QA validateDeployDetails");
		ui_IsElementDisplay(ui_waitForElementToDisplay(buildPiperUIDeployConfigBtn, Pause.MEDIUM));
		ui_click(buildPiperUIDeployConfigBtn, "clicks on buildPiperUI button");
		//ui_getTextForElements(accessLevelImageName);
		//Assert.assertEquals(ui_getTextForElements(imageNameValidateText), accessLevelImageName, "Image Name Does Not Match");
		ui_click(addAcessLevel, "Add Access Level");
		ui_wait(2);
		
		if(AccessType.contains("PRIVATE"))
		{
			ui_click(privateAccess, "Poc_QA privateAccess");

		}else if(AccessType.contains("PUBLIC"))
		{

			ui_click(publicAccess, "Poc_QA publicAccess");

		}else {

			ui_click(protectedAccess, "Poc_QA protectedAccess");

		}
		ui_clearAndSetValue(accessName, AccessName);
		ui_clearAndSetValue(port, portNumber);
		ui_clearAndSetValue(targetPort, TargetPort);
		ui_click(addAccessBtn, "clicks add button");
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
//		ui_click(addConfigToDeploy, "switches config maps add to yes");
//		Select dropdown = new Select(selectDropDownConfigNames);
//		dropdown.selectByVisibleText(configName);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(continueBtn, "clicks continue button");
		ui_wait(2);
		ui_click(validateDeployDetails, "Clicks on Deploy Details tag");
		//ui_click(buildFlashBtn, "clicks build button");
//    	if (ServiceButton.contains(serviceBtnList.get(0).getAttribute("title").trim())) {
//    		
//    		System.out.println(serviceBtnList.size());
//
//    		serviceBtnList.get(0).click();
//    	}
		//serviceBtnList.get(0).click();
//		ui_click(triggerBuildBtn, "clicks trigger build button");
//		ui_IsElementDisplay(ui_waitForElementToDisplay(deployDetailsText1, Pause.MEDIUM));
//		ui_IsElementDisplay(ui_waitForElementToDisplay(deployStableConfigCheck, Pause.MEDIUM));
//		ui_click(buildHyperLink, "clicks on recent build history hyperlink");
////		ui_switchToNewWindow();
//		ui_IsElementDisplay(ui_waitForElementToDisplay(cloningRepositorySuccessLink, Pause.MEDIUM));
//		ui_click(cloningRepositorySuccessLink, "cloningRepository click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs1 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status1 = consoleLogs1.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status1);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(preHooksExecuingSuccessLink, Pause.MEDIUM));
//		ui_click(preHooksExecuingSuccessLink, "preHooksExecuing click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs2 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status2 = consoleLogs2.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status2);
//		ui_IsElementDisplay(ui_waitForElementToDisplay(preHooksExecuingSuccessLink, Pause.MEDIUM));
//		ui_click(preHooksExecuingSuccessLink, "preHooksExecuing click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs3 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status3 = consoleLogs3.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status3);
//		/////validateion from build config page /////////////
//		
//		ui_wait(6000);
//	    ui_getUIDriver().navigate().refresh();
//		
//		
//		ui_IsElementDisplay(ui_waitForElementToDisplay(buildDockerImageSuccessLink, Pause.MEDIUM));
//		ui_click(buildDockerImageSuccessLink, "preHooksExecuing click for Console Logs");
//		ui_wait(10);
//		List<String> consoleLogs4 = ui_getTextForElements("//div[@class='d-grid grid-temp-log-line']//span");
//		boolean status4 = consoleLogs4.get(0).length()>0;
//		Reporter.log("Successful Validate the Console logs", status4);
//		ui_wait(3);
//		ui_getUIDriver().quit();
		
		//String TxtValidationDockerLine = dockerBuildCommandLogLine.getText().trim();
		//TxtValidationDockerLine.contains("docker build command : DOCKER_BUILDKIT=1 docker build -t registry.buildpiper.in/automation-ccmzklcafvsaytf/qa/qa:1-20230524T1441 --build-arg qa=**** -f spring3hibernate/./Dockerfile spring3hibernate/.");
		
		//String TxtValidationPreHooksLine = preHooksCommandLogLine.getText().trim();
		//TxtValidationPreHooksLine.contains("PRE_HOOKS with order id: 1");
		
//		if(!ui_IsElementPresent(buildSuccessStatus,"5")==true) {
//			
//			refreshBuildStatus.click();
//			
//		}

		return this;
	}

}
