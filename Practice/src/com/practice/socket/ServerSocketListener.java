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
				JOptionPane.showMessageDialog(null, "�пͻ������ӵ��˱�����12345�˿�");
//				new ChatSocket(socket).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
