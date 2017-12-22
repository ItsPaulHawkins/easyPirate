import java.io.File;
import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.util.Scanner;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import javax.print.Doc;
import java.util.ArrayList;
public class parser {
    public parser(){
    }
    public static void main(String args[]) throws  IOException{

    }
    public static void parseIt(String url) throws IOException{
        Document doc = Jsoup.connect(url).get(); //downloads the source file from the URL, only works with piratebay queries.
        Elements links = doc.select("[href]"); //gets all the links from the file
        ArrayList<String> linkList= new ArrayList<String>(); //creates an arraylist to store the links in
        int objAmount = 0;
        for (Element link : links) { //grabs the magnets
            String linkS = "" + link;
            if (linkS.substring(0,15).equals("<a href=\"magnet")) {
                linkList.add("\n" + linkS);
                objAmount++;
            }
        }
        String magnet = linkList.get(0); //selects the first magnet
        System.out.println(magnet);
        Document magnetDoc = Jsoup.parse(magnet);
        Element link = magnetDoc.select("a").first();
        String relHref = link.attr("href"); // == "/"
    }
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
