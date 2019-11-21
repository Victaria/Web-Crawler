public class Main {

    public static void main(String[] args) {

        //listing all URL`s
        String start_url = "https://www.travelontoast.de/";

        Bot bot = new Bot(start_url);
        bot.start();

        //listing depth of USl's
        BasicWebCrawler bwc = new BasicWebCrawler();
        bwc.start();


    }
}
