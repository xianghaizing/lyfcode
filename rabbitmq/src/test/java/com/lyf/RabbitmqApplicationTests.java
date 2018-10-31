package com.lyf;

import com.lyf.queue.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {
	
	@Autowired
	private HelloSender helloSender;
	@Autowired
	private AmqpTemplate template;
	
	@Test
	public void testRabbit() {
		helloSender.send();
	}

	@Test
	public void testRabbit02() {
		helloSender.sendObj();
	}
	

	@Test
	public void testRabbit03() {
		template.convertAndSend("exchange","topic.message","hello,rabbit~~~11");
		template.convertAndSend("exchange","topic.messages","hello,rabbit~~~22");
	}

	@Test
	public void testRabbit04() {
		template.convertAndSend("fanoutExchange","","xixi,haha");//参数2忽略
	}
	
	
}
