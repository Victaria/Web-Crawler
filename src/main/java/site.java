import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class site {
    public static void main(String[] args){
        /*choose site to collect data from extractor
        //type of sites:
        Startseite: overview newest posts
        over this blog: generic
        Reiseziele->country: categorized posts
        Reisearten->type: categorized posts
        Shop:blog:referal link
        blog:blog:txt,pics,comment field ----to analyze
         */
        String start_url = "https://www.travelontoast.de/tokio-tipps-sehenswuerdigkeiten/";
        int type = 0;
        site analyse = new site();
        analyse.analyse(start_url, type);
    }

    private void analyse(String url, int type){


        String html = getHTML(url);
        Document doc = Jsoup.parse(html);
        Elements el = doc.select("img");

        int countpics = 0;
        for (Element e: el){
            countpics++;
            System.out.println(countpics);
        }


        //When The Site is the Home Site
        if (type == 0){
            //TODO save newest posts
        }

        //When the Site is individual Site
        if (type == 1){
            //TODO number word and pics
        }

        //When the Site is post overview like home site
        if (type == 2){
            //TODO save number posts, comments
        }

        //When the Site is a post
        if (type == 3){
            //TODO number of words per title, pics, comments
        }
    }

    private String getHTML(String url) {

        URL u;
        try {
            u = new URL(url);

            URLConnection conn = u.openConnection();
            //   conn.setRequestProperty("User-Agent", "BBot/1.0");
            //  conn.setRequestProperty("Accept-Charset", "UTF-8");

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            String html = "";
            while ((line = reader.readLine()) != null) {
                html += line + "\n";
            }
            html = html.trim();

            return html;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
