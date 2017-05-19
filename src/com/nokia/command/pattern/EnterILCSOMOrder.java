package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class EnterILCSOMOrder extends GenericAction{
	
	private List<String> params;
	
	public EnterILCSOMOrder(List<String> params) throws FileNotFoundException, IOException {

		super();
		
		this.params=params;
	}
	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		super.sa.enterIlCSOMOrder(params.get(0),params.get(1),params.get(2));
	}
	@Override
	protected void extractAndValidate() {
		if(params.size()!=3){
			log.error("EnterILCSOMOrder have 0 parameters!", new Exception("parameters introduced wrong!"));
		}
	}
	
	

}
