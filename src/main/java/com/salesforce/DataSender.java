package com.salesforce;

import org.apache.qpid.client.AMQAnyDestination;

import javax.jms.*;
import javax.naming.Context;

public class DataSender
{
    public void runTest(Session session , Context context) throws JMSException {

        try
        {
            Destination dest = (Destination) context.lookup("queue1");

            Destination destination = new AMQAnyDestination(dest.toString());

            MessageProducer messageProducer = session.createProducer(destination);

            int no_of_messages = 2 ;

            for(int i=0 ; i<no_of_messages ; i++) {
                String s = "This is a test message number " + i ;
                TextMessage message = session.createTextMessage(s);
                System.out.println("Message Sent");
                messageProducer.send(message);
            }
        }
        catch (Exception exp)
        {
//            exp.printStackTrace();
            System.out.println(java.time.LocalTime.now());

            System.out.println( exp.getMessage() ) ;

            CreateConnection cr = new CreateConnection() ;
            cr.refreshConnection();
        }
    }
}
