/**
 * 
 */
package com.techidiocy.amq.ehr.queue.listeners.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.techidiocy.amq.ehr.queue.exceptions.EHRQueueException;
import com.techidiocy.amq.ehr.queue.listeners.EHRBaseListener;

/**
 * @author saurabhj
 *
 */
public class TestEHRBaseListener extends EHRBaseListener{

	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		//TODO logger replacement
		try {
			System.out.println("Consumed Message from Test Listener---->"+textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
			throw new EHRQueueException("Failed in the default implementation of MessageListener");
		}
	}
}
