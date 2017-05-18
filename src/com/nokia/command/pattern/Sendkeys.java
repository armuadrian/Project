package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;

public class Sendkeys extends GenericAction{
	
	private SeleniumActions sa;
	/*private String element;
	private String keysToSendToElement;*/
	private List<String> params;
	
	public Sendkeys(List<String> params) throws FileNotFoundException, IOException {
		this.sa = new SeleniumActions();
		this.params=params;
	}
	
	public void execute(){
		extractAndValidate();
		sa.sendKey(params.get(0), params.get(1));
	}

	public void extractAndValidate(){
		if(params.size()!=2){
			//TODO logs
		}
	}
}
