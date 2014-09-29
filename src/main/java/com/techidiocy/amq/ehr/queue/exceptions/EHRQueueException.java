/**
 * 
 */
package com.techidiocy.amq.ehr.queue.exceptions;

/**
 * @author saurabhj
 *
 * EHR application specific class that will be used to handle exceptions
 * raised in the application.
 */
public class EHRQueueException extends RuntimeException {

	/**
	 * 
	 */
	public EHRQueueException() {}

	/**
	 * @param message
	 */
	public EHRQueueException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public EHRQueueException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EHRQueueException(String message, Throwable cause) {
		super(message, cause);
	}

}
