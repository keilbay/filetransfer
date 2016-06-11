package com.ociv.filetranfer;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class ServerApplication
{
	private static final Log log = LogFactory.getLog(ServerApplication.class);
	
    public static void main( String[] args ) throws IOException
    {    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    	
    	log.info("Server ApplicationContext created");
    	
    	Server server = (Server) context.getBean("FileTransferServer");	
    	
    	new Thread(server).start();
    	
    	try {
    	    Thread.sleep(1000 * 1000);
    	} catch (InterruptedException e) {
    	    e.printStackTrace();  
    	}
    	
    	server.stop();
    	
    }
}
