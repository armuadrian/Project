package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class WaitForStatusWFC extends GenericAction{
	
	private List<String> params;
	
	public WaitForStatusWFC(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.params=params;
		super.subject.attach(this);
	}
	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		super.sa.waitForStatusWfc(params.get(0), params.get(1), params.get(2));
	}
	
	public void extractAndValidate(){
		if(params.size()!=3){
			super.subject.setState("Failed!");
			log.error("WaitForStatusWFC have 3 parameters!", new Exception("parameters introduced wrong!"));
		}
	}
	@Override
	public void update() {
		log.info("The object "+super.subject.getState().getClass());
	}
}
