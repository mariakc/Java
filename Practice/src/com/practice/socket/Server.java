package com.practice.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private List<ServerThread> clients = null;

	public static void main(String[] args) {
		/*try {
			ServerSocket ss = new ServerSocket(12345);
			Socket socket = ss.accept();
			JOptionPane.showMessageDialog(null, "有客户端连接到了本机的12345端口");
//			System.out.println("有客户端连接到了本机的12345端口");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*new ServerSocketListener().start();*/
		
		new Server().startup();
	}

	private void startup() {
		ServerSocket ss = null;
		Socket s = null;
		try {
			ss = new ServerSocket(12345);
			clients = new ArrayList<ServerThread>();
			while(true){
				s = ss.accept();
				ServerThread st = new ServerThread(s);
				st.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ss != null){
					ss.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private class ServerThread extends Thread {
		private Socket socket;
		private BufferedReader br;
		private PrintWriter pw;
		private String name;
		private boolean flag = true;
		
		public ServerThread(Socket s) throws IOException {
			this.socket = s;
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());
			String str = br.readLine();
			name = str + "[" +socket.getInetAddress().getHostAddress()
					+":"+socket.getPort()+"]";
			clients.add(this);
			send(name+"上线了");
		}
		
		private void send(String msg) {
			// TODO Auto-generated method stub
			for(ServerThread st : clients){
				st.pw.println(msg);
			}
		}
		
		private void recieve() throws IOException{
			String str = null;
			while((str=br.readLine()) != null){
				if(str.equalsIgnoreCase("quit")){
					offline();
					pw.println("disconnect");
					break;
				}
				send(name+":"+str);
			}
		}
		
		private void offline(){
			clients.remove(this);
			flag = false;
			send(name+"下线");
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				while(true){
					if(!flag){
						break;
					}
					recieve();
				}
			}catch(SocketException e){
				offline();
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				try{
					if(socket != null){
						socket.close();
					}
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
	}

}
