package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nokia.connect.order.SeleniumActions;
import com.nokia.observer.pattern.Observer;
import com.nokia.observer.pattern.Subject;

public abstract class GenericAction extends Observer implements Command{

	protected abstract void extractAndValidate();
	protected SeleniumActions sa;
	protected final Logger log = LoggerFactory.getLogger(GenericAction.class);
	
	public GenericAction() throws FileNotFoundException, IOException{
		sa=new SeleniumActions();
		super.subject = new Subject();
	}
	
	public abstract void update();
}
