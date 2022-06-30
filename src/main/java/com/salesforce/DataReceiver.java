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

            Context context = cr.getContext() ;

            Session session = cr.getSession() ;

            Destination destination = (Destination) context.lookup("queue1");

            MessageConsumer messageConsumer = session.createConsumer(destination);

            while (true) {
                QueueMessageListener queueMessageListener = new QueueMessageListener("queue1") ;
                messageConsumer.setMessageListener(queueMessageListener);
            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
}
