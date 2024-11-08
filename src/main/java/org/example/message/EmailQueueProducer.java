package org.example.message;

import com.rabbitmq.client.*;

public class EmailQueueProducer {
    private final static String QUEUE_NAME = "email_queue";

    public void sendEmailToQueue(String email, String subject, String body) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = email + ";" + subject + ";" + body;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("Sent to queue: " + message);
        }
    }
}

