import java.io.IOException;
import java.util.Scanner;
public class Start {
    public static void main(String args[]) throws IOException{
        Scanner getTitle = new Scanner(System.in);
        System.out.println("Name the title of a movie or tv show.");
        String title = getTitle.nextLine();
        parser theParser = new parser();
        theParser.convertToUrl(title);
        download downloader = new download();
        downloader.downloadIt();

    }
}
