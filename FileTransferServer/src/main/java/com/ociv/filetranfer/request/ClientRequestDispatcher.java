/**
 * 
 */
package com.ociv.filetranfer.request;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ociv.filetranfer.handler.SocketHandlerInterface;


/**
 * @author Frank
 *
 */
public class ClientRequestDispatcher implements Runnable {
	
    protected Socket clientSocket = null;
    protected SocketHandlerInterface handler = null;
    
    private static final Log log = LogFactory.getLog(ClientRequestDispatcher.class);
    
    public ClientRequestDispatcher( Socket clientSocket, SocketHandlerInterface socketHandler ) {
    	    	
        this.clientSocket = clientSocket;
        this.handler = socketHandler;
    }

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
        try {
        	
        	handler.send(clientSocket, new File("test.txt"));
        	//log.info("Request processed: " + time);            
        } 
        catch (IOException e) {
            log.error(e);
        }
	}

}
