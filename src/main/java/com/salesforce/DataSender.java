package com.salesforce;

import org.apache.qpid.client.AMQAnyDestination;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// implements client
public class DataSender
{
    public void runTest(Session session) throws JMSException, NamingException {
        //Connection connection = null ;
        Context context = null;
        try
        {
//            connection = cr.getConnection() ;

//            context = cr.getContext() ;
//            Destination destination = (Destination) context.lookup("topicExchange");
            Destination destination = new AMQAnyDestination("ADDR:ha_q1; {create: always}");

            MessageProducer messageProducer = session.createProducer(destination);

            int no_of_messages = 2 ;

            for(int i=0 ; i<no_of_messages ; i++) {

                String s = "This is a test message number " + i ;
                TextMessage message = session.createTextMessage(s);
                System.out.println("Message Sent");
                messageProducer.send(message);

            }

           // connection.close();
          //  context.close();
        }
        catch (Exception exp)
        {
//            exp.printStackTrace();

//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:");
//            LocalDateTime now = LocalDateTime.now();
//            System.out.println(dtf.format(now));
            System.out.println(java.time.LocalTime.now());

            System.out.println( exp.getMessage() ) ;

            CreateConnection cr = new CreateConnection() ;
            cr.refreshConnection();
        }
    }
}
