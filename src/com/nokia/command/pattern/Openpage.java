package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Openpage extends GenericAction{

	private List<String> params;
	
	public Openpage(List<String> params) throws FileNotFoundException, IOException{
		super();
		this.params= params;
	}
	
	public void execute() throws FileNotFoundException, IOException{
		extractAndValidate();
		super.sa.openpage(params.get(0));
	}
	
	public void extractAndValidate(){
		if(params.size()!=1){
			//TODO logs
		}
	}
}
