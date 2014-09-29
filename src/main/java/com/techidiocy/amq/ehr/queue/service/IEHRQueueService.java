/**
 * 
 */
package com.techidiocy.amq.ehr.queue.service;

import com.techidiocy.amq.ehr.queue.exceptions.EHRQueueException;

/**
 * @author saurabhj
 * 
 * Interface exposed to the external clients.
 * It provides the core functionality to interact with a queue.
 * A client can send and consume message to and from a queue.
 *
 */
public interface IEHRQueueService<T> {

	/**
	 * It will be used to send a message to a queue.
	 * @param message
	 * @throws EHRQueueException
	 */
	public void sendMessage(T message) throws EHRQueueException;
	
	/**
	 * Consumes the message from the queue and returns it.
	 * Actual implementation for consuming the message is controlled
	 * by overriding the {@link com.techidiocy.amq.ehr.queue.listeners.EHRBaseListener#onMessage(javax.jms.Message)}
	 * @return
	 * @throws EHRQueueException
	 */
	public void consumeMessage() throws EHRQueueException;
}
