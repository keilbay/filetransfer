/**
 * 
 */
package com.ociv.filetransfer;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		
    	ApplicationContext context = new ClassPathXmlApplicationContext("Beans_Test.xml");
    	
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
			Socket sock1 = new Socket("127.0.0.1", server.getSocketport());			
			Assert.assertEquals( "is Connected", true ,sock1.isConnected());			
			
		} catch (UnknownHostException e) {
			
			fail("Unknown Host Exception");
			
		} catch (IOException e) {
			
			fail("Connection refused");
		}
	}

	/**
	 * 	 * @param sock
	 * @param file
	 * @throws IOException
	 */
	private void read(Socket sock, String file) throws IOException {
		
		byte [] mybytearray  = new byte [6022386];
		
		InputStream is = sock.getInputStream();
			
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		int bytesRead = is.read(mybytearray,0,mybytearray.length);
		int current = bytesRead;
		
	    do {
	    	bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
	    	
	    	if(bytesRead >= 0) 
	    		current += bytesRead;
	     }
	    
	    while(bytesRead > -1);
	    
	    bos.write(mybytearray, 0 , current);
	    bos.flush();		
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
