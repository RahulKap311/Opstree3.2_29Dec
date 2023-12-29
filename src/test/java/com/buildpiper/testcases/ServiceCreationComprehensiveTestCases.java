package com.buildpiper.testcases;

import java.util.ArrayList;
import java.util.Iterator;

import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.buildpiper.base.BaseTest;
import com.buildpiper.pages.BuildConfigurationPage;
import com.buildpiper.pages.BuildDeployAlternatePage;
import com.buildpiper.pages.DeployConfigurationPage;
import com.buildpiper.pages.EnvironmentCreationPage;
import com.buildpiper.pages.HomePage;
import com.buildpiper.pages.LoginPage;
import com.buildpiper.pages.PreRequisitesPage;
import com.buildpiper.pages.ServiceCreationPage;
import com.buildpiper.utils.ExcelUtility;
import com.buildpiper.utils.FrameworkConfig;
import com.buildpiper.utils.Pause;
import com.buildpiper.utils.testDataUtil;

/**
 * @author: SagarT
 * @reviewer: @
 * 
 *
 */

@Listeners(com.buildpiper.report.ExtentReportListener.class)

public class ServiceCreationComprehensiveTestCases extends BaseTest {

	FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

	ExcelUtility reader = new ExcelUtility();

	 @BeforeMethod
	    public void StartDriver() {
	    	new LoginPage().login(config.username(), config.password());
	    	ui_wait(5);
	    }
	  @AfterMethod
	    public void StopDriver() {
	    	ui_getUIDriver().quit();
	    }
	@Test(groups = { "Regression" }, priority = 0)
	public void serviceOverview_TriggerBuild_NoCache() {
		
		int rowCount = reader.getRowCount("MicroServiceData");
		System.out.println(rowCount);

		ArrayList<String> chipList = new ArrayList<String>();
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");
        for(int i=3; i < 10; i++) {
		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(4);
//		new ServiceCreationPage().buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", 2),
//				reader.getCellData("MicroServiceData", "envName", 2),
//				reader.getCellData("MicroServiceData", "buildRadioButtonName", 2), list,
//				reader.getCellData("MicroServiceData", "JobTemplateValue", 2));
//		new BuildConfigurationPage().CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", 2),
//				reader.getCellData("MicroServiceData", "BranchName", 2),
//				reader.getCellData("MicroServiceData", "FilePath", 2),
//				reader.getCellData("MicroServiceData", "DockerFilePath", 2), chipList, languageList,
//				reader.getCellData("MicroServiceData", "preHookPass", 2),
//				reader.getCellData("MicroServiceData", "envName", 2));
//		new DeployConfigurationPage().CreateAndValidateDeployConfig(
//				reader.getCellData("MicroServiceData", "AccessType", 2),
//				reader.getCellData("MicroServiceData", "AccessName", 2),
//				reader.getCellData("MicroServiceData", "portNumber", 2),
//				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
//				reader.getCellData("MicroServiceData", "configName", 2));
		new ServiceCreationPage().searchService(reader.getCellData("MicroServiceData", "applicationName", i),
				reader.getCellData("MicroServiceData", "serviceName", i));
		new ServiceCreationPage().buildTrigger();
		ui_getUIDriver().close();
	}
	}
	
	@Test(groups = { "Regression" }, priority = 0)
	public void serviceOverview_TriggerBuild_Cache() {

		ArrayList<String> chipList = new ArrayList<String>();
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");

	//	new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new ServiceCreationPage().buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", 2), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 2));
		new BuildConfigurationPage().CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2),
				reader.getCellData("MicroServiceData", "FilePath", 2),
				reader.getCellData("MicroServiceData", "DockerFilePath", 2), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", 2),
				reader.getCellData("MicroServiceData", "envName", 2));
		new DeployConfigurationPage().CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));		
		new ServiceCreationPage().buildButton_Click();
		ui_wait(3);
		new ServiceCreationPage().Verify_EnvironmentandSubEnvironment("DEV","dev1");
		ui_wait(3);
		new ServiceCreationPage().CacheCheckbox_Click();
		ui_wait(3);
		new ServiceCreationPage().triggerBuild_Click();
		ui_wait(3);
		new ServiceCreationPage().Verify_buildStatus("RUNNING");
		ui_wait(3);
		new ServiceCreationPage().buildRecentButtonClick();
		ui_wait(3);
		//ui_getUIDriver().close();
	}


	@Test(groups = { "Regression" }, priority = 1)
	public void serviceOverview_Deploy() {

		ArrayList<String> chipList = new ArrayList<String>();
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");

		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new ServiceCreationPage().buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", 2), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 2));
		new BuildConfigurationPage().CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2),
				reader.getCellData("MicroServiceData", "FilePath", 2),
				reader.getCellData("MicroServiceData", "DockerFilePath", 2), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", 2),
				reader.getCellData("MicroServiceData", "envName", 2));
		new DeployConfigurationPage().CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
		new ServiceCreationPage().deployService();
		ui_getUIDriver().close();
	}


	@Test(groups = { "Regression" }, priority = 2)
	public void serviceOverview_Promote() {
		ServiceCreationPage servicecreationpage=new ServiceCreationPage();
		BuildConfigurationPage buildconfig=new BuildConfigurationPage();
		DeployConfigurationPage deployconfig=new DeployConfigurationPage();
		ArrayList<String> chipList = new ArrayList<String>();
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");

		//new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		ui_wait(5);
		servicecreationpage.buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", 2), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 2));
		buildconfig.CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2),
				reader.getCellData("MicroServiceData", "FilePath", 2),
				reader.getCellData("MicroServiceData", "DockerFilePath", 2), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", 2),
				reader.getCellData("MicroServiceData", "envName", 2));
		deployconfig.CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
		servicecreationpage.addNewEnvironmentToService(reader.getCellData("MicroServiceData", "toEnv", 2), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 2),
				reader.getCellData("MicroServiceData", "cloneText", 2),
				reader.getCellData("MicroServiceData", "envCloneValue", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2),
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2));
		String servicename=servicecreationpage.servicename;
				System.out.println(servicename);
		servicecreationpage.SearchServiceViaRandomStringValue(reader.getCellData("MicroServiceData", "applicationName", 2),servicename);
				
		//build Service
		ui_wait(2);
		new ServiceCreationPage().buildButton_Click();
		ui_wait(1);
		//new ServiceCreationPage().buildButton_Click();
		ui_wait(5);
		new ServiceCreationPage().Verify_EnvironmentandSubEnvironment("DEV",reader.getCellData("MicroServiceData", "envName", 2));
		ui_wait(3);
		new ServiceCreationPage().triggerBuild_Click();
		ui_wait(8);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(3);
		new ServiceCreationPage().Verify_buildStatus("RUNNING");
		ui_wait(90);
		new ServiceCreationPage().buildRecentButtonClick();
		ui_switchToNewWindow();
		ui_wait(10);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(40);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(10);
		new ServiceCreationPage().Verify_buildStatus("SUCCESS");
		ui_wait(3);
		new ServiceCreationPage().closeBuildWindow();
		ui_wait(8);
		new ServiceCreationPage().RefreshService_Click();
		ui_wait(2);
		ui_IsElementDisplay(ui_waitForElementToDisplay(new ServiceCreationPage().buildArtifact, Pause.MEDIUM));
		String ArtifactID=new ServiceCreationPage().buildArtifact.getText();
		
		//Deploy Service
		new ServiceCreationPage().deployService(ArtifactID);
		ui_wait(3);
	//	new ServiceCreationPage().Verify_deployStatus("RUNNING");
		ui_wait(4);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(60);
		new ServiceCreationPage().deployRecentButtonClick();
		ui_switchToNewWindow();
		ui_wait(20);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(10);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(3);
		new ServiceCreationPage().Verify_deployStatus("SUCCESS");
		ui_wait(3);
		new ServiceCreationPage().closeDeployWindow();
		ui_wait(3);
		
		//Promote Service		
		new ServiceCreationPage().promoteService(reader.getCellData("MicroServiceData", "toEnv", 2),ArtifactID);
		ui_wait(3);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(3);
		new ServiceCreationPage().Verify_promoteStatus("RUNNING");	
		ui_wait(4);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(60);
		new ServiceCreationPage().promoteRecentButtonClick();
		ui_switchToNewWindow();
		ui_wait(8);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(30);
		new ServiceCreationPage().RefreshBuildandDeploy_Click();
		ui_wait(3);
		new ServiceCreationPage().Verify_promoteStatus("SUCCESS");
		ui_wait(3);
		new ServiceCreationPage().closeDeployWindow();
		ui_wait(3);
			
		ui_wait(30);
		
		//Validate monitor Service
		 new ServiceCreationPage().validateServiceMonitoring();
		 ui_wait(3);
		 
		// Validate History Screen 
		new ServiceCreationPage().serviceHistory();		
		
		// Switch to QA Environment and Validate Artifact ID on QA 
		new ServiceCreationPage().switchEnvironmentTab("QA");
		ui_wait(20);
		String ArtifactID1=new ServiceCreationPage().deployandPromoteartifactID1.getText();
		Assert.assertEquals(ArtifactID, ArtifactID1);
		
		
	}



	@Test(groups = { "Regression" }, priority = 3)
	public void serviceOverview_History() {

		ArrayList<String> chipList = new ArrayList<String>();
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");

		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new ServiceCreationPage().buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", 2), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 2));
		new BuildConfigurationPage().CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2),
				reader.getCellData("MicroServiceData", "FilePath", 2),
				reader.getCellData("MicroServiceData", "DockerFilePath", 2), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", 2),
				reader.getCellData("MicroServiceData", "envName", 2));
		new DeployConfigurationPage().CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
		ui_getUIDriver().close();
	}

	@Test(groups = { "Regression" }, priority = 4)
	public void serviceOverview_Monitoring() {

		ArrayList<String> chipList = new ArrayList<String>();
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");

		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new ServiceCreationPage().buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", 2), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 2));
		new BuildConfigurationPage().CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2),
				reader.getCellData("MicroServiceData", "FilePath", 2),
				reader.getCellData("MicroServiceData", "DockerFilePath", 2), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", 2),
				reader.getCellData("MicroServiceData", "envName", 2));
		new DeployConfigurationPage().CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
		ui_getUIDriver().close();
	}
	
	@Test(groups = { "Regression" }, priority = 5)
	public void serviceOverview_TriggerBuildAndDeploy_Cache() {

		ArrayList<String> chipList = new ArrayList<String>();
		chipList.add("linux/arm64");
		chipList.add("linux/amd64");

		ArrayList<String> languageList = new ArrayList<String>();
		languageList.add("JAVA");

		ArrayList<String> list = new ArrayList<String>();
		list.add("QA");
		list.add("DEV");
		list.add("DevOps");

		ArrayList<String> serviceButton = new ArrayList<String>();
		serviceButton.add("Build");
		serviceButton.add("Deploy");
		serviceButton.add("History");
		serviceButton.add("Monitoring");

		new LoginPage().login(config.username(), config.password());
		new PreRequisitesPage().switchUser();
		new ServiceCreationPage().buildAndValidateService(reader.getCellData("MicroServiceData", "applicationName", 2),
				reader.getCellData("MicroServiceData", "envName", 2),
				reader.getCellData("MicroServiceData", "buildRadioButtonName", 2), list,
				reader.getCellData("MicroServiceData", "JobTemplateValue", 2));
		new BuildConfigurationPage().CreateAndValidateBuildConfig(reader.getCellData("MicroServiceData", "gitURL", 2),
				reader.getCellData("MicroServiceData", "BranchName", 2),
				reader.getCellData("MicroServiceData", "FilePath", 2),
				reader.getCellData("MicroServiceData", "DockerFilePath", 2), chipList, languageList,
				reader.getCellData("MicroServiceData", "preHookPass", 2),
				reader.getCellData("MicroServiceData", "envName", 2));
		new DeployConfigurationPage().CreateAndValidateDeployConfig(
				reader.getCellData("MicroServiceData", "AccessType", 2),
				reader.getCellData("MicroServiceData", "AccessName", 2),
				reader.getCellData("MicroServiceData", "portNumber", 2),
				reader.getCellData("MicroServiceData", "TargetPort", 2), serviceButton,
				reader.getCellData("MicroServiceData", "configName", 2));
		new ServiceCreationPage().buildTriggerAndDeploy();
		ui_getUIDriver().close();
	}

}


