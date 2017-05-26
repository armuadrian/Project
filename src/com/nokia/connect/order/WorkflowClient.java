package com.nokia.connect.order;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.nokia.action.SeleniumActions;

public class WorkflowClient extends SeleniumActions {
	
	private String wfcLink = "http://ec2-52-65-62-217.ap-southeast-2.compute.amazonaws.com:44380/wfc_ui";
	private String logInButton = "//*[@id=\"login-table-background\"]/table[2]/tbody/tr/td/button[1]";
	private String enterUsername = "j_username";
	private String enterPassword = "j_password";
	private String workQueueXpath = "//*[@id=\"mainForm\"]/table/tbody/tr/td/table[2]/tbody/tr[1]/td/div[1]/a";
	private String clearButton = "//*[@id=\"mainForm:workspace_working_area_view:_idJsp59\"]";
	private String productIdXpath = "//*[@id=\"mainForm:workspace_working_area_view:additionalSearchFields_14\"]";
	private String searchButton = "//*[@id=\"mainForm:workspace_working_area_view:_idJsp58\"]";
//	private String wfcFirstNotifyStatusOrder = "Unable to Activate NGB Circuit";
//	private String wfcSecondNotifyStatusOrder = "Wait For Manual Activation";
//	private String tableWfcXpath = "//*[@id=\"mainForm:workspace_working_area_view:actionTicketListingTable\"]";
	private Properties props;
	private String user = "Administrator";
	private String pass = "guiadmin";

	public WorkflowClient() throws FileNotFoundException, IOException {
		super();
		props = new Properties();
		props.load(new FileInputStream("files/config.properties"));
	}

	public void wfcActions(String productId2, String tableFromWfc,
			String wfcFirstNotifyStatus, String wfcSecondNotifyStatus)
			throws InterruptedException, FileNotFoundException, IOException {

		openpage(wfcLink);
		login(enterUsername, user, enterPassword, pass);
		clickButton(logInButton);
		clickButton(workQueueXpath);
		clickButton(clearButton);
		sendKey(productIdXpath, productId2);
		clickButton(searchButton);
		waitForStatusWfc(wfcFirstNotifyStatus, tableFromWfc, searchButton);
		enterWfcOrder();
		doManualActivationNGBCircuitFallout();
		clickButton(clearButton);
		sendKey(productIdXpath, productId2);
		clickButton(searchButton);
		waitForStatusWfc(wfcSecondNotifyStatus, tableFromWfc, searchButton);
		enterWfcOrder();
		sendContinueManualActivation();

	}
}
