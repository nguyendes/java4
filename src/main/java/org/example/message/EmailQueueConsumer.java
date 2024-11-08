package org.example.message;

import com.rabbitmq.client.*;

public class EmailQueueConsumer {
    private final static String QUEUE_NAME = "email_queue";

    public void receiveEmails() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Waiting for messages...");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                String[] parts = message.split(";");
                String email = parts[0];
                String subject = parts[1];
                String body = parts[2];

                // Gửi email
                EmailSender emailSender = new EmailSender();
                emailSender.sendEmail(email, subject, body);
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        }
    }
}

