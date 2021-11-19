package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

//URLŬ����
//=> ���ͳݿ��� Ȯ���� �� �ִ� �ڿ��� ����
public class URLTest01 {
	public static void main(String[] args) {
		try {
//			URL url = new URL("https://www.kisa.or.kr/jsp/common/downloadAction.jsp?bno=259&dno=66&fseq=1");
			URL url = new URL("https://www.daum.net");
			System.out.println(url.toString());
			System.out.println("url.getHost()===>"+url.getHost());
			System.out.println("url.getPath()===>"+url.getPath());
			//port�� -1
			//-1��Ʈ�� �����ߴٴ� �ǹ̰� �ƴ϶� http�� �⺻��Ʈ�� �����ϰ� �ִٴ� �ǹ�
			//�⺻��Ʈ : 80
			// �츮�� �����ϱ� ���� ����� url�� ���� �⺻ ��Ʈ�� ����ϰ� �ִٴ� �ǹ�
			System.out.println("url.getPort()===>"+url.getPort());
			System.out.println("url.getProtocol()===>"+url.getProtocol());
			System.out.println("url.getFile()===>"+url.getFile());
			
			//������ url���� �ڿ��� �о����
			//io�� �о�ö� ���� �о��??�����µ�? Buffered reader => ������ ����ϱ� ������ ������� ���� ��κ� BufferedReader��. �� binary data ����(���� ������ ��)
//			InputStream is = url.openStream();
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			
			while(true) {
				String data_byte = br.readLine();
				if(data_byte==null) {
					break;
				}
				System.out.println(data_byte);
			}
		} catch (MalformedURLException e) { //URL�� ������ �߸� �Ǿ����� �ߴ� ����
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}