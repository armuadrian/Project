package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;

public class EnterILCSOMOrder extends GenericAction{
	
	private SeleniumActions sa;
/*	private String productId;
	private String orderTable;
	private String refreshButton;*/
	private List<String> params;
	
	public EnterILCSOMOrder(List<String> params) throws FileNotFoundException, IOException {

		this.sa = new SeleniumActions();
		/*this.productId = productId;
		this.orderTable = orderTable;
		this.refreshButton = refreshButton;*/
		this.params=params;
	}
	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		sa.enterIlCSOMOrder(params.get(0),params.get(1),params.get(2));
	}
	@Override
	protected void extractAndValidate() {
		if(params.size()!=3){
			//TODO logs
		}
	}
	
	

}
