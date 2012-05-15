import java.io.ByteArrayInputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


public class ReadingXmlUsingJdom {
	public static void main(String[] args) throws Exception {
		String data =
				  "<root>" +
				      "<Companyname>" +
				          "<Employee name=\"Girish\" Age=\"25\">Developer</Employee>" +
				      "</Companyname>" +
				      "<Companyname>" +
				          "<Employee name=\"Komal\" Age=\"55\">Administrator</Employee>" +
				      "</Companyname>" +
				  "</root>";
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new ByteArrayInputStream(data.getBytes()));
		Element root = document.getRootElement();
		
		List row = root.getChildren("Companyname");
		for (int i = 0; i < row.size(); i++) {
			Element Companyname = (Element) row.get(i);
			
			List column = Companyname.getChildren("Employee");
			for (int j = 0; j < column.size(); j++) {
				Element Employee = (Element) column.get(j);
				String name = Employee.getAttributeValue("name");
				int age = Employee.getAttribute("Age").getIntValue();
				String innerValue = Employee.getText();
				System.out.println("Name = " + name + " ; Age = " + age + " ; Profile = " + innerValue);
			}
		}
	}
}
