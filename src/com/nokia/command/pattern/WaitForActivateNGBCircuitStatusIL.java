package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import com.nokia.connect.order.WorkflowClient;

public class WaitForActivateNGBCircuitStatusIL extends GenericAction {

	private WorkflowClient wfc;
	private List<String> params;

	public WaitForActivateNGBCircuitStatusIL(List<String> params) throws FileNotFoundException, IOException {
		super();
		this.wfc = new WorkflowClient();
		this.params = params;
		super.subject.attach(this);
	}

	@Override
	public void execute() throws FileNotFoundException, IOException, InterruptedException {
		extractAndValidate();
		super.sa.waitForActivateNGBCircuitStatusIl(wfc, params.get(0), params.get(1), params.get(2), params.get(3),
				params.get(4));
	}

	public void extractAndValidate() {
		if (params.size() != 5) {
			super.subject.setState("Failed!");
			log.error("WaitForActivateNGBCircuitStatusIl have 5 parameters!", new Exception("parameters introduced wrong!"));
		}
	}

	@Override
	public void update() {
		log.info("The object "+super.subject.getState().getClass());
	}
}
