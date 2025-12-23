package com.ecommerce.notification;


import com.ecommerce.notification.payload.OrderCreatedEvent;
import com.ecommerce.notification.payload.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderEventConsumer {

    //    @RabbitListener(queues = "${rabbitmq.queue.name}")
//    public void handleOrderEvent(Map<String, Object> orderEvent) {
//
//        System.out.println("Received order event: " + orderEvent);
//        Long orderId = Long.parseLong(orderEvent.get("orderId").toString());
//        String status = orderEvent.get("status").toString();
//
//        System.out.println("Order Status: " + status);
//        System.out.println("Order ID: " + orderId);
//    }
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleOrderEvent(OrderCreatedEvent orderEvent) {

        System.out.println("Received order event: " + orderEvent);
        Long orderId = orderEvent.getId();
        OrderStatus status = orderEvent.getStatus();

        System.out.println("Order Status: " + status);
        System.out.println("Order ID: " + orderId);
    }

    //Update DB
    //Send Notification
    //Send Emails
    //Send Invoice
    //Send Seller Notification

}
