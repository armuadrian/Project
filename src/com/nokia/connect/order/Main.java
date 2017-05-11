package com.nokia.connect.order;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException{
		
		//String xmlFile2Send = "C:\\Users\\abexa\\Documents\\Chorus\\Broadband_Connect.xml";

		ExecuteAction exAct = new ExecuteAction();
		
		//Xml.sendXml(xmlFile2Send, true);

		exAct.addDataFromXml();

		exAct.execute();
	}

}
