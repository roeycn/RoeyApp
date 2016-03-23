package com.example.roey.roeyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Screen5 extends Activity implements View.OnClickListener {


    Button btnStartBroadcast;
    TextView text;
    String number = null ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen5);

        btnStartBroadcast = (Button)findViewById(R.id.btnStartBroadcast) ;
        btnStartBroadcast.setOnClickListener(this);

        // without this appcrash occurs. -Attempt to read on a null object reference by incomingcall activity
    try {
    number = IncomingCall.mInstance.number;
    }catch (Exception e){

    }



        text = (TextView) findViewById(R.id.tvNumber);
        text.setText("Incoming call from " + number);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartBroadcast:

                Intent intent = new Intent("MyCustomIntent");

                EditText et = (EditText)findViewById(R.id.extraIntent);
                // add data to the Intent
                intent.putExtra("message", (CharSequence)et.getText().toString());
                intent.setAction("com.example.roey.roeyapp.A_CUSTOM_INTENT");
                sendBroadcast(intent);

                break;
        }

    }
}