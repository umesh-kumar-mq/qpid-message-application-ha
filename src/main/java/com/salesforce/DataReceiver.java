package com.salesforce;

import javax.jms.*;
import javax.naming.Context;


public class DataReceiver implements Clint
{
    public static void main(String args[]) {
        DataReceiver dataReceiver = new DataReceiver();
        dataReceiver.runTest() ;
    }
    public void runTest()
    {
        try
        {
            CreateConnection cr = new CreateConnection() ;

//            Connection connection = cr.getConnection() ;
//
            Context context = cr.getContext() ;
//
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Session session = cr.getSession() ;

            Destination destination = (Destination) context.lookup("queue1");

            MessageConsumer messageConsumer = session.createConsumer(destination);

            while (true) {
                QueueMessageListener queueMessageListener = new QueueMessageListener("queue1") ;
                messageConsumer.setMessageListener(queueMessageListener);
            }

//            int no_of_messages = 10 ;

//            for(int i=0 ; i<no_of_messages ; i++) {
//                QueueMessageListener queueMessageListener = new QueueMessageListener("queue1") ;
//                messageConsumer.setMessageListener(queueMessageListener);
//
//                //System.out.println(ms.getText());
//                i-- ;
//            }

//            connection.close();
//            context.close();
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
}
