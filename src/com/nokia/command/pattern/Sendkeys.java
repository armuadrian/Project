package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class Sendkeys extends GenericAction{
	
	private List<String> params;
	
	public Sendkeys(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.params=params;
	}
	
	public void execute(){
		extractAndValidate();
		super.sa.sendKey(params.get(0), params.get(1));
	}

	public void extractAndValidate(){
		if(params.size()!=2){
			//TODO logs
		}
	}
}
