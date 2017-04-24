package Iterative;

import java.net.*;
import java.io.*;
import MathShared.MathCalc;

/**
 * Created by cgf13hun on 27/03/2017.
 */

public class MathsServer {

    public static void main(String[] args) {

        try {
            // Open a server socket to listen on port 4444
            ServerSocket serverSocket = new ServerSocket(4444);
            System.out.println("Iterative: Server started");
            try {
                // Wait to accept a connecting client
                Socket clientSocket = serverSocket.accept();
                // Create Input and Output streams to communicate with the client
                try (BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter os = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                    // Set up the server state
                    System.out.println("Math server waiting for input");
                    MathCalc MC = new MathCalc();
                    String inputLine, outputLine;
                    outputLine = "Please enter your equation in the format +:3.2:4.5";
                    os.println(outputLine);
                    os.flush();
                    while ((inputLine = is.readLine()) != null) {
                        if (inputLine.equals("INVALID")) {
                            outputLine = "Invalid input. Please enter your equation in the format +:3.2:4.5";
                        } else
                        {
                            outputLine = MC.processInput(inputLine);
                        }
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
                    serverSocket.close();
                    System.out.println("Iterative: Server Stopped");
                } catch (IOException e) {
                    System.out.println("Failed to create I/O streams " + e);
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println("Accept failed on port: " + 4444 + ", " + e);
                System.exit(1);
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + 4444 + ", " + e);
            System.exit(1);
        } // end catch
    } // end main

} // end MathsServer

