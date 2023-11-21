import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class WriteToXML {
    public static void main(String[] args) {
        try {
            // Create a new XML document
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Create the root element
            Element rootElement = document.createElement("data");
            document.appendChild(rootElement);

            // Example data
            String playerName = "John Doe";
            String teamName = "National Team";

            // Create elements and add data
            Element playerElement = document.createElement("player");
            playerElement.appendChild(document.createTextNode(playerName));
            rootElement.appendChild(playerElement);

            Element teamElement = document.createElement("team");
            teamElement.appendChild(document.createTextNode(teamName));
            rootElement.appendChild(teamElement);

            // Write the XML document to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("output.xml"));

            transformer.transform(domSource, streamResult);

            System.out.println("XML file created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
