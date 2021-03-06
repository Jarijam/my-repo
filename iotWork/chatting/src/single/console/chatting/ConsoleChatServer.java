package single.console.chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//서버와 클라이언트가 키보드로 통신하기
public class ConsoleChatServer {
	public static void main(String[] args) {
		Socket client = null;
		InetAddress clientIp = null;
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("서버 접속 완료!!");
			while (true) {
				// 클라이언트끼리 1:1로 통신하도록 구현하려면? 클라이언트 정보를 저장,
				// accept해서 클라이언트의 접속을 대기하는 부분도 쓰레드로 처리

				client = server.accept();
				if (client != null) {
					clientIp = client.getInetAddress();
					System.out.println("접속한 클라이언트: " + clientIp.getHostAddress());

					// 클라이언트가 접속하면 클라이언트와 통신할 수 있는 서버쪽 쓰레드를 생성하고 start시킨다
					ServerReceiveThread receiveThread = new ServerReceiveThread(client);
					receiveThread.start();
					new ServerSenderThread(client).start();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
