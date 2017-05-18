package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.nokia.connect.order.SeleniumActions;

public abstract class GenericAction implements Command{

	protected abstract void extractAndValidate();
	protected SeleniumActions sa;
	
	public GenericAction() throws FileNotFoundException, IOException{
		sa=new SeleniumActions();
	}
	
}
