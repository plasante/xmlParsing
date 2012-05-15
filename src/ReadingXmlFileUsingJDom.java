import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ReadingXmlFileUsingJDom {
	public static void main(String[] args) {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("src\\personnel.xml");
		try {
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List row = rootNode.getChildren("Employee");
			for (int i = 0; i < row.size(); i++) {
				Element node = (Element) row.get(i);
				System.out.println("Type = " + node.getAttributeValue("type"));
				System.out.println("Name = " + node.getChildText("Name"));
				System.out.println("Id = " + node.getChildText("Id"));
				System.out.println("Age = " + node.getChildText("Age"));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
