package api;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
* This lets us log various logic throughout our application.
* @author  Zach Firestone
* 
*/
public class Logger implements Runnable {

	/**
	 * The thread instance.
	 */
	private Thread thread;
	
	/**
	 * The type of logging being done.
	 */
	private String logType;
	
	/**
	 * The message being logged.
	 */
	private String logMessage;

	/**
	 * The constructor that sets the logging data.
	 * @param type The type of logging being done.
	 * @param message The message being logged.
	 */
	Logger(String type, String message) {

		logType = type;
		logMessage = message;
	}
	
	/**
	 * Runs the business logic.
	 */
	public void run() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(logType + ".log", true));
			writer.append(logMessage);
			writer.newLine();
			writer.close();
		} catch (Exception e) {
			System.out.println("Thread " +  logType + "log interrupted.");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Initializes the thread instance.
	 */
	public void start () {

		if (thread == null) {
		  	thread = new Thread (this, logType + "log");
		  	thread.start();
		}
	}
}
