package com.practice.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerSocketListener extends Thread {

	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(12345);
			while(true){
				Socket socket = ss.accept();
				JOptionPane.showMessageDialog(null, "有客户端连接到了本机的12345端口");
//				new ChatSocket(socket).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
