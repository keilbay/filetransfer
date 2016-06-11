/**
 * 
 */
package com.ociv.filetranfer.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Frank
 *
 */
public class ClientRequestDispatcher implements Runnable {
	
    protected Socket clientSocket = null;
    protected String serverText   = null;
    
    private static final Log log = LogFactory.getLog(ClientRequestDispatcher.class);
    
    public ClientRequestDispatcher( Socket clientSocket, String serverText ) {
    	    	
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
        try {
        	
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + 
            		this.serverText + " - " + time + "").getBytes());
            output.close();
            input.close();
            log.info("Request processed: " + time);
            
        } 
        catch (IOException e) {
            log.error(e);
        }
	}

}
