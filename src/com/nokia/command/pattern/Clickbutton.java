package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;

public class Clickbutton extends GenericAction {

	private SeleniumActions sa;
	private List<String> params;
	
	public Clickbutton(List<String> params) throws FileNotFoundException, IOException {
		this.sa = new SeleniumActions();
		this.params = params;
	}
	
	public void execute(){
		extractAndValidate();
		sa.clickButton(params.get(0));
	}

	@Override
	protected void extractAndValidate() {
		if(params.size() != 1){
			//TODO logs
		}
	}
	
	
}
