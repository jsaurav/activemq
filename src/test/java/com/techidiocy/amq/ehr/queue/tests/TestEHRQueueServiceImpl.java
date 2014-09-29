/**
 * 
 */
package com.techidiocy.amq.ehr.queue.tests;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import com.techidiocy.amq.ehr.queue.constants.QueueConstants;
import com.techidiocy.amq.ehr.queue.listeners.test.TestEHRBaseListener;
import com.techidiocy.amq.ehr.queue.service.IEHRQueueService;
import com.techidiocy.amq.ehr.queue.service.impl.EHRQueueServiceImpl;

/**
 * @author saurabhj
 *
 */
public class TestEHRQueueServiceImpl {

	private static IEHRQueueService<String> ehrQueueService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ehrQueueService = new EHRQueueServiceImpl<String>(QueueConstants.URL,"dummy2",
				new TestEHRBaseListener());
	}

	@Test
	public void test() {		
		int i=0;
		while(true) {
			//ehrQueueService.sendMessage("Good Bye World "+i);
			//i++;
		}
	}

}
