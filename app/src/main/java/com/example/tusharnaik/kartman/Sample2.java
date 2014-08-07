package com.example.tusharnaik.kartman;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tusharnaik.kartman.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Sample2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample2);
        Intent prevIntent = getIntent();
        final String searchQuery=prevIntent.getStringExtra("query");
        Toast toast= Toast.makeText(Sample2.this.getApplicationContext(), "searching for "+searchQuery, Toast.LENGTH_LONG);
        toast.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doSearchImpl(searchQuery);
            }
        });
        thread.start();

    }

    public static final String BASE_URL ="http://mobileapi.flipkart.net/2/discover/getSearch?store=search.flipkart.com&start=0&count=10&q=";

    private void doSearchImpl(String searchTerm) {
        URL url = null;
        try {
            url = new URL(BASE_URL+searchTerm);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection urlConnection = null;
        if(url!=null) {
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream inputStream = null;
        if(urlConnection!=null)
        {
            try {
                inputStream = urlConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(inputStream!=null)
        {
            String response = convertStreamToString(inputStream);
            processResponse(response);
        }
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    private void processResponse(String response) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(response);
        JsonObject responseElement = (JsonObject) jsonObject.get("RESPONSE");
        JsonObject product = (JsonObject)responseElement.get("product");
        System.out.println("PRODUCT");

        final ArrayList<String> titles = new ArrayList<String>();

        for (Map.Entry<String, JsonElement> jsonElementEntry : product.entrySet()) {
            String key = jsonElementEntry.getKey();
            JsonObject element = (JsonObject) jsonElementEntry.getValue();
            JsonElement mainTitle = element.get("mainTitle");
            if(mainTitle!=null)
            {
                String title = mainTitle.getAsString();
                titles.add(title);
            }
        };

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onResponse(titles);
            }
        });
    }

    private void onResponse(ArrayList<String> titles) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,titles);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
