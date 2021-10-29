package xml;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//��걤����_������ ��Ȳ�� �Ľ��ϱ�
//xml ������ -- X
//url�� ���ؼ� �����ϰ� xml������ ������ �� parser�� ���ϴ� ������ ����
public class seochoTest2 {
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			//�Ľ��� InputStream ����
			//1. �����񽺸� �����ϴ� url�� �����ؼ� �����͸� ��������
			//1) ������ url�� ������ ����
			StringBuffer urlBuilder = new StringBuffer(); 
			urlBuilder.append("http://api.odcloud.kr/api/3053840/v1/uddi:4deadabc-7425-44b2-bfd1-c5475c257fa9_201905171537"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=data-portal-test-key"); /*Service Key*/
//	        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*����Ű*/
	        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*������ ��ȣ*/
	        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*�� ������ ��� ��*/
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*��û�ڷ�����(XML/JSON)*/
//	        urlBuilder.append("&" + URLEncoder.encode("sidoCd","UTF-8") + "=" + URLEncoder.encode("110000", "UTF-8")); /*�õ��ڵ�*/
//	        urlBuilder.append("&" + URLEncoder.encode("sgguCd","UTF-8") + "=" + URLEncoder.encode("110019", "UTF-8")); /*�ñ����ڵ�*/
//	        urlBuilder.append("&" + URLEncoder.encode("emdongNm","UTF-8") + "=" + URLEncoder.encode("�ų���", "UTF-8")); /*���鵿��*/
//	        urlBuilder.append("&" + URLEncoder.encode("yadmNm","UTF-8") + "=" + URLEncoder.encode("�´����ǰ�", "UTF-8")); /*������*/
//	        urlBuilder.append("&" + URLEncoder.encode("xPos","UTF-8") + "=" + URLEncoder.encode("127.0965441345503", "UTF-8")); /*x��ǥ*/
//	        urlBuilder.append("&" + URLEncoder.encode("yPos","UTF-8") + "=" + URLEncoder.encode("37.60765568913871", "UTF-8")); /*y��ǥ*/
//	        urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode("3000", "UTF-8")); /*�ݰ�*/
	        
			
			
			
	        //2) �������� �����ϱ�
			URL url = new URL(urlBuilder.toString());
			//System.out.println(url.openStream());
			//3) �����ؼ� reponse�Ǵ� �����͸� �о����
			//	 �о�� �����͸� ���� - BufferedInputStream�� InputStream�� ����
			BufferedInputStream xmldata = new BufferedInputStream(url.openStream());
			
			Document document = builder.parse(xmldata); //InputStream��ü�� ���·� �Ľ��� ������ �Ѱ��ش�.
			
			Element root = document.getDocumentElement();
			System.out.println(root.getTagName());
			NodeList list = root.getElementsByTagName("item");
			System.out.println(list.getLength());
			
			for(int i=0;i<list.getLength();i++) {
				Node node = list.item(i);
				NodeList item_childlist = node.getChildNodes();
				//System.out.println(item_childlist);
				for(int j=0;j<item_childlist.getLength();j++) {
					Node item_child = item_childlist.item(j);
					System.out.println(item_child.getNodeName()+":"+ item_child.getTextContent());
					System.out.println(item_child.getTextContent());
				}
				System.out.println();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}