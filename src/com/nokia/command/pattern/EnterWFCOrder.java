package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;

public class EnterWFCOrder extends GenericAction{
	
	private SeleniumActions sa;
	private List<String> params;

	public EnterWFCOrder(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.sa = new SeleniumActions();
		this.params=params;
	}
	
	public void execute(){
		extractAndValidate();
		sa.enterWfcOrder();
	}
	
	public void extractAndValidate(){
		if(params.size()!=0){
			//TODO logs
		}
	}
}
