/**
 * 
 */
package com.techidiocy.amq.ehr.queue.producers;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.techidiocy.amq.ehr.queue.constants.QueueConstants;

/**
 * @author saurabhj
 *
 */
public class Queue2Producer {
	
	 private ConnectionFactory connectionFactory = null;
	   private Connection connection = null;
	   private Session session  = null;	
	   private Destination destination = null;
		
	   public void sendMessage(String message) throws JMSException {
		   connectionFactory = new ActiveMQConnectionFactory(QueueConstants.URL);
		   
		   connection = connectionFactory.createConnection();
		   
		   connection.start();
		   
		   session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		   
		   destination = session.createQueue("dummy2");
		   
		   MessageProducer producer = session.createProducer(destination);
		   
		   
		   
		   producer.send(session.createTextMessage(message));
		   	   
		   connection.close();
	   }	
	   
	   public static void main(String[] arg) throws JMSException {
		   Queue2Producer queue1Producer = new Queue2Producer();
		   int i=1;
		   while(true && i <=10) {
			   queue1Producer.sendMessage(("Good Bye World "+i));
			   i++;
			}
		  
	   }
}
