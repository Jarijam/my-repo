package socket_echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Date;


public class MyEchoServerExam {
	public static void main(String[] args) {
		Socket client = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("서버 접속 완료!!");
			while(true) {
				client = server.accept();
				InetAddress clientIp = client.getInetAddress();
				
				//네트워크를 통해서 입출력을 하기 위한 IO스트림객체를 생성
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream(),true);
				
				//=====================<<< 통신 시작>>>====================
				out.println("안녕하세요 클라이언트("+clientIp.getHostAddress()+")님 ");
				//클라이언트가 보내오는 메시지를 다시 클라이언트에게 전송
				String resMsg = ""; //클라이언트가 보내는 메시지 저장
				//LocalDate now = LocalDate.now();
				
				while(true) {
					//1.서버 <- 클라이언트
					resMsg = in.readLine();
					if(resMsg==null) {
						break;
					}else if(resMsg.startsWith("안녕하세요?") | resMsg.startsWith("하이")) {
						out.println(clientIp.getHostAddress()+"님 반가워요");
					}else if(resMsg.startsWith("오늘 날짜는")) {
						out.println(new Date().toString());
					}else {
						out.println(clientIp.getHostAddress()+"님 어여 들어가~");
					}
					
					
					System.out.println("클라이언트가 보낸 메시지>>"+resMsg);
					//2. 서버 -> 클라이언트
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
