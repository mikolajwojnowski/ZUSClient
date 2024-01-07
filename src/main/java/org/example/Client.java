package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


//KLASA UMOZLIWIAJACA POLACZENIE SIE Z SERWEREM
/**
 *
 * */
public class Client{
    String odpowiedzOdSerwera;

    //wyslamie zapytania od klienta na serwer jako argument String tresc
    //String typKomendy do rozroznienia rodzaju zapytania po stronie serwera
    public void zapytanie(String typKomendy,String tresc) {

        try {
            //nawiazanie polaczenia z serwerem
            Socket clientSocket = new Socket("localhost", 5000);

            //strumienie do komunikacji z serewerem
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(),true);
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //
            String zapytanieOdKlienta = tresc;
            Main.logr.info("zapytanie od klienta " +zapytanieOdKlienta);

            String commandType = typKomendy;
            Main.logr.info("typ komendy od klienta: " +commandType);


            //wyslanie zapytania na serwer
            printWriter.println(commandType);
            printWriter.println(tresc);

            //odbior odpowiedzi od serwera
            odpowiedzOdSerwera = bufferedReader.readLine();

            // Przetwarzanie odpowiedzi
            System.out.println("Odpowiedź od serwera: " + odpowiedzOdSerwera);
            Main.logr.info("odpowiedz od serwera: " +odpowiedzOdSerwera);

            //zamkniecie
            printWriter.close();
            bufferedReader.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}


