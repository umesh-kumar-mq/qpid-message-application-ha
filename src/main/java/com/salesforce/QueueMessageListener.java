package com.salesforce;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueMessageListener implements MessageListener {
        private String QueueName;

        public QueueMessageListener(String QueueName) {
            this.QueueName = QueueName;
        }

        public void onMessage(Message message) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(QueueName + " received : " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
}
