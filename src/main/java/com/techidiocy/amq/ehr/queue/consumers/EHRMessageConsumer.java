/**
 * 
 */
package com.techidiocy.amq.ehr.queue.consumers;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.techidiocy.amq.ehr.queue.constants.QueueConstants;
import com.techidiocy.amq.ehr.queue.listeners.EHRBaseListener;
import com.techidiocy.amq.ehr.queue.producers.Queue2Producer;

/**
 * @author saurabhj Consumer for the Queue 1.
 */
public class EHRMessageConsumer<T> {

	private ConnectionFactory connectionFactory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private String queueName;
	private EHRBaseListener ehrBaseListener;

	public EHRMessageConsumer(String queueName,	ConnectionFactory connectionFactory, EHRBaseListener ehrBaseListener) {
		this.connectionFactory = connectionFactory;
		this.queueName = queueName;
		this.ehrBaseListener = ehrBaseListener;
	}

	public void consume() throws JMSException {
		
		connection = connectionFactory.createConnection();

		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		destination = session.createQueue(queueName);
		
		MessageConsumer consumer = session.createConsumer(destination);
		
		consumer.setMessageListener(ehrBaseListener);
		
		connection.start();
	}

	/**
	 * @param args
	 * @throws JMSException 
	 * TODO Migrate it to the unit test case
	 */
	/*public static void main(String[] args) throws JMSException {
		EHRMessageConsumer consumer = new EHRMessageConsumer();
		Queue2Producer queue2Producer = new Queue2Producer();
		while(true) {
			Message message = consumer.consume();
			TextMessage textMessage = (TextMessage)message;
			System.out.println("Consumed "+textMessage.getText());
			queue2Producer.sendMessage(textMessage);
		}
	}*/

}
