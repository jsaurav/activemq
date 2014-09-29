/**
 * 
 */
package com.techidiocy.amq.ehr.queue.producers;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.techidiocy.amq.ehr.queue.constants.QueueConstants;
import com.techidiocy.amq.ehr.queue.exceptions.EHRQueueException;

/**
 * @author saurabhj
 * Message Producer for the Queue
 */
public class EHRMessageProducer<T> {
	
   private ConnectionFactory connectionFactory = null;
   private Connection connection = null;
   private Session session  = null;	
   private Destination destination = null;
   private String queueName;
	
   /**
    * Constructor for initializing the queueName and connectionFactory.
    * @param queueName
    * @param connectionFactory
    */
   public EHRMessageProducer(String queueName, ConnectionFactory connectionFactory) {
	   this.connectionFactory = connectionFactory;
	   this.queueName = queueName;
   }

   /**
    * Wrapper over the actual sendMessage implementation.
    * @param message
    * @throws EHRQueueException
    */
   public void sendMessage(T message) throws EHRQueueException {	 
	   try {
		   connection = connectionFactory.createConnection();	   
		   connection.start();		   
		   session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);		   
		   destination = session.createQueue(queueName);		   
		   MessageProducer producer = session.createProducer(destination);		   
		   TextMessage textMessage = session.createTextMessage(message.toString());		   
		   producer.send(textMessage);		   	   
		   connection.close();
	   } catch(JMSException jms) {
		   jms.printStackTrace();
		   throw new EHRQueueException("Failed while sending message to the queue");
	   }
   }   
}
