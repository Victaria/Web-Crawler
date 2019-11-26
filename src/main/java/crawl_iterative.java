import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}