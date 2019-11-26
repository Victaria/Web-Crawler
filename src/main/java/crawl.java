import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class crawl {
    private int iteration = 0;
    HashSet links = new HashSet<String>();
    int MAX_DEPTH = 2000;

    public void main(String[] args) {

        String site = "https://www.travelontoast.de/";
        String url = site;
        int depth = 0;

        crawl(site, url, depth);
    }

//recursive crawler
    public void crawl(String site, String url, int depth){

        System.out.println("iteration: " + iteration);
        iteration++;

        if(url.contains(site) && depth <= MAX_DEPTH && !links.contains(url)){
            System.out.println(">> Depth: " + depth + " [" + url + "]");
            try {
                links.add(url);

                Document document = Jsoup.connect(url).get();
                Elements linksOnPage = document.select("a[href]");

                for (Element page : linksOnPage) {
                    crawl(site, page.attr("abs:href"), depth++);
                }

            }catch (IOException e) {
                e.printStackTrace();
                System.err.println("For '" + url + "': " + e.getMessage());
            }
        }else {
            System.out.println("end of site, depth: " + depth);
        }
    }
}
