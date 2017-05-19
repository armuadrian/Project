package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nokia.connect.order.SeleniumActions;

public abstract class GenericAction implements Command{

	protected abstract void extractAndValidate();
	protected SeleniumActions sa;
	protected final Logger log = LoggerFactory.getLogger(GenericAction.class);
	
	public GenericAction() throws FileNotFoundException, IOException{
		sa=new SeleniumActions();
	}
	
}
