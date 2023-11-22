import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTableColumnExample {
    public static void main(String[] args) {
        try {
            // Connect to the website and fetch HTML content
            String url = "https://en.wikipedia.org/wiki/FIFA_100"; // Replace with the URL of the website
            Document doc = Jsoup.connect(url).get();

            // Specify the id or class of the table you're interested in
            String targetTableSelector = "table.wikitable.plainrowheaders.sortable"; // Adjust as needed

            // Specify the index of the column you're interested in (e.g., 2 for the third column)
            int targetColumnIndex = 1; // Adjust as needed

            // Extract values from the specified table and column
            Element targetTable = doc.select(targetTableSelector).first();

            if (targetTable != null) {
                Elements rows = targetTable.select("tr");

                for (Element row : rows) {
                    Elements columns = row.select("td");

                    if (!columns.isEmpty() && columns.size() > targetColumnIndex) {
                        String value = columns.get(targetColumnIndex).text();
                        System.out.println("Value from the specified column: " + value);
                    }
                }
            } else {
                System.out.println("Table with the specified selector not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
