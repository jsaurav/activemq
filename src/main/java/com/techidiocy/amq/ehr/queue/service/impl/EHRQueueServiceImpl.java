/**
 * 
 */
package com.techidiocy.amq.ehr.queue.service.impl;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.techidiocy.amq.ehr.queue.consumers.EHRMessageConsumer;
import com.techidiocy.amq.ehr.queue.exceptions.EHRQueueException;
import com.techidiocy.amq.ehr.queue.listeners.EHRBaseListener;
import com.techidiocy.amq.ehr.queue.producers.EHRMessageProducer;
import com.techidiocy.amq.ehr.queue.service.IEHRQueueService;

/**
 * 
 * Implementation class for the <code>com.techidiocy.amq.ehr.queue.service.IEHRQueueService</code>
 * 
 * @author saurabhj
 * @param <T>
 *
 */
public class EHRQueueServiceImpl<T> implements IEHRQueueService<T> {
	
	/*Implementation for the message producer class*/
	private EHRMessageProducer<T> ehrMessageProducer;
	private EHRMessageConsumer<T> ehrMessageConsumer;
	private ConnectionFactory connectionFactory;
	
    /**
     * An overloaded constructor that will take brokerURL,queueName and listener class 
     * implementation as an argument. 
     * @param brokerURL
     * @param queueName
     * @param ehrBaseListener
     * @throws EHRQueueException
     */
	public EHRQueueServiceImpl(String brokerURL,String queueName,EHRBaseListener ehrBaseListener) throws EHRQueueException {		
		connectionFactory = createConnectionFactory(brokerURL);
		ehrMessageProducer = new EHRMessageProducer<T>(queueName,connectionFactory);
		ehrMessageConsumer = new EHRMessageConsumer<T>(queueName,connectionFactory,ehrBaseListener);
		try {
			ehrMessageConsumer.consume();
		} catch (JMSException e) {
			e.printStackTrace();
			throw new EHRQueueException("Failed in the constructor of the EHRQueueServiceImpl");
		}
	}
	
	/**
	 * Creates a new ActiveMQConnectionFactory instance
	 * @param brokerURL2
	 * @return
	 */
	private ConnectionFactory createConnectionFactory(String brokerURL2) {
		return new ActiveMQConnectionFactory(brokerURL2);
	}

	/**
	 * Implementation for the {@link com.techidiocy.amq.ehr.queue.service.IEHRQueueService#sendMessage(Object)}
	 * @param message
	 * @throws EHRQueueException
	 */
	public void sendMessage(T message) throws EHRQueueException {
		ehrMessageProducer.sendMessage(message);
	}

	/**
	 * Direct invocation of consume will throw an exception.
	 * Clients of this application must extend {@link EHRBaseListener}
	 * and override {@link EHRBaseListener#onMessage(javax.jms.Message)}.
	 * In the overriden version of this method client can write the logic 
	 * whatever they want once the message is consumed from the queue.
	 * 
	 * 
	 */
	public void consumeMessage() throws EHRQueueException {
		throw new EHRQueueException("Can't be invoked.");
	}

}
