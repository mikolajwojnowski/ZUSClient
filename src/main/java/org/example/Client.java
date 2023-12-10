package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * klasa umozliwia polaczenie sie z serewerem
 */
public class Client
{
    String odpowiedz;
    Boolean licznik = true;
    public String zapytanie(String tresc)
    {
        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 1234)) {

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // object of scanner class


            while (licznik == true) {

                // reading from user


                // sending the user input to server
                out.println(tresc);
                out.flush();

                // displaying server reply

                odpowiedz = in.readLine();
                licznik = false;
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return odpowiedz;
    }
}


