package com.example.tusharnaik.kartman.NLPEngine;

import java.util.List;

/**
 * Created by tushar.naik on 07/08/14.
 */
public class Query {

    public String fullQuery;
    public List<String> tokens;
    public List<String> keywords;
    Query prevQuery;

    Action action;
    Object object;
    public List<String> object;

    public Query(String fullQuery, Query prev)
    {
        this.prevQuery=prev;
        this.fullQuery=fullQuery;
        tokens=Tokenizer.tokenizeString(fullQuery);
    }

    public void level3()
    {
        if(keywords.contains("compare"))
        {
            action=
        }
    }

}
