package com.example.tusharnaik.kartman.NLPEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tushar.naik on 07/08/14.
 */
public class Query {

    public String fullQuery;
    public List<String> tokens;
    public List<String> keywords;
    Search search;
    Browse browse;
    public List<String> verbs;
    public List<String> nonverbs;

    int actionEnum;

    public Query(String fullQuery, Query prev)
    {
        verbs=new ArrayList<String>();
        nonverbs=new ArrayList<String>();


        this.fullQuery=fullQuery;
        tokens=Tokenizer.tokenizeString(fullQuery);
        actionEnum=-1;
    }
    public void level3()
    {
        if(keywords.contains("compare"))
        {

        }
    }



}
