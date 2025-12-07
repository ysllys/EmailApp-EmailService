package com.emailapp.emailservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /**
    private static final String USER_PW = "guest";
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();

        factory.setUsername(USER_PW);
        factory.setPassword(USER_PW);

        return factory;
    } */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("emailExchange", true, false);
    }
    @Bean
    Queue queue() {
        return new Queue("emailQueue", true);
    }
    @Bean
    Binding bindQueue(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("demo");
    }
    /**
    @Bean
    MessageListenerContainer messageListenerContainer(RabbitListener rabbitListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueues(queue());
        container.setMessageListener(rabbitListener);

        return container;
    }
     @Bean
     public JavaMailSender getJavaMailSender() {
     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

     Properties props = mailSender.getJavaMailProperties();
     props.put("mail.transport.protocol", "smtp");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.debug", "true");

     return mailSender;
     } */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public MessageConverter messageConverter() {
        // Necessary for Spring to convert JSON messages into your EmailRequest object
        return new Jackson2JsonMessageConverter();
    }
}
