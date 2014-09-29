/**
 * 
 */
package com.techidiocy.amq.ehr.queue.listeners;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.techidiocy.amq.ehr.queue.exceptions.EHRQueueException;

/**
 * 
 * This is the base listener comes as a default for all the consumers.
 * All the external applications need to extend this class and have 
 * to override {@link #onMessage(Message)} to provide the functionality that 
 * they want once the message is consumed by the consumer.
 * 
 * @author saurabhj
 */
public class EHRBaseListener implements MessageListener {

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) throws EHRQueueException{
		TextMessage textMessage = (TextMessage)message;
		//TODO logger replacement
		try {
			System.out.println("Consumed Message ---->"+textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
			throw new EHRQueueException("Failed in the default implementation of MessageListener");
		}
	}

}
