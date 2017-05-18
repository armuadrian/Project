package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class EnterILOrder extends GenericAction{

	private List<String> params;

	public EnterILOrder(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.params=params;
	}
	
	public void execute() throws FileNotFoundException, IOException{
		extractAndValidate();
		super.sa.enterIlOrder();
	}
	
	public void extractAndValidate(){
		if(params.size()!=0){
			//TODO logs
		}
	}
}
