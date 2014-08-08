package com.example.tusharnaik.kartman;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tusharnaik.kartman.NLPEngine.NLPEngine;
import com.example.tusharnaik.kartman.R;

import org.json.JSONException;

import java.io.IOException;

public class ResultScreen extends ActionBarActivity {

    TextView tvQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        Intent prevIntent=getIntent();
        String searchQuery=prevIntent.getStringExtra("query");
        searchQuery=searchQuery.toLowerCase();
        Toast toast= Toast.makeText(ResultScreen.this.getApplicationContext(), "searching for "+searchQuery, Toast.LENGTH_LONG);
        toast.show();


        final NLPEngine[] nlp = new NLPEngine[1];


        final String finalSearchQuery = searchQuery;
        Thread th=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        nlp[0] = new NLPEngine(finalSearchQuery);
                        String url;
                        url = nlp[0].processCommand();

                        final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });


        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result_screen, menu);
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
