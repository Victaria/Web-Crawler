import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Pattern;

public class crawl {
    private int iteration = 0;
    HashSet links = new HashSet<String>();
    int MAX_DEPTH = 20000;

    public void main(String[] args) {

        String site = "https://www.travelontoast.de/";
        String url = site;
        int depth = 0;
        crawl(site, url, depth);
    }

//recursive crawler
    public void crawl(String site, String url, int depth){
        if (url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith("#") || url.contains("wp-content") || url.endsWith("pdf")){
            //excluded
        }else {
            if(url.contains(site) && depth <= MAX_DEPTH && !links.contains(url) && !url.contains("#")
                    &&!url.contains("img_") && url.matches("//d")){

                System.out.println(">> Depth: " + depth + " [" + url + "] " + iteration++);
                try {
                    links.add(url);

                    if (url.contains("#"))
                        System.out.println("#");

                    Document document = Jsoup.connect(url).get();
                    Elements linksOnPage = document.select("a[href]");

                    for (Element page : linksOnPage) {
                        crawl(site, page.attr("abs:href"), depth++);
                    }

                }catch (IOException e) {
                    //e.printStackTrace();
                    System.err.println("For '" + url + "': " + e.getMessage());
                }
                }else {
                    //System.out.println("excluded, depth: " + depth + " " + url);
            }
        }
    }
}
