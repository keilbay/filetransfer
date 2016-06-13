/**
 * 
 */
package com.ociv.filetransfer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ociv.filetranfer.Server;

/**
 * @author Frank
 *
 */
public class ServerTest {
	
	private Server server;
	
	@Before 
	public void setUp() {
		
    	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    	
    	server = (Server) context.getBean("FileTransferServer");	
    	
    	new Thread(server).start();
	}
	
	@After
	public void close(){
		
		server.stop();
	}


	/**
	 * Test method for {@link com.ociv.filetranfer.Server#run()}.
	 */
	@Test
	public final void testRun() {
		
		try {
			Socket sock = new Socket("127.0.0.1", server.getSocketport());
			
			Assert.assertEquals( "is Connected", true ,sock.isConnected());
			
		} catch (UnknownHostException e) {
			
			fail("Unknown Host Exception");
			
		} catch (IOException e) {
			
			fail("Connection refused");
		}
	}

	/**
	 * Test method for {@link com.ociv.filetranfer.Server#stop()}.
	 *
	@Test
	public final void testStop() {
		
		//server.stop();
		
		//Assert.assertEquals( "is Connected", true ,sock.isConnected());

	}*/

}
