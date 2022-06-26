package com.salesforce;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CreateConnection {
    private int noOfConnections = 3 ;
    // Number of sessions should be less than 256 because we cannot have more then 256 sessions inside 1 connection.
    private int noOfSession = 20 ;
    private int messageSession = 0 ;
    private int totalNoOfInstances = 3 ;
    private int currentInstance = 1 ;

    public static List<Session> list = new ArrayList<>();
    public Session getSession() {

        if(list.size() >= 1){
            System.out.println(messageSession+1);
            System.out.println("From pool connection");
            messageSession++ ;
            if(messageSession >= noOfSession*noOfConnections){
                messageSession = 0 ;
            }
            return list.get(messageSession) ;
        }

//        for(int i=1 ; i<=3 ; i++) {
        while(true){
            try {
                Context context = getContext();
                ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("qpidConnectionfactory" + currentInstance);
                list.clear();
                for(int k=0 ; k<noOfConnections ; k++) {
                    Connection connection = connectionFactory.createConnection();
                    connection.start();
                    for (int j = 0; j < noOfSession; j++) {
                        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                        list.add(session);
                    }
                }
                //Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                //list.add(session) ;
                System.out.println(java.time.LocalTime.now());
                System.out.println(list.size());
                System.out.println("Added to pool");
                return list.get(messageSession);
            } catch (Exception exp) {
                //exp.printStackTrace();
//                System.out.println("check1");
               // return null;
            }
            currentInstance++ ;
            if(currentInstance > totalNoOfInstances){
                currentInstance = 1 ;
            }
        }
    }

    public void refreshConnection() {
        list.clear();
        getSession() ;
    }

    public Context getContext() throws IOException, NamingException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("broker.properties") ;

        Properties properties = new Properties();
        properties.load(resourceAsStream);
        Context context = new InitialContext(properties);
        return context ;
    }
}
