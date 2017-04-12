package com.practice.socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread {
	
	private Socket socket;
	
	public ChatSocket(Socket s){
		this.socket = s;
	}
	
	public void getMessage(String message){
		try {
			socket.getOutputStream().write(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("断开了一个客户端链接");
//			Cha
			e.printStackTrace();
		}
	}
	
}
