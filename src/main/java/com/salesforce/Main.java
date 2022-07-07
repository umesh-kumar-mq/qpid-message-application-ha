package com.salesforce;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws JMSException, NamingException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("/com/salesforce/app.xml") ;

        CreateConnection cr = new CreateConnection() ;
        cr.refreshConnection();

        Context cxt = cr.getContext() ;

        DataSender ds = new DataSender() ;

        int numberOfMessagesToSend = 100 ;

        System.out.println(java.time.LocalTime.now());
        for(int i=0 ; i<numberOfMessagesToSend ; i++) {
            ds.runTest(cr.getSession() , cxt);
        }
    }
}












//        obj = (Clint) context.getBean("Clint2") ;
//        obj.runTest();




//        DataSender sender = new DataSender();
//        sender.runTest();
//        DataReceiver receiver = new DataReceiver();
//        receiver.runTest() ;

//producer & Consumer applications
// producer will produce messgaes
//Consumer will consume -> keep this allways running
//Queue & queue listners





//broker_host=localhost
//        broker_port_http=8080
//        broker_port=5671
//        username=guest
//        password=guest
//        broker_ssl_port=5672
//        path_to_qpid_sources=/Users/umesh.kumar/Downloads/qpid-broker-j-main

//
//
//mvn dependency:tree integration-test -f ${path_to_qpid_sources}/perftests/pom.xml -DskipTests -Dperftests=qpid-jms-client -Dperftests.management-url=http://${broker_host}:${broker_port_http} -Dpertests.management-user=${username} -Dperftests.management-password=${password} -Dperftests.messaging-hostport-plain=${broker_port} -Dperftests.messaging-hostport-plain=${broker_ssl_port} -Dperftests.messaging-user=${username} -Dperftests.messaging-password=${password} -Dperftests.hillclimb=true

// mvn  -f /Users/umesh.kumar/Downloads/qpid-broker-j-main/perftests/pom.xml integration-test -Dperftests=jms-client-0-9

// mvn dependency:tree integration-test -f ${path_to_qpid_sources}/perftests/pom.xml -DskipTests -Dperftests=qpid-jms-client -Dperftests.hillclimb=true
// longest run