package com.practice.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket s;
	private BufferedReader br;
	private PrintWriter pw;
	private boolean flag=true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client().startup();
	}
	
	private void startup(){
		BufferedReader sbr = null;
		try{
			s = new Socket("127.0.0.1", 12345);
			pw = new PrintWriter(s.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw.println("张三");
//			pw.println("李四");
			sbr = new BufferedReader(new InputStreamReader(System.in));
			
			new ClientThread().start();
			String str = null;
			while(flag && (str = sbr.readLine()) != null){
				if(!flag){
					break;
				}
				pw.println(str);
			}
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(s!=null)
					s.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				if(sbr!=null)
					s.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private void recieve(){
		try{
			String rs = br.readLine();
			if(rs.equalsIgnoreCase("disconnect")){
				flag = false;
				System.out.println("点击回车退出");
			}
			System.out.println(rs);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private class ClientThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				if(!flag){
					break;
				}
				recieve();
			}
		}
		
	}

}
