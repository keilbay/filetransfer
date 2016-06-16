/**
 * 
 */
package com.ociv.filetranfer.handler;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Frank
 * @param <T>
 * 
 */
public abstract class SocketHandlerInterface {
			
	public abstract void send(Socket clientSocket, Object toSend) throws IOException;
	
	public abstract Object receive(Socket serverSocket) throws IOException;

}
