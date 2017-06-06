package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.Xml;


public class SendXml extends GenericAction{

	private Xml xml;
	private List<String> params;
	
	public SendXml(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.xml = new Xml();
		this.params=params;
		super.subject.attach(this);
	}
	
	@Override
	public void execute() throws FileNotFoundException, IOException {
		extractAndValidate();
		xml.sendSOAPXml(params.get(0), params.get(1), true);
	}
	
	public void extractAndValidate(){
		if(params.size()!=2){
			super.subject.setState("Failed!");
			log.error("SendXml have 2 parameters!", new Exception("parameters introduced wrong!"));
		}
	}

	@Override
	public void update() {
		log.info("The object "+super.subject.getState().getClass());
	}	
}
