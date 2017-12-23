import java.io.File;
import java.net.InetAddress;
import java.nio.file.Path;

import
import bt.*;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.Config;

public class download {
    public static void main(String args[]){
        File exportPath = new File("/users/Pablo/Desktop/export");
        File importPath = new File("magnet.txt");
        Config config = new Config(){
            @Override
            public int getNumOfHashingThreads(){
                return Runtime.getRuntime().availableProcessors() * 2;
            }

        };
        DHTModule dhtModule = new DHTModule(new DHTConfig() {
            @Override
            public boolean shouldUseRouterBootstrap() {
                return true;
            }
        });
        Path targetDirectory = new File("~/Downloads").toPath();

    }
}
