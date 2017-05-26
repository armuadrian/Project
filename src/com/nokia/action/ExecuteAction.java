package com.nokia.connect.order;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.nokia.xml.generated.classes.Config;
import com.nokia.xml.generated.classes.Config.Actions;
import com.nokia.xml.generated.classes.Config.Actions.Action;

public class ExecuteAction {

	private Config config;
	private Config.Actions actions;
	private List<Actions.Action> action;

	public ExecuteAction(){}

	public void addDataFromXml() {
		try {
			// create JAXBContext
			String context = "com.nokia.xml.generated.classes";

			// Create an instance of JAXB Context
			JAXBContext jContext = JAXBContext.newInstance(context);

			// Unmarshal the data from InputStream
			config = (Config) jContext.createUnmarshaller()
					.unmarshal(new File("C:/Users/opis/Documents/AutomationProject/automation.xml"));

			actions = config.getActions();
			action = actions.getAction();

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public List<Object> getInstances() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {

		List<Object> callAction = new LinkedList<>();
		Iterator<Action> it = action.iterator();

		while (it.hasNext()) {
			Action action = it.next();
			Class<?> classDefinition = Class.forName("com.nokia.command.pattern."+action.getName());
			Constructor<?> cons = classDefinition.getConstructor(List.class);
			Object date = cons.newInstance(action.getParam());
			callAction.add(date);
		}
		return callAction;
	}

}
