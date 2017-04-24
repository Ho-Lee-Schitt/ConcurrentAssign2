package Concurrent;

import MathShared.MathCalc;

import java.io.*;
import java.net.Socket;

/**
 * Created by Niall Hughes on 23/04/2017.
 */
public class ServerThread extends Thread {
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
            System.out.println("Concurrent: client Closed");
            //serverSocket.close();
        } catch (IOException e) {
            System.out.println("Failed to create I/O streams " + e);
            e.printStackTrace();
        }
    }
}