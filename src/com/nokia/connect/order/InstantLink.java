package com.nokia.connect.order;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class InstantLink  extends SeleniumActions {
	
	private String ilLink = "http://cfiwn02-app2.nz.alcatel-lucent.com:44080/sas5/";
	private String omIlLink = "http://cfiwn02-app2.nz.alcatel-lucent.com:44080/sas5/navigation_servlet/showOM";
	private String enterUsername = "username";
	private String enterPassword = "password";
	private String logInButton = "btnauthenticate";
	private String clearButton = "btnclearSearchForm";
	private String orderNoXpath = "txtorderNo";
	private String searchOrderButton = "//*[@id=\"OrdersForm\"]/div/table/tbody/tr[2]/td/table/tbody/tr/td/button[1]";
	private String table1IlXpath = "//*[@id=\"content\"]/form/table[2]";
	private String table2IlXpath = "//*[@id=\"OrdersForm\"]/table";
	private String activity = "3/3";
	private String refreshButton = "//*[@id=\"content\"]/form/div[2]/div/button[3]";
	private String orderStatus = "Wait for ICMS Service Order Closure";
	private String xmlFile2Send = "C:\\Users\\abexa\\Documents\\Chorus\\NotifyOrderComplete.xml";
	private Properties props;
	private String goBackToWorkItemsButton = "//*[@id=\"content-div\"]/div[1]/table/tbody/tr/td/a[2]";
	private String completedStatus = "Completed";
	private String extServiceId = "112233";
	
	public InstantLink() throws FileNotFoundException, IOException {
		super();
		props = new Properties();
		props.load(new FileInputStream("files/config.properties"));
		// TODO Auto-generated constructor stub
	}


	public void ilFirstAction() throws InterruptedException, FileNotFoundException, IOException{ 

		String orderNo = props.getProperty("orderNo");

		openpage(ilLink);
		//login(enterUsername, enterPassword);
		clickButton(logInButton);
		openpage(omIlLink);
		clickButton(clearButton);
		sendKey(orderNoXpath, orderNo);
		clickButton(searchOrderButton);
		waitForActivities(activity, table2IlXpath, searchOrderButton);
		
	}
	
	public void ilSecondAction() throws InterruptedException, IOException{
		
		String orderNo = props.getProperty("orderNo");
		
		openpage(ilLink);
		openpage(omIlLink);
		clickButton(clearButton);
		sendKey(orderNoXpath, orderNo);
		clickButton(searchOrderButton);
//		enterIlOrder();
//		waitForStatusIl(orderStatus, table1IlXpath, refreshButton);
//		Xml.sendXml(xmlFile2Send, true);
//		exitOrder(goBackToWorkItemsButton);
		waitForCompletedOrderStatus(completedStatus, extServiceId, table2IlXpath, refreshButton);
		
	}


}
