import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Site {
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
        String start_url = "https://www.travelontoast.de/tokio-tipps-sehenswuerdigkeiten/#Tokio_Tipps_zur_Reiseplanung";
        int type = 0;
        Site analyse = new Site();
        analyse.analyse(start_url);
    }

    private void analyse(String url){

        if (url.contains("category")){
            //TODO is a overview site
        }else{
            //TODO is it not
        }

        String html = getHTML(url);
        Document doc = Jsoup.parse(html);

        //count the number of page pics
        Elements img = doc.select("img");
        int countpics = 0;
        for (Element e: img){
            countpics++;
        }
        System.out.println("countpics" + countpics);

        //count number of article
        Elements article = doc.select("article");
        int countart = 0;
        for (Element e: article){
            countart++;
        }

        //count
        //When The Site is the Home Site

            //TODO save newest posts


        //When the Site is individual Site

            //TODO number word and pics


        //When the Site is post overview like home site

            //TODO save number posts, comments


        //When the Site is a post

            //TODO number of words per title, pics, comments

        //count words
        Elements p = doc.select("h1");
        int countp = 0;
        for (Element e: article){
            countp++;
        }
        System.out.println("countp" + countp);

        Elements h1 = doc.select("h1");
        int counth1 = 0;
        for (Element e: article){
            counth1++;
        }
        Elements h2 = doc.select("h2");
        int counth2 = 0;
        for (Element e: article){
            counth2++;
        }
        Elements h3 = doc.select("h3");
        int counth3 = 0;
        for (Element e: article){
            counth2++;
        }

        //count comment
        Elements comment = doc.getElementsByClass("comments");
        int countcomment = 0;
        for (Element e: comment){
            countcomment++;
        }
        System.out.println("countcomment" + countcomment);
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
