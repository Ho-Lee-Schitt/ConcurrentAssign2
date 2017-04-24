package Question2;

/**
 * Created by cgf13hun on 07/04/2017.
 */

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

public class MathClientHttp
{
    private static final String BASE_URI = "http://localhost:8080/calc";

    public static void main(String[] args) throws UnsupportedEncodingException {

        Scanner userInput = new Scanner(System.in);
        String numberString;
        do {
            System.out.print("Please enter your equation in the format <+|-|*|/>:5.5:1.2 e.g. +:3.2:4.5\n");
            numberString = userInput.nextLine();
            if (!numberString.matches("^[-+\\/*]:[0-9]+(\\.[0-9]+)?:[0-9]+(\\.[0-9]+)?$"))
            {
                System.out.print("The equation entered was invalid.\n");
            }
        } while (!numberString.matches("^[-+\\/*]:[0-9]+(\\.[0-9]+)?:[0-9]+(\\.[0-9]+)?$"));
        //int number = Integer.parseInt(numberString);
        //String answer = mi.processInput(numberString);

        String operators[] = numberString.split(":");

        operators[0] = URLEncoder.encode(operators[0], "UTF-8");

        String queryExpression = "operator=" + operators[0] + "&" + "num1=" + operators[1] + "&" + "num2=" + operators[2];

        try {
            String result = Request.Get(BASE_URI + "?" + queryExpression).execute().returnContent().asString();
            System.out.println("Result = " + result);
        } catch (HttpResponseException e) {
            System.out.println("Error: " + e.getStatusCode());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
