package com.lyf;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConf {

  @Bean
  public Queue queue() {
    return new Queue("queue");
  }

  @Bean(name = "message")
  public Queue queueMessage() {
    return new Queue("topic.message");
  }

  @Bean(name = "messages")
  public Queue queueMessages() {
    return new Queue("topic.messages");
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange("exchange");
  }

  @Bean
  Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
    return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
  }

  @Bean
  Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
    return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    //*表示一个词,#表示零个或多个词
    /***
     * 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
     * 因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。
     * */
  }

  @Bean(name="Amessage")
  public Queue AMessage() {
    return new Queue("fanout.A");
  }


  @Bean(name="Bmessage")
  public Queue BMessage() {
    return new Queue("fanout.B");
  }

  @Bean(name="Cmessage")
  public Queue CMessage() {
    return new Queue("fanout.C");
  }

  @Bean
  FanoutExchange fanoutExchange() {
    return new FanoutExchange("fanoutExchange");//配置广播路由器
  }

  @Bean
  Binding bindingExchangeA(@Qualifier("Amessage") Queue AMessage,FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(AMessage).to(fanoutExchange);
  }

  @Bean
  Binding bindingExchangeB(@Qualifier("Bmessage") Queue BMessage, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(BMessage).to(fanoutExchange);
  }

  @Bean
  Binding bindingExchangeC(@Qualifier("Cmessage") Queue CMessage, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(CMessage).to(fanoutExchange);
  }
}