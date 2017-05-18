package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class WaitForCompletedStatus extends GenericAction{
	
	private List<String> params;
	
	public WaitForCompletedStatus(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.params=params;
	}
	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		super.sa.waitForCompletedOrderStatus(params.get(0), params.get(1), params.get(2));
	}

	public void extractAndValidate(){
		if(params.size()!=3){
			//TODO logs
		}
	}
	
	

}
