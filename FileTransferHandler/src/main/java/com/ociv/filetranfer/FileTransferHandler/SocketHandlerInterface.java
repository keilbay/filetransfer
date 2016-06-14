/**
 * 
 */
package com.ociv.filetranfer.FileTransferHandler;

import java.net.Socket;

/**
 * @author Frank
 * @param <T>
 * 
 */
public abstract class SocketHandlerInterface<T> {
			
	public abstract void send(Socket clientSocket, T toSend);
	
	public abstract T receive(Socket serverSocket);

}
