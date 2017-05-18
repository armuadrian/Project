package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;

public class SendManualActivation extends GenericAction{
	
	private SeleniumActions sa;
	private List<String> params;

	public SendManualActivation(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.sa = new SeleniumActions();
		this.params=params;
	}

	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		sa.sendContinueManualActivation();
	}
	
	public void extractAndValidate(){
		if(params.size()!=3){
			//TODO logs
		}
	}

}
