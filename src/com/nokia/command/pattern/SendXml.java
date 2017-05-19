package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.Xml;


public class SendXml extends GenericAction{

	private Xml xml;
/*	private String soapUrl;
	private String xmlFile2Send;
	private boolean verifyProxy = false;*/
	private List<String> params;
	
	public SendXml(List<String> params) throws FileNotFoundException, IOException {
		this.xml = new Xml();
		this.params=params;
	}
	
	@Override
	public void execute() throws FileNotFoundException, IOException {
		extractAndValidate();
		xml.sendSOAPXml(params.get(0), params.get(1), true);
	}
	
	public void extractAndValidate(){
		if(params.size()!=2){
			log.error("SendXml have 2 parameters!", new Exception("parameters introduced wrong!"));
		}
	}
	
}
