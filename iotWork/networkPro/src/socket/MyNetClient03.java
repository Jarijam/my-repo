package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyNetClient03 {
	//������ ����ϸ� �����͸� �ְ� ���� �� �ִ� Ŭ���̾�Ʈ
	public static void main(String[] args) {
		Socket serverobj = null;
		InputStream socketIn = null; //������ �������� �����͸� �б� ���ؼ� ���Ͽ��� �������� ��Ʈ����ü
		OutputStream socketOut = null; //�������� �����͸� ������ ���ؼ� ��Ĺ���� ��������  ��Ʈ����ü
		DataInputStream dis = null; // ������ ���� ���� �����͸� Ÿ�Ժ��� �б� ���� ����ϴ� ���� ��Ʈ����ü
		DataOutputStream dos = null; // ������ ���� �����͸� Ÿ�Ժ��� ������ ���� ����ϴ� ���� ��Ʈ����ü
		
		try {
			//������ ����� �� �ִ� ���ϰ�ü�� ����
			//������ ������ ��û
			serverobj = new Socket("192.168.0.158", 12345);
			System.out.println("���� ���� �Ϸ�: "+serverobj);
			//������ ����ϱ� ���� Input/Output ��Ʈ����ü�� ��Ĺ���� ���� ���� �����Ѵ�. �����Ӹ� �ƴ϶� Ŭ���̾�Ʈ������ �ʿ�!
			socketIn = serverobj.getInputStream();
			socketOut = serverobj.getOutputStream();
			dis = new DataInputStream(socketIn);
			dos = new DataOutputStream(socketOut);
			
			//1. ������ �������� �����͸� �б�(������ �߿� - 2���� �����ؼ� �о��ִ� �۾�)
			//		Ŭ���̾�Ʈ <- ����
			String data = dis.readUTF();
			System.out.println(data);
			int intdata = dis.readInt();
			System.out.println("������ ������ ���� ��:"+intdata);
			for (int i=1; i<=9; i++) {
				System.out.print(intdata+"*"+i+"="+(intdata * i)+"   ");
			}
			
			
			//2. Ŭ���̾�Ʈ -> ����
			if (intdata%2==0) {
				dos.writeUTF("�������� ¦���� �����̱���");
			}else {
				dos.writeUTF("�������� Ȧ���� �����̱���");
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}