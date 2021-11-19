package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//Ŭ���̾�Ʈ�� ����ϱ� ���� ����
//=> Ŭ���̾�Ʈ�� �����ϸ� Ŭ���̾�Ʈ�� ����� �� �ִ� IO��Ʈ�� ��ü�� ���ؼ� Ŭ���̾�Ʈ���� �޽����� ������.
//=> Ŭ���̾�Ʈ�� ������ �޽����� �ް� ������ �޽����� ���� �ֿܼ� ����ϴ� �۾��� ����
// Ŭ���̾�Ʈ�� IO(input -> DataInputStream, output -> DataOutputStream Ȱ��)

public class MyNetServer02 {
	public static void main(String[] args) {
		Socket client = null;
		InputStream socketIn = null; //Ŭ���̾�Ʈ�� �������� �����͸� �б� ���ؼ� ���Ͽ��� �������� ��Ʈ����ü
		OutputStream socketOut = null; //Ŭ���̾�Ʈ���� �����͸� ������ ���ؼ� ��Ĺ���� ��������  ��Ʈ����ü
		DataInputStream dis = null; // Ŭ���̾�Ʈ�� ���� ���� �����͸� Ÿ�Ժ��� �б� ���� ����ϴ� ���� ��Ʈ����ü
		DataOutputStream dos = null; // Ŭ���̾�Ʈ�� ���� �����͸� Ÿ�Ժ��� ������ ���� ����ϴ� ���� ��Ʈ����ü
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("�����غ�Ϸ�....Ŭ���̾�Ʈ�� ������ ��ٸ��ϴ�.");
			//���� Ŭ���̾�Ʈ���� ���񽺸� �����ϱ� ���ؼ� ���ѷ��� ����
			while(true) {
				client = server.accept();
				InetAddress clientIp = client.getInetAddress();
				System.out.println("������ Ŭ���̾�Ʈ : "+clientIp.getHostAddress()); 
				
				//Ŭ���̾�Ʈ�� ����ϱ� ���� Input/Output ��Ʈ����ü�� ��Ĺ���� ���� ���� �����Ѵ�. �����Ӹ� �ƴ϶� Ŭ���̾�Ʈ������ �ʿ�!
				socketIn = client.getInputStream();
				socketOut = client.getOutputStream();
				dis = new DataInputStream(socketIn);
				dos = new DataOutputStream(socketOut);
				
				//1. Ŭ���̾�Ʈ�� ������ �ϸ� �޼����� ������.
				//		���� -> Ŭ���̾�Ʈ(������ �߿�...output�� �������� �� ��)
				dos.writeUTF(clientIp.getHostAddress()+"�� ������ ȯ���Ѵ���!");
				dos.writeInt(3000);
				
				//2. ���� <- Ŭ���̾�Ʈ
				String data = dis.readUTF();
				System.out.println("Ŭ���̾�Ʈ�� ���� �޼���: "+data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}