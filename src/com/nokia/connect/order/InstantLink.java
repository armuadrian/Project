package com.nokia.connect.order;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.nokia.action.SeleniumActions;

public class InstantLink  extends SeleniumActions {
	
	private String ilLink = "http://cfiwn02-app2.nz.alcatel-lucent.com:44080/sas5/";
	private String omIlLink = "http://cfiwn02-app2.nz.alcatel-lucent.com:44080/sas5/navigation_servlet/showOM";
	private String enterUsername = "username";
	private String enterPassword = "password";
	private String logInButton = "btnauthenticate";
	private String clearButton = "btnclearSearchForm";
	private String orderNoId = "txtorderNo";
	private String searchOrderButton = "//*[@id=\"OrdersForm\"]/div/table/tbody/tr[2]/td/table/tbody/tr/td/button[1]";
	private String tableFromOrder = "//*[@id=\"content\"]/form/table[2]";
	private String tableWithOrders = "//*[@id=\"OrdersForm\"]/table";
//	private String activity = "3/3";
//	private String refreshButton = "//*[@id=\"content\"]/form/div[2]/div/button[3]";
	private String refreshButton = "//*[@id=\"OrdersForm\"]/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button[1]";
	private String orderStatus = "Wait for Orchestration Signal";
	private String xmlFile2Send = "C:\\Users\\opis\\Documents\\CHORUS\\XML\\NotifyOrderComplete.xml";
	private Properties props;
//	private String goBackToWorkItemsButton = "//*[@id=\"content-div\"]/div[1]/table/tbody/tr/td/a[2]";
	private String goBackToWorkItemsButton = "http://cfiwn02-app2.nz.alcatel-lucent.com:44080/sas5/order_management_servlet/showOrders?same_search=true&navigation_text=Order%20Management&navigation_uri=/navigation_servlet/showOM&navigation_text=Orders&navigation_uri=/order_management_servlet/showOrders?same_search=true";
	private String completedStatus = "Completed";
	private String user = "Administrator";
	private String pass = "guiadmin";
	private String SOAPUrl = "http://cfiwn02-app2.nz.alcatel-lucent.com:44006/ilws/InstantLinkSOA?wsdl";
	
	
	
	private String extServiceId = "7575333375";
	
	
	
	
	public InstantLink() throws FileNotFoundException, IOException {
		super();
		props = new Properties();
		props.load(new FileInputStream("files/config.properties"));
	}


	public void ilFirstAction(WorkflowClient wfc) throws InterruptedException, FileNotFoundException, IOException{ 

		String orderNo = props.getProperty("orderNo");

		openpage(ilLink);
		login(enterUsername, user, enterPassword, pass);
		clickButton(logInButton);
		openpage(omIlLink);
		clickButton(clearButton);
		sendKey(orderNoId, orderNo);
		clickButton(searchOrderButton);
//		waitForActivities(activity, table2IlXpath, searchOrderButton); // delete 
		enterIlCSOMOrder(extServiceId, tableWithOrders, searchOrderButton);
	//	waitForActivateNGBCircuitStatusIl(wfc, table1IlXpath);
		
	}
	
	public void ilSecondAction() throws InterruptedException, IOException{
		
		String orderNo = props.getProperty("orderNo");
		Xml xml = new Xml();
		
		openpage(ilLink);
//		login(enterUsername, user, enterPassword, pass);// comment it
//		clickButton(logInButton);// comment it 
		openpage(omIlLink);
		clickButton(clearButton);
		sendKey(orderNoId, orderNo);
		clickButton(searchOrderButton);
//		waitForActivities(activity, table2IlXpath, searchOrderButton);// comment it
		enterIlOrder();
		waitForStatusIl(orderStatus, tableFromOrder, refreshButton);
		xml.sendSOAPXml(SOAPUrl ,xmlFile2Send, true);
//		exitOrder(goBackToWorkItemsButton);
		openpage(goBackToWorkItemsButton);
		waitForCompletedOrderStatus(completedStatus, extServiceId, tableWithOrders);
		
	}


	public void testActivate(WorkflowClient wfc) throws FileNotFoundException, IOException, InterruptedException {
		
		String orderNo = props.getProperty("orderNo");
		
		openpage(ilLink);
		login(enterUsername, user, enterPassword, pass);
		clickButton(logInButton);
		openpage(omIlLink);
		clickButton(clearButton);
		sendKey(orderNoId, orderNo);
		clickButton(searchOrderButton);
		enterIlCSOMOrder(extServiceId, tableWithOrders, searchOrderButton);
	//	waitForActivateNGBCircuitStatusIl(wfc, table1IlXpath);
	}


}
