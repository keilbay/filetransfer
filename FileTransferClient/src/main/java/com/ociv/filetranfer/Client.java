/**
 * 
 */
package com.ociv.filetranfer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Frank
 *
 */
public class Client {
	
	private static final Log log = LogFactory.getLog(Client.class);
	
	private int socketport;
	
	private String server;

	private Socket sock;	
	
	private String file = "src/main/resources/download.txt";
	
	private InputStream is;
	
	private FileOutputStream fos;
	
	BufferedOutputStream bos;
	
	/*
	 * file size temporary hard coded
	 * should bigger than the file to be downloaded
	 */
	public final static int FILE_SIZE = 6022386;

	public void run() throws IOException {
		
		log.info("Run Client");
		
		try {
			sock = new Socket(server, socketport);
			
			log.info("Connected to Server " + server);
			
			byte [] mybytearray  = new byte [FILE_SIZE];
			is = sock.getInputStream();
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
			
			
		} catch (UnknownHostException e) {
			log.error("Can't connect to Server " + server, e);
		} catch (IOException e) {
			log.error("Problem while reading " + file, e);
		}
	    finally {
	    	
	        if (fos != null) fos.close();
	        if (bos != null) bos.close();
	        if (sock != null) sock.close();
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

}
