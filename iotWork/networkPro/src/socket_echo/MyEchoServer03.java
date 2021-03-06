package socket_echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//서버와 클라이언트가 키보드로 통신하기
public class MyEchoServer03 {
	public static void main(String[] args) {
		Socket client = null;
		BufferedReader in = null; //클라이언트와 통신하기 위한 스트림 객체
		BufferedReader keyin = null; //키보드로 입력하는 메세지를 읽기위한 스트림 객체
		PrintWriter out = null;
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("서버 접속 완료!!");
			while(true) {
				client = server.accept();
				InetAddress clientIp = client.getInetAddress();
				System.out.println("접속한 클라이언트: "+clientIp.getHostAddress());
				//네트워크를 통해서 입출력을 하기 위한 IO스트림객체를 생성
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream(),true);
				keyin = new BufferedReader(new InputStreamReader(System.in));
				
				//=====================<<< 통신 시작>>>====================
				//클라이언트가 보내오는 메시지를 다시 클라이언트에게 전송
				String sendMsg = "";
				String resMsg = ""; //클라이언트가 보내는 메시지 저장
				while(true) {
					//1.서버 <- 클라이언트
					resMsg = in.readLine();
					if(resMsg==null) {
						break;
					}
					System.out.println("클라이언트가 보낸 메시지>>"+resMsg);
					//2. 서버 -> 클라이언트
					sendMsg = keyin.readLine();
					out.println(sendMsg);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
