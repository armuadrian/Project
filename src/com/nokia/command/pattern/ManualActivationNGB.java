package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;

public class ManualActivationNGB extends GenericAction{

	private SeleniumActions sa;
	private List<String> params;

	public ManualActivationNGB(List<String> params) throws FileNotFoundException, IOException {
		this.sa = new SeleniumActions();
		this.params=params;
	}

	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		sa.doManualActivationNGBCircuitFallout();
	}
	
	public void extractAndValidate(){
		if(params.size()!=0){
			//TODO logs
		}
	}
}
