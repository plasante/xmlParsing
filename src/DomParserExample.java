import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DomParserExample {

	List myEmpls;
	Document dom;
	
	public DomParserExample() {
		myEmpls = new ArrayList();
	}
	
	public static void main(String[] args) {
		DomParserExample dpe = new DomParserExample();
		
		dpe.runExample();
	}

	private void runExample() {
		parseXmlFile();
		
		parseDocument();
		
		printData();
		
	}

	private void printData() {
		System.out.println("No of Employees '" + myEmpls.size() + "'.");
		Iterator it = myEmpls.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

	private void parseDocument() {
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("Employee");
		if (nl != null && nl.getLength() > 0) {
			for(int i = 0; i < nl.getLength(); i++) {
				Element el = (Element)nl.item(i);
				Employee e = getEmployee(el);
				myEmpls.add(e);
			}
		}
	}

	private Employee getEmployee(Element empEl) {
		String name = getTextValue(empEl, "Name");
		int id = getIntValue(empEl, "Id");
		int age = getIntValue(empEl, "Age");
		
		String type = empEl.getAttribute("type");
		Employee e = new Employee(name,id,age,type);
		return e;
	}

	private int getIntValue(Element ele, String tagName) {
		return Integer.parseInt(getTextValue(ele, tagName));
	}

	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

	private void parseXmlFile() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse("src\\personnel.xml");
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
