package MathShared;

/**
 * Created by cgf13hun on 27/03/2017.
 */

import java.io.*;
import java.net.Socket;

public class MathClient
{
    public static final String REGEX_EXPRESSION = "^[-+\\/*]:[0-9]+(\\.[0-9]+)?:[0-9]+(\\.[0-9]+)?$";

    public static void main(String[] args) {
        // create Socket for communication
        try (Socket kkSocket = new Socket("localhost", 4444)) {
            try {
                // Create Input and Output streams
                PrintWriter os = new PrintWriter(new OutputStreamWriter(
                        kkSocket.getOutputStream()));
                BufferedReader is = new BufferedReader(new InputStreamReader(
                        kkSocket.getInputStream()));
                String fromServer, input;
                try {
                    while ((fromServer = is.readLine()) != null) {
                        System.out.println("Server: " + fromServer);
                        if (fromServer.equals("Bye.")) {
                            break;
                        }

                        // sets up a stream for user input
                        BufferedReader userInput = new BufferedReader(
                                new InputStreamReader(System.in));
                        input = userInput.readLine();

                        // Validate input
                        String result;
                        if (!input.matches(REGEX_EXPRESSION) && !input.equals("Bye."))
                        {
                            result = "INVALID";
                            System.out.println("Client: " + input);
                            os.println(result); // sending message to the server
                        } else {
                            System.out.println("Client: " + input);
                            os.println(input); // sending message to the server
                        }
                        os.flush();
                    } // end while
                } catch (IOException e) {
                    System.err.println("Unable to open I/O streams  " + e);
                }
                os.close();
                is.close();
            } catch (IOException e) {
                System.err.println("Unable to open socket to Host  " + e);
            }
            kkSocket.close();
        } catch (IOException ex) {
            System.err.println("Unable to connect to host  " + ex);
        }
    } // end main

} // end MathClient

