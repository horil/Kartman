package com.example.tusharnaik.kartman.NLPEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by tushar.naik on 07/08/14.
 */
public class Tokenizer {
    private static ArrayList<String> tokens;

    public static List<String> tokenizeString(String query)
    {
        StringTokenizer stringTokenizer;
        tokens = new ArrayList<String>();
        String command=query;

        stringTokenizer=new StringTokenizer(query,",. ");
        while(stringTokenizer.hasMoreTokens())
        {
            tokens.add(stringTokenizer.nextToken());
        }
        return tokens;
    }

    public static void main(String[] args) {

        System.out.println(tokenizeString("samsung galaxy s5"));
    }
}
