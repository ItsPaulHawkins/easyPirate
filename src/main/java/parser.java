import java.io.File;
import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.util.Scanner;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.PrintWriter;
import java.util.ArrayList;
public class parser {

    public static void main(String args[]) throws  IOException{

    }
    public static void convertToUrl(String movie) throws IOException{ //creates the proper url format
        movie = movie.replace(" ","%20" );
        parseIt(movie);
    }
    public static void parseIt(String url) throws IOException{
        url = "https://www.thepiratebay.org/search/" + url;
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
        Document magnetDoc = Jsoup.parse(magnet);
        Element link = magnetDoc.select("a").first();
        String relHref = link.attr("href"); // == "/"
        System.out.println(relHref);
        writeToFile(relHref);
    }
    public static void writeToFile(String mirror)throws IOException{
        File writeHere = new File("/home/pablo/Desktop/export/mirror.txt");
        PrintWriter writing = new PrintWriter(writeHere);
        writing.print(mirror);
        writing.close();
    }
}
