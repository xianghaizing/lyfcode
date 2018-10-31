package com.lyf.queue;

import com.lyf.po.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
  @Autowired
  private AmqpTemplate template;

  public void send() {
    template.convertAndSend("queue", "hello,rabbit~");
  }
  
  public void sendObj() {
    User user=new User();    //实现Serializable接口
    user.setUsername("linyufeng");
    user.setPassword("123456");
    template.convertAndSend("queue",user);
  }
  
  
}