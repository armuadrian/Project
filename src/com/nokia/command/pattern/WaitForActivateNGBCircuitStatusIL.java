package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;
import com.nokia.connect.order.WorkflowClient;

public class WaitForActivateNGBCircuitStatusIL extends GenericAction{

	private SeleniumActions sa;
	private WorkflowClient wfc;
//	private String table1IlXpath;
	private List<String> params;
	
	public WaitForActivateNGBCircuitStatusIL(List<String> params) throws FileNotFoundException, IOException {
		this.sa = new SeleniumActions();
		this.wfc = new WorkflowClient();
		this.params=params;
	}
	
	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		sa.waitForActivateNGBCircuitStatusIl(wfc, params.get(0));
	}
	
	public void extractAndValidate(){
		if(params.size()!=1){
			//TODO logs
		}
	}
}
