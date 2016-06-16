/**
 * 
 */
package com.ociv.filetranfer;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ociv.filetranfer.handler.SocketHandlerInterface;

/**
 * @author Frank
 *
 */
public class Client {
	
	private static final Log log = LogFactory.getLog(Client.class);
	
	private int socketport;
	
	private String server;

	private Socket serverSocket;

	private SocketHandlerInterface handler;	

	public void run() throws IOException {
		
		log.info("Run Client");
		
		try {
			serverSocket = new Socket(server, socketport);
			
			log.info("Connected to Server " + server);
			
			File f = (File) getHandler().receive(serverSocket);
			
			/*
			byte [] mybytearray  = new byte [FILE_SIZE];
			is = serverSocket.getInputStream();
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			
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
		    
		    log.info("File " + file + " downloaded (" + current + " bytes read)");
		    */
			
			
		} catch (UnknownHostException e) {
			log.error("Can't connect to Server " + server, e);
		}
		/*catch (IOException e) {
			log.error("Problem while reading " + file, e);
		}*/
	    finally {
	    	
	        //if (fos != null) fos.close();
	        //if (bos != null) bos.close();
	        if (serverSocket != null) serverSocket.close();
	    }

	}

	/**
	 * @return the socketport
	 */
	public int getSocketport() {
		return socketport;
	}

	/**
	 * @param socketport the socketport to set
	 */
	public void setSocketport(int socketport) {
		this.socketport = socketport;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}	
	
	public SocketHandlerInterface getHandler() {
		return handler;
	}

	public void setHandler(SocketHandlerInterface handler) {
		this.handler = handler;
	}
}
