package com.nokia.connect.order;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import com.nokia.command.pattern.GenericAction;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException,
			NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, ClassNotFoundException {

//		String xmlFile2Send = "C:\\Users\\opis\\Documents\\devTestXml.xml";
//		String SOAPUrl = "http://cfiwn02-app2.nz.alcatel-lucent.com:44006/ilws/InstantLinkSOA?wsdl";

		ExecuteAction exAct = new ExecuteAction();
//		InstantLink il = new InstantLink();
//		WorkflowClient wfc = new WorkflowClient();

		// Xml.sendSOAPXml(SOAPUrl, xmlFile2Send, true);

		exAct.addDataFromXml();
		List<Object> lista = exAct.getInstances();
		Iterator<Object> it = lista.iterator();
		while (it.hasNext()) {
//			System.out.println(it.next() + "");
			GenericAction ga = (GenericAction) it.next();
			ga.execute();
		}
		System.exit(0);
		// il.ilFirstAction(wfc);
		// wfc.wfcActions();
		// il.ilSecondAction();
		// il.testActivate();
	}

}
