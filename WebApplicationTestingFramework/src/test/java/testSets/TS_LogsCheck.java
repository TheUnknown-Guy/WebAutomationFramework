package testSets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
/*
 * Author - Sachin Jagad
 * This Class is created to check the log4j2 functionality, it is an independent class.
 * Run this as TestNG individually to check if the log is generated under logs folder.
 * Check the log file to see if the log message are getting added into the file. 
 * */

public class TS_LogsCheck {

	final Logger log = LogManager.getLogger(TS_LogsCheck.class.getName());
	@Test
	public void TEST_Logging()
	{
		log.debug("Debug Logged message.");
		log.info("Info Logged message.");
		log.trace("Trace Logged message.");
		log.warn("Warning Logged message.");
		log.error("Error Logged message.");
		log.fatal("Fatal Logged message.");
		System.out.println("Execution completed.");
	}

}
