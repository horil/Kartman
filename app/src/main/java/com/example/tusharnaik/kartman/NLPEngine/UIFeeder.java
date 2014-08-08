package com.example.tusharnaik.kartman.NLPEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tushar.naik on 07/08/14.
 */
public class UIFeeder {

    public static void main(String args[]) throws IOException {

        NLPEngine nlpEngine = new NLPEngine("Asd");
        StopWordRemover stopWordRemover = new StopWordRemover();

        System.out.println("FLIPKART KARTMAN ENGINE");
        System.out.println("(commands to quit: q,quit,exit)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String consoleInput="";
        do {
            System.out.print("OK KARTMAN, ");
            consoleInput=br.readLine();
            //nlpEngine.processCommand();

        }while(consoleInput!="q" && consoleInput!="quit" && consoleInput!="exit");
        System.out.println("THANK YOU. KARTMAN OUT.. PEACE");
    }
}
