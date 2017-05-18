package com.nokia.command.pattern;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {

	public void execute() throws FileNotFoundException, IOException, InterruptedException;
}
