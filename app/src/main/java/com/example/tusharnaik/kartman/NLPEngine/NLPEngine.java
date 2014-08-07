package com.example.tusharnaik.kartman.NLPEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by tushar.naik on 07/08/14.
 */
public class NLPEngine {

    Query query;
    public NLPEngine(String entireQuery)
    {
        query=new Query(entireQuery,null);
    }

    public void processCommand(String consoleInput) {




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
