package com.example.tusharnaik.kartman.NLPEngine;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.sax.StartElementListener;

import com.example.tusharnaik.kartman.StartScreen;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by tushar.naik on 07/08/14.
 */
public class Query {

    public String fullQuery;
    public List<String> tokens;
    public List<String> keywords;
    Search search;
    Browse browse;
    public List<String> verbsDict;
    public List<String> nonverbs;
    public List<String> verbs;

    StopWordRemover stopWordRemover;
    int actionEnum;

    public Query(String fullQuery) throws IOException {
        verbs =new ArrayList<String>();
        nonverbs=new ArrayList<String>();
        verbsDict=new ArrayList<String>();
        keywords=Tokenizer.tokenizeString(fullQuery);
        fillVerbs();
        this.fullQuery=fullQuery;
        //tokens=Tokenizer.tokenizeString(fullQuery);
        actionEnum=-1;
        stopWordRemover = new StopWordRemover();
        keywords=stopWordRemover.removeWords(keywords);
    }
    public void fillVerbs()
    {
        verbsDict.add("show");
        verbsDict.add("tell");
        verbsDict.add("list");
        verbsDict.add("run");
        verbsDict.add("take");
        verbsDict.add("hit");
        verbsDict.add("display");
        System.out.println(verbsDict);
    }
    public void level3()
    {
        if(keywords.contains("compare"))
        {

        }
    }
    public void verbise()
    {
        for(String s: keywords )
        {
            if(verbsDict.contains(s))
            {
                verbs.add(s);
            }
            else
            {
                nonverbs.add(s);
            }
        }
    }
        public void parseNonverbs() throws JSONException {
        String BASE_URL1="http://sherlock-large-svc15.nm.flipkart.com:25280/solr/queries/select?fl=query,totalFrequency&q=query_lc:(";
        String BASE_URL2=")&sort=totalFrequency%20desc&wt=json";

        String url;
        url = BASE_URL1+getPlusString(nonverbs)+BASE_URL2;
        JsonObject jsonObject=URLdude.convertUrlToJson(url);
        //JsonObject resp= (JsonObject) jsonObject.get("response");
         JsonArray docs=jsonObject.getAsJsonObject("response").getAsJsonArray("docs");
        System.out.println(docs);
        Iterator i = docs.iterator();
        while(i.hasNext())
        {
            JsonObject slide=(JsonObject) i.next();
            System.out.println(slide.get("query"));
            JsonElement query1=slide.get("query");
            JsonElement tf=slide.get("totalFrequency");
            System.out.println(tf.toString());
            int tF=tf.getAsInt();
            if(tF<1000) {
                nonverbs.remove(query1.getAsString());

            }

        }
        System.out.println(nonverbs);

    }
    public String getPlusString(List<String> a)
    {
        String ret="";
        for(String s:a)
        {
            ret=ret+s+"+";
        }

        if(ret.length()<=1)
        {
            return null;
        }
        return ret.substring(0, ret.length() - 1);

    }

    public void setAction()
    {

        String BASE_URL1="http://sherlock-large-svc15.nm.flipkart.com:25280/sherlock/intent?q=";
        String url;
        url = BASE_URL1+getPlusString(nonverbs);
        JsonObject jsonObject=URLdude.convertUrlToJson(url);
        //JsonObject resp= (JsonObject) jsonObject.get("response");
        JsonPrimitive docs=jsonObject.getAsJsonObject(":RESPONSE").getAsJsonObject("classification").getAsJsonPrimitive("queryType");
        System.out.println(docs.getAsString());
        if(docs.getAsString().equals("product"))
         actionEnum=0;
        else
         actionEnum=1;


    }
    public static void main(String args[]) throws JSONException, IOException {
        Query q=new Query("samsung");
        q.verbise();
        q.parseNonverbs();
        q.setAction();
        System.out.println(createURL.queryToURL(q));
    }




}
