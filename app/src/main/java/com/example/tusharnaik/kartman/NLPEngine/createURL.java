package com.example.tusharnaik.kartman.NLPEngine;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.List;

import static com.example.tusharnaik.kartman.NLPEngine.URLdude.convertUrlToJson;

/**
 * Created by gajendra.dadheech on 08/08/14.
 */
public class createURL {

    public static String queryToURL(Query q)
    {
        String url_begin = "http://w3-web1.nm.flipkart.com/dl/search?q=";
        String url_end = "&as=off&as-show=off&otracker=start";
        String resp = null;
        if(q.actionEnum == 0)
        {
            resp = buildSearchURL(q.nonverbs,url_begin,url_end);
        }
        else if(q.actionEnum == 1)
        {
            resp = buildBrowseURL(q.nonverbs,url_begin,url_end);
        }
        else if(q.nonverbs.contains("offers"))
        {
            if(q.nonverbs.contains("today"))
            {
                resp = "http://www.flipkart.com/offers/deal-of-the-day?&icmpid=foz_menu_dotd";
            }
            else if (q.nonverbs.contains("top"))
            {
                resp = "http://www.flipkart.com/offers?&icmpid=foz_menu_top";
            }
        }

        return resp;
    }

    private static String buildBrowseURL(List<String> non_verbs, String begin, String end) {
        String url =  new String();
        url = url.concat(begin);

        for (String n1 : non_verbs) {
            n1 = n1.replaceAll("\"","");
            url = url.concat(n1);
            url = url.concat("+");
        }
        url = url.substring(0,url.length()-1);
        url.concat(end);
        return url;
    }

    private static String buildSearchURL(List<String> nonverbs, String begin, String end) {
       String  indexer_begin = "http://sherlock-indexer1.nm.flipkart.com:25280/sherlock/stores/all/select?sort=relevance&products.start=0&contributors.start=0&products.count=120&contributors.count=120&q=";

       String indexer_end = "&enable-augment=true&enable-igor=true&disable-filter-count=true&enable-new-grouping=true&intent-ranking=true&bucket-ranking=true&enable-compatible-search=true&disable-mp3=true&auto-correct=1&context.internal=false";

        for (String i1 : nonverbs) {
            indexer_begin = indexer_begin.concat(i1);
            indexer_begin = indexer_begin.concat("+");
        }
        indexer_begin = indexer_begin.concat(indexer_end);
        JsonObject json = convertUrlToJson(indexer_begin);
        String FSN = String.valueOf(json.getAsJsonObject("RESPONSE").getAsJsonObject("products").getAsJsonArray("ids").get(0));
        FSN = FSN.replaceAll("\"","");
        String resp = new String();
        resp = resp.concat(begin);
        resp = resp.concat(FSN);
        resp = resp.concat(end);
        return resp;
    }

    public static void main(String[] args) throws IOException {

        createURL c1 = new createURL();
        Query q = new Query("Samsung galaxy s4");
        q.actionEnum = 1;
        q.verbs.add("show");
        q.nonverbs.add("samsung");
        q.nonverbs.add("galaxy");
        q.nonverbs.add("s4");
        String s = c1.queryToURL(q);

        System.out.println(s);
    }

}

