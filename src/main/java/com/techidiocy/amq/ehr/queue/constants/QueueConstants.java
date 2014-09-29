/**
 * 
 */
package com.techidiocy.amq.ehr.queue.constants;

import org.apache.activemq.ActiveMQConnection;

/**
 * @author saurabhj
 *
 */
public class QueueConstants {

	   public static final String URL              = ActiveMQConnection.DEFAULT_BROKER_URL;
	   public static final String USER_NAME        = "admin";
	   public static final String PASSWORD         = "admin";
	   public static final String QUEUE_1_NAME     = "queue1";
	   public static final String QUEUE_2_NAME     = "queue2";
}
