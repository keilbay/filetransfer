/**
 * 
 */
package com.ociv.filetranfer.handler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Gaby
 *
 */
public class SocketHandlerImpl extends SocketHandlerInterface {
		
	private static final Log log = LogFactory.getLog(SocketHandlerImpl.class);
	
	/*
	 * file size temporary hard coded
	 * should bigger than the file to be downloaded
	 */
	public final static int FILE_SIZE = 6022386;	
	
	private InputStream is;
	
	private FileOutputStream fos;
	
	BufferedOutputStream bos;	
	
	private String file = "src/main/resources/download.txt";

	@Override
	public void send(Socket clientSocket, Object toSend) throws IOException {
		
        InputStream input  = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();
        output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + 
        		+ time + "").getBytes());
        
        output.close();
        input.close();		
	}

	@Override
	public File receive(Socket serverSocket) throws IOException {
		
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
	    
		return new File(file);
	}
}
