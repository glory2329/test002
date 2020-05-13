package com.glory.demo.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * author : glory
 * date : 2019/12/25 18:12
 * description : RabbitMQ  直连交换机配置
 */
@Configuration
public class DirectRabbitConfig {

    //Direct交换机(路由)名称
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("justin.exchange");
    }


    //队列名称
    @Bean
    public Queue TestDirectQueue() {
        return new Queue("justin.queue",true);  //true 是否持久
    }


    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("justin.queue");
    }
}
