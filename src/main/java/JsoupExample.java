import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsoupExample {
    public static void main(String[] args) {
        try {

            List<String> nationality = new ArrayList<>();
            List<String> sex = new ArrayList<>();
            List<String> position = new ArrayList<>();
            List<String> born = new ArrayList<>();
            List<String> died = new ArrayList<>();
            List<String> national = new ArrayList<>();

            Map<String, List<String>> playerTeamsMap = new LinkedHashMap<>();
            // Connect to the website and fetch HTML content
            String url = "https://en.wikipedia.org/wiki/FIFA_100"; // Replace with the URL of the website
            Document doc = Jsoup.connect(url).get();

            // Select all <td> tags within the specified structure
            String targetTableSelector = "table.wikitable.plainrowheaders.sortable";

            // Iterate through the list of <td> elements

            // Select all <a> tags within the specified structure

            Elements aTags = doc.select("table.wikitable.plainrowheaders.sortable span.vcard span.fn a,table.wikitable.plainrowheaders.sortable th[scope=row] a");
            Element targetTable = doc.select(targetTableSelector).first();
            for(int i = 0; i<5; i++ ){
                if (targetTable != null) {

                    Elements rows = targetTable.select("tr");
                    //Could prolly make this a function
                    for (Element row : rows) {
                        Elements columns = row.select("td");

                        if (!columns.isEmpty() && columns.size() > i  ) {
                            String value = columns.get(i).text();
                            if(i == 0){
                                nationality.add(value);
                            } else if (i == 1) {
                                sex.add(value);
                            } else if (i==2) {
                                position.add(value);
                            } else if (i==3) {
                                born.add(value);
                            } else {
                                died.add(value);
                            }

                        }
                    }
                } else {
                    System.out.println("Table with the specified selector not found.");
                }
            }


            // Iterate through the list of elements

            for (Element aTag : aTags) {
                // Get the text value inside each <a> tag
                String result = aTag.text();
                String playerUrl = "https://en.wikipedia.org" + aTag.attr("href");
                Document playerDoc = Jsoup.connect(playerUrl).get();

                Elements aTags2 = playerDoc.select("td.infobox-data.infobox-data-a a");
                List<String> team = new ArrayList<>();


                if (aTags2.isEmpty()) {
                    // Add default or placeholder value
                    team.add("No value");

                } else {
                    // Process the values as usual
                    for (Element aTag2 : aTags2) {

                        String aResult = aTag2.text();
                        String aTitle = aTag2.attr("title");

                        //doesn't contain anything but it s on purpose
                        if(containsNumbers(aResult)){

                        }else{

                            if (aTitle.contains("national")) {
                                national.add(aResult);
                            }else{
                                team.add(aResult);
                            }


                        }

                    }
                }

                playerTeamsMap.put(result, team);
            }

            PlayerDataListWrapper wrapper = new PlayerDataListWrapper();
            int j = 0;
            for (Map.Entry<String, List<String>> entry : playerTeamsMap.entrySet()) {
                PlayerData playerData = new PlayerData();
                playerData.setUp(entry.getKey(),sex.get(j),nationality.get(j), position.get(j),born.get(j), died.get(j), national.get(j) );
                playerData.setTeams(entry.getValue());
                wrapper.getPlayerDataList().add(playerData);
                j++;
            }


            JAXBContext context = JAXBContext.newInstance(PlayerDataListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(wrapper, new File("output.xml"));
            System.out.println("printed document");



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }


    }
    private static boolean containsNumbers(String input) {
        return input.matches(".*\\d.*");
    }

}
