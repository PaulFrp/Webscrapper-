import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class WriteToXML {
    public static void main(String[] args) {
        try {
            // Create a PlayerData object (replace this with your actual data)
            PlayerData playerData = new PlayerData();
            playerData.setUp("John Doe", "Male", "USA", "Forward", "1990-01-01", "2022-12-31");

            // Create JAXB context and marshaller
            JAXBContext context = JAXBContext.newInstance(PlayerData.class);
            Marshaller marshaller = context.createMarshaller();

            // Convert the object to XML
            StringWriter writer = new StringWriter();
            marshaller.marshal(playerData, writer);

            // Print the XML
            System.out.println(writer.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
