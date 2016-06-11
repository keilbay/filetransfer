package com.ociv.filetranfer;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class ClientApplication 
{
    private static final Log log = LogFactory.getLog(ClientApplication.class);

	public static void main( String[] args ) throws UnknownHostException, IOException
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    	
    	log.info("Client ApplicationContext created");
    	
    	Client client = (Client) context.getBean("FileTransferClient");	
    	
    	client.run();
    }
}
