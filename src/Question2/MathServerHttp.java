package Question2;

/**
 * Created by cgf13hun on 07/04/2017.
 */

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MathServerHttp
{

    // static logger
    public static final Logger LOG = Logger.getLogger(MathServerHttp.class.getName());

    // start web server
    public static void main(String[] args) throws Exception {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            LOG.info("HTTP: Server started.");
            server.createContext("/calc", new MathContextHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException ex) {
            LOG.log(Level.ALL, ex.getMessage());
        }
    } // end main

} // MathServerHttp
