package com.example.tusharnaik.kartman;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class StartScreen extends ActionBarActivity {

    protected static final int RESULT_SPEECH = 1;
    Button bOkKartman, sendButton;
    EditText etOkKartman;
    TextView tvOkKartman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        etOkKartman = (EditText) findViewById(R.id.editText1);
        tvOkKartman = (TextView) findViewById(R.id.textView2);
        sendButton= (Button) findViewById(R.id.button2);

        etOkKartman.setText("");
        tvOkKartman.setText("");
        etOkKartman.setHint("Type text here or use the button");
        bOkKartman=(Button)findViewById(R.id.button1);
        bOkKartman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                   // txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tvOkKartman.getText().length()>0)
                {
                    Intent sendIntent=new Intent(StartScreen.this, ResultScreen.class);
                    sendIntent.putExtra("query",tvOkKartman.getText());
                    startActivity(sendIntent);
                }
                else
                {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! You havent searched for anything",
                            Toast.LENGTH_SHORT);
                    t.show();
                }


            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    tvOkKartman.setText(text.get(0));
                    etOkKartman.setHint(text.get(0));
                }
                break;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_screen, menu);
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
