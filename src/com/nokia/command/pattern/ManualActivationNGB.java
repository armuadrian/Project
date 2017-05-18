package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class ManualActivationNGB extends GenericAction{

	private List<String> params;

	public ManualActivationNGB(List<String> params) throws FileNotFoundException, IOException {
		this.params=params;
	}

	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		super.sa.doManualActivationNGBCircuitFallout();
	}
	
	public void extractAndValidate(){
		if(params.size()!=0){
			//TODO logs
		}
	}
}
