package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class SendManualActivation extends GenericAction{
	
	private List<String> params;

	public SendManualActivation(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.params=params;
	}

	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		super.sa.sendContinueManualActivation();
	}
	
	public void extractAndValidate(){
		if(params.size()!=3){
			//TODO logs
		}
	}

}
