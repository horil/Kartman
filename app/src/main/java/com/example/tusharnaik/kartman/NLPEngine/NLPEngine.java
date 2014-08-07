package com.example.tusharnaik.kartman.NLPEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by tushar.naik on 07/08/14.
 */
public class NLPEngine {
    String previousCommand;
    String command;
    List<String> associatedKeywords =new ArrayList<String>();
    List<String> tokens;
    StopWordRemover stopWordRemover = new StopWordRemover();
    StringTokenizer stringTokenizer;
    public NLPEngine()
    {
        command="";
        previousCommand="";
    }

    public void processCommand(String consoleInput) {

        tokens = new ArrayList<String>();
        command=consoleInput;
        stringTokenizer=new StringTokenizer(consoleInput,",. ");
        while(stringTokenizer.hasMoreTokens())
        {
            tokens.add(stringTokenizer.nextToken());
        }
        System.out.println("Tokenized form: "+tokens);
        System.out.println("ok?");


        // lowercase it
        // spell correction if required
        // remove duplicates
        // remove stop words
        // realize keywords
        // check if applicable
        // perform action (search, select, buy etc etc)
        // retrieve results.

    }
}
