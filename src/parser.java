import java.io.File;
import java.io.IOException;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.util.Scanner;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.PrintWriter;

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
    public static void writeToFile(String mirror)throws IOException{
        File writeHere = new File("mirror.txt");
        PrintWriter writing = new PrintWriter(writeHere);
        writing.print(mirror);
        writing.close();
    }
}
