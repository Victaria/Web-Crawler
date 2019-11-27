import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;

public class crawl_iterative {
    private int iteration = 0;
    HashSet links = new HashSet<String>();
    int MAX_DEPTH = 20000;

    public static void main(String[] args) {
        String site = "https://www.travelontoast.de/";
        String url = site;
        int depth = 0;
        new crawl_iterative(site, url, depth);
    }

    public crawl_iterative(String site, String url, int depth){
        try {
            Document document = Jsoup.connect(site).get();
            Elements linksOnPage = document.select("a[href]");

            for (Element page : linksOnPage){

                if (url.contains(site)){
                    System.err.println("same");
                }else {
                    System.out.println(">> Depth: " + depth + " [" + url + "] " + iteration++);
                    Document doc = Jsoup.connect(url).get();
                    linksOnPage = document.select("a[href]");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}