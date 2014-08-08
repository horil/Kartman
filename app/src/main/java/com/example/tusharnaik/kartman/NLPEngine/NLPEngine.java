package com.example.tusharnaik.kartman.NLPEngine;

import android.content.Intent;
import android.net.Uri;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by tushar.naik on 07/08/14.
 */
public class NLPEngine {

    Query query;
    public NLPEngine(String entireQuery) throws IOException {
        query=new Query(entireQuery);
    }

    public String processCommand() throws JSONException
    {

        query.verbise();
        query.parseNonverbs();
        query.setAction();
        System.out.println();
        String url=createURL.queryToURL(query);
        return url;

    }
}
