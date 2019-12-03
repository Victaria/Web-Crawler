import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class crawl {
    private int iteration = 0;
    public HashSet<String> links = new HashSet<String>();
    private int MAX_DEPTH = 2;

    //test function
    public void main(String[] args) {

        String site = "https://www.travelontoast.de/";
        String url = site;
        int depth = 0;
        crawler(site, url, depth);
    }


    public crawl(String site, String url, int depth){
        System.out.println("before crawl");
        crawler(site, url, depth);

        //output to file
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("list.txt")));
            {
                Iterator hashSetI = links.iterator();
                while (hashSetI.hasNext()) {
                    String o = hashSetI.next().toString();
                    writer.println(o.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("list.txt created");
    }



    //recursive crawler
    // Filtering soll unwichige Seiten herausnehmen
    // ungefähre Seitenanzahl: 1512
    // Anzahl Iterationen über nützliche und nicht  nützliche Seiten:20.000

    public void crawler(String site, String url, int depth){
        if (url.endsWith(".jpg")
                || url.endsWith(".png")
                || url.endsWith("#")
                || url.contains("wp-content")
                || url.endsWith("pdf")){
            //System.out.println("excluded, depth: " + depth + " " + url);
        } else {
            if(url.contains(site)
                    && !links.contains(url)
                    && !url.contains("#")
                    && !url.contains("img_")
                    && !url.contains("page")
                    && !url.contains("tag")
                    && !(url.matches("0-9" + "/"))){
                //add to url list
                links.add(url);
                iteration++;

                //Zur Analyse
                System.out.println(">> Depth: " + depth + " [" + url + "] ");

                //stopp in test mode
                if (depth >= MAX_DEPTH)
                    System.err.println("---------------------> ERROR MAX_DEPTH");
                else {
                    try {

                        //live analysis - not work recursively
                        //collect analyse = new collect();
                        //analyse.analyse(url, iteration, "data.xls");


                        Document document = Jsoup.connect(url).get();
                        Elements linksOnPage = document.select("a[href]");

                        for (Element page : linksOnPage) {
                            crawler(site, page.attr("abs:href"), depth++);
                        }

                    }catch (IOException e) {
                        //e.printStackTrace();
                        //System.err.println("For '" + url + "': " + e.getMessage());
                    }
                }
            }
        }
    }
}
