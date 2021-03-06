package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class ManualActivationNGB extends GenericAction{

	private List<String> params;

	public ManualActivationNGB(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.params=params;
		super.subject.attach(this);
	}

	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		super.sa.doManualActivationNGBCircuitFallout();
	}
	
	public void extractAndValidate(){
		if(params.size()!=0){
			super.subject.setState("Failed!");
			log.error("ManualActivationNGB have 0 parameters!", new Exception("parameters introduced wrong!"));
		}
	}

	@Override
	public void update() {
		log.info("The object "+super.subject.getState().getClass());
	}
}
