package com.example.tusharnaik.kartman;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tusharnaik.kartman.R;

public class Sample extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        final EditText editText1;
        final TextView tv1=(TextView) findViewById(R.id.textView1);
        final TextView tv2=(TextView) findViewById(R.id.textView2);
        editText1=(EditText) findViewById(R.id.editText1);
        Button b1=(Button) findViewById(R.id.button1);

        tv2.setText("");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv2.setText(editText1.getText().toString());
                Intent intent=new Intent( Sample.this, Sample2.class);
                intent.putExtra("query",editText1.getText().toString());
                startActivity(intent);
                //Toast asd = Toast.makeText(MyActivity.this.getApplicationContext(),"You just pressed a button",Toast.LENGTH_LONG);
                //asd.show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample, menu);
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
