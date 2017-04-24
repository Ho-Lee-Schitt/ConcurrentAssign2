package Concurrent;

import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by cgf13hun on 27/03/2017.
 */

public class ConcurrentServer
{

    public static void main(String[] args) {

        try {
            // Open a server socket to listen on port 4444
            ServerSocket serverSocket = new ServerSocket(4444);
            System.out.println("Concurrent: Server started");
            while (true)
            {
                try
                {
                    // Wait to accept a connecting client
                    Socket clientSocket = serverSocket.accept();

                    ServerThread clientThread = new ServerThread(clientSocket);
                    System.out.println("Concurrent: Client Connected");
                    clientThread.start();

                } catch (IOException e)
                {
                    System.out.println("Accept failed on port: " + 4444 + ", " + e);
                    System.exit(1);
                }
            }
            //serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + 4444 + ", " + e);
            System.exit(1);
        } // end catch
    } // end main

} // end ConcurrentServer