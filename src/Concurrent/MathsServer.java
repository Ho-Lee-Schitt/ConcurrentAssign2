package Concurrent;

import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by cgf13hun on 27/03/2017.
 */

public class MathsServer {

    public static void main(String[] args) {

        try {
            // Open a server socket to listen on port 4444
            ServerSocket serverSocket = new ServerSocket(4444);
            while (true)
            {
                try
                {
                    // Wait to accept a connecting client
                    Socket clientSocket = serverSocket.accept();

                    ServerThread clientThread = new ServerThread(clientSocket);
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

} // end KnockKnockServer

class ServerThread extends Thread {
    BufferedReader is;
    PrintWriter os;
    String inputLine;
    String outputLine;
    MathInput MI;
    Socket clientSocket;

    public ServerThread(Socket cs)
    {
        this.clientSocket = cs;
    }

    public void run() {
        try (BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter os = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            // Set up the server state
            System.out.println("Math server waiting for input");
            MathInput MI = new MathInput();
            String inputLine, outputLine;
            outputLine = "Please enter your equasion in the format +:3.2:4.5";
            os.println(outputLine);
            os.flush();
            while ((inputLine = is.readLine()) != null) {
                outputLine = MI.processInput(inputLine);
                os.println(outputLine);
                os.flush();
                if (outputLine.equals("Bye.")) {
                    break;
                }
                if (outputLine.equals("NULL")) {
                    throw new RuntimeException();
                }
            } // end while
            clientSocket.close();
            //serverSocket.close();
        } catch (IOException e) {
            System.out.println("Failed to create I/O streams " + e);
            e.printStackTrace();
        }
    }
}