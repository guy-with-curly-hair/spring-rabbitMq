package com.dailycodebuffer.springrabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@Component
public class MessageConsumer {

@RabbitListener(queues = "message_queue" )
    public void consumerMessage(  CustomMessage customMessage)
    {
        System.out.println(customMessage.getMessage());

    }
}
