import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import bt.*;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import bt.runtime.BtRuntime;
import bt.runtime.Config;
import java.lang.Math;

public class download {
    public download(){

    }
    public static void main(String args[]){

    }
    public static void downloadIt() throws IOException{
        File exportPath = new File("/home/pablo/Desktop/export/"); //http://atomashpolskiy.github.io/bt/intro/
        File importPath = new File("/home/pablo/Desktop/export/mirror.txt");
        Scanner readTheFile = new Scanner(importPath);
        String magnetLink = readTheFile.nextLine();
        System.out.println(magnetLink);
        Storage storage = new FileSystemStorage(exportPath.toPath());
        DHTModule dhtModule = new DHTModule(new DHTConfig(){
            @Override
            public boolean shouldUseRouterBootstrap() {
                return true;
            }
        });
        BtClient client = Bt.client().magnet(magnetLink).storage(storage).autoLoadModules().module(dhtModule).build();

        client.startAsync(state -> {
            int lastByte = 0;
            while(state.getPiecesRemaining() != 0){
                if(lastByte != state.getPiecesRemaining()) {
                    System.out.println("Downloading: " + Math.abs(((double)state.getPiecesRemaining() / (double)state.getPiecesTotal()) * 100 - 100) + "%");
                    lastByte = state.getPiecesRemaining();

                }
            }
            if (state.getPiecesRemaining() == 0){
                System.out.println("Done!");
                client.stop();
            }

        }, 1000).join();


    }
}
