package com.nokia.connect.order;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.nokia.xml.Config;
import com.nokia.xml.Config.Actions;
import com.nokia.xml.Config.Actions.Action;

public class ExecuteAction {

	private Config config;
	private SeleniumActions selAc;
	private Config.Actions actions;
	private List<Actions.Action> action;

	public ExecuteAction() throws FileNotFoundException, IOException {
		selAc = new SeleniumActions();
	}

	public void addDataFromXml() {
		try {
			// create JAXBContext
			String context = "com.nokia.test.xsd";

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

	public void execute() throws FileNotFoundException, IOException {

		for (Action c: action) {
			List<String> parametrii = c.getParam();
			
			switch (c.getName()) {
			case "openpage":
				selAc.openpage(parametrii.get(0));
				break;
			case "login":
				selAc.login(parametrii.get(0), parametrii.get(1), parametrii.get(2), parametrii.get(3));
				break;
			case "clickbutton":
				selAc.clickButton(parametrii.get(0));
				break;
			default:
				System.out.println("tag name doesn't exist!");
				break;
			}
		}
	}
}
