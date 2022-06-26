package com.salesforce;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class DataReceiver2 {
    Session session ;
    MessageConsumer messageConsumer ;
    public DataReceiver2(Session session , MessageConsumer messageConsumer){
        this.session = session ;
        this.messageConsumer = messageConsumer ;
    }

    public void ListenMessages() {
        QueueMessageListener queueMessageListener = new QueueMessageListener("queue1") ;
        try {
            messageConsumer.setMessageListener(queueMessageListener);
        }
        catch (Exception exp){
            exp.printStackTrace();

        }
    }


}
