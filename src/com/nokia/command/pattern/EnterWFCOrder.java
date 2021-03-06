package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EnterWFCOrder extends GenericAction{
	
	private List<String> params;

	public EnterWFCOrder(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.params=params;
		super.subject.attach(this);
	}
	
	public void execute(){
		extractAndValidate();
		super.sa.enterWfcOrder();
	}
	
	public void extractAndValidate(){
		if(params.size()!=0){
			super.subject.setState("Failed!");
			log.error("EnterWFCOrder have 0 parameters!", new Exception("parameters introduced wrong!"));
		}
	}

	@Override
	public void update() {
		log.info("The object "+super.subject.getState().getClass());
	}
}
