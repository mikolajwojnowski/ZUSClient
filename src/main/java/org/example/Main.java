package org.example;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public final static Logger  logr = Logger.getLogger(Logger.class.getName());


    public static void main(String[] args) throws IOException, InterruptedException
    {

        FileHandler fh = new FileHandler("logs.log");
        fh.setLevel(Level.ALL);
        logr.addHandler(fh);
        logr.info("uzytkownik uruchamia aplikacje");


        LoginPage loginPage = new LoginPage();

    }
}