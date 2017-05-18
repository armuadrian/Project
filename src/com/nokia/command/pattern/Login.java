package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.nokia.connect.order.SeleniumActions;

public class Login extends GenericAction{
	
	private SeleniumActions sa;
/*	private String nameId;
	private String username;
	private String passwordId;
	private String password;*/
	private List<String> params;
	
	public Login(List<String> params) throws FileNotFoundException, IOException{
		this.sa=new SeleniumActions();
/*		this.nameId=nameid;
		this.username=user;
		this.passwordId=passId;
		this.password=password;*/
		this.params=params;
	}

	@Override
	public void execute() throws FileNotFoundException, IOException {
		extractAndValidate();
		sa.login(params.get(0), params.get(1), params.get(2), params.get(3));
		
	}
	
	public void extractAndValidate(){
		if(params.size()!=4){
			//TODO logs
		}
	}

}
