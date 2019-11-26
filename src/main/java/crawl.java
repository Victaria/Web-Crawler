import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class crawl {
    private int iteration = 0;
    private HashSet<String> links = new HashSet<String>();
    private int MAX_DEPTH = 50000;

    public void main(String[] args) {

        String site = "https://www.travelontoast.de/";
        String url = site;
        int depth = 0;
        crawl(site, url, depth);
    }

//recursive crawler
    public void crawl(String site, String url, int depth){
        if (url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith("#") || url.contains("wp-content") || url.endsWith("pdf")){
            //System.out.println("excluded, depth: " + depth + " " + url);
        }else {
            if(url.contains(site) && !links.contains(url) && !url.contains("#")
                    && !url.contains("img_") && !url.contains("page") && !url.contains("tag")
                    && !(url.matches("0-9" + "/"))    ){

                //System.out.println(">> Depth: " + depth + " [" + url + "] " + iteration++);
                try {
                    links.add(url);

                    if (depth >= MAX_DEPTH)
                        System.err.println("---------------------> ERROR MAX_DEPTH");

                    Document document = Jsoup.connect(url).get();
                    Elements linksOnPage = document.select("a[href]");

                    for (Element page : linksOnPage) {
                        crawl(site, page.attr("abs:href"), depth++);
                    }

                }catch (IOException e) {
                    //e.printStackTrace();
                    //System.err.println("For '" + url + "': " + e.getMessage());
                }
            }
        }
    }
}
