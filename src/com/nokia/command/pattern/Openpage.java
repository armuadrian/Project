package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;

public class Openpage extends GenericAction{

	private SeleniumActions seleniumActions;
	private List<String> params;
	
	public Openpage(List<String> params) throws FileNotFoundException, IOException{
		this.seleniumActions=new SeleniumActions();
		this.params= params;
	}
	
	public void execute() throws FileNotFoundException, IOException{
		extractAndValidate();
		seleniumActions.openpage(params.get(0));
	}
	
	public void extractAndValidate(){
		if(params.size()!=1){
			//TODO logs
		}
	}
}
