package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Login extends GenericAction{

	private List<String> params;
	
	public Login(List<String> params) throws FileNotFoundException, IOException{
		super();
		this.params=params;
		super.subject.attach(this);
	}

	@Override
	public void execute() throws FileNotFoundException, IOException {
		extractAndValidate();
		super.sa.login(params.get(0), params.get(1), params.get(2), params.get(3));
		
	}
	
	public void extractAndValidate(){
		if(params.size()!=4){
			super.subject.setState("Failed!");
			log.error("parameters doesn't look good!", new Exception("parameters wrong!"));
		}
	}

	@Override
	public void update() {
		log.info("The object "+super.subject.getState().getClass());
	}
}
