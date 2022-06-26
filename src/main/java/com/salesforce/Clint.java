package com.salesforce;

import javax.jms.JMSException;
import javax.naming.NamingException;

public interface Clint {
    void runTest() throws JMSException, NamingException;
}
