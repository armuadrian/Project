package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;

public class WaitForCompletedStatus extends GenericAction{
	
	private SeleniumActions sa;
	/*private String status;
	private String productId;
	private String table2IlPath;*/
	private List<String> params;
	
	public WaitForCompletedStatus(List<String> params) throws FileNotFoundException, IOException {
		this.sa = new SeleniumActions();
		this.params=params;
	}
	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		sa.waitForCompletedOrderStatus(params.get(0), params.get(1), params.get(2));
	}

	public void extractAndValidate(){
		if(params.size()!=3){
			//TODO logs
		}
	}
	
	

}
