package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Clickbutton extends GenericAction {

	private List<String> params;
	
	public Clickbutton(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.params = params;
		super.subject.attach(this);
	}
	
	public void execute(){
		extractAndValidate();
		super.sa.clickButton(params.get(0));
	}

	@Override
	protected void extractAndValidate() {
		if(params.size() != 1){
			super.subject.setState("Failed!");
			log.error("Clickbutton have 1 parameters!", new Exception("parameters introduced wrong!"));
		}
	}

	@Override
	public void update() {
		log.info("The object "+super.subject.getState().getClass());
	}	
}
