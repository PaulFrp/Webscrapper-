import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsoupExample {
    public static void main(String[] args) {
        try {

            // Connect to the website and fetch HTML content
            String url = "https://en.wikipedia.org/wiki/FIFA_100"; // Replace with the URL of the website
            Document doc = Jsoup.connect(url).get();

            // Select all <td> tags within the specified structure
            Elements trTags = doc.select("table.wikitable.plainrowheaders.sortable td");
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%n", "nationality", "sex", "position", "born", "died");
            // Iterate through the list of <td> elements
            int index= 5;
            for (Element trTag : trTags) {
                // Get the text values inside the <td> tags in each <tr>
                Element firstTd = trTag.selectFirst("td");
                String result = firstTd.text();

                if(index > 0){

                    System.out.printf("%-30s", result);
                    index -=1;

                }else{
                    System.out.printf("%n%-30s", result);
                    index = 4;
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            Map<String, List<String>> playerTeamsMap = new HashMap<>();
            // Connect to the website and fetch HTML content
            String url = "https://en.wikipedia.org/wiki/FIFA_100"; // Replace with the URL of the website
            Document doc = Jsoup.connect(url).get();

            // Select all <a> tags within the specified structure
            Elements aTags = doc.select("span.vcard span.fn a");
            System.out.printf("%-30s%n", "Player");
            // Iterate through the list of elements
            for (Element aTag : aTags) {
                // Get the text value inside each <a> tag

                String result = aTag.text();
                String playerUrl = "https://en.wikipedia.org" + aTag.attr("href");
                Document playerDoc = Jsoup.connect(playerUrl).get();

                Elements aTags2 = playerDoc.select("td.infobox-data.infobox-data-a a");
                List<String> team = new ArrayList<>();

                for (Element aTag2 : aTags2) {

                    String aResult = aTag2.text();
                    String aTitle = aTag2.attr("title");

                    //doesn't contain anything but it s on purpose
                    if(containsNumbers(aResult)){

                    }else{

                        if (aTitle.contains("national")) {

                        }

                        team.add(aResult);
                    }

                }
                playerTeamsMap.put(result, team);
            }

            for (Map.Entry<String, List<String>> entry : playerTeamsMap.entrySet()) {
                System.out.println("Player: " + entry.getKey());
                System.out.println("Teams: " + entry.getValue());
                System.out.println();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean containsNumbers(String input) {
        return input.matches(".*\\d.*");
    }

}
