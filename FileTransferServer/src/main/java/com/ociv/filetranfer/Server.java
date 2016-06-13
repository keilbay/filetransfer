/**
 * 
 */
package com.ociv.filetranfer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ociv.filetranfer.request.ClientRequestDispatcher;

/**
 * @author Frank
 */
public class Server implements Runnable {
	
	private static final Log log = LogFactory.getLog(Server.class);
	
	private int socketport;
	
	/*
	 * used only for testing
	 */
	private int socketsleep;
	
	private ServerSocket servsock;
	
	protected boolean isStopped = false;	

	public int getSocketport() {
		
		log.info("get socketport " + socketport);
		
		return socketport;
	}

	public void setSocketport(int socketport) {
		
		log.info("set socketport to " + socketport);
		
		this.socketport = socketport;
	}

	public void run() {
		
		if( openServerSocket() ) {
			
			Socket sock = null;
			
			while(! isStopped()){
				
				try {					
					sock = servsock.accept();
					log.info("Accepted connection : " + sock);
				} 
				catch (IOException e) {
					
					if(isStopped()) {
						log.info("Server Stopped.") ;
	                    return;
	                }
					
					log.error("Error accepting client connection", e);
	                throw new RuntimeException("Error accepting client connection", e);
				}
				
		    	try {
		    	    Thread.sleep( getSocketsleep() );
		    	} catch (InterruptedException e) {
		    	    e.printStackTrace();  
		    	}
				
				new Thread( new ClientRequestDispatcher( sock, "Multithreaded Server")).start();
	                        
			}
			
			log.info("Server Stopped.") ;
		}
	}
	
    public synchronized void stop() {
    	
        this.isStopped = true;        
        try {
        	servsock.close();
        } 
        catch (IOException e) {        	
        	log.error("Error closing server", e);
        }
    }
	
	private synchronized boolean isStopped() {
        return this.isStopped;
    }

	private boolean openServerSocket() {
		
        try {
            this.servsock = new ServerSocket(socketport);
        } catch (IOException e) {
        	log.error("Cannot open port " + socketport, e);
        }
        
        return true;
	}

	/**
	 * @return the socketsleep
	 */
	public int getSocketsleep() {
		return socketsleep;
	}

	/**
	 * @param socketsleep the socketsleep to set
	 */
	public void setSocketsleep(int socketsleep) {
		this.socketsleep = socketsleep;
	}
}
