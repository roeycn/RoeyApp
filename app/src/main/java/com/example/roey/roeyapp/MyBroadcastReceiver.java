package com.example.roey.roeyapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{


    // if we put a simple string  and press the button, a Toast will appear, containing
    // that message . This Toast is generated in the onReceive() function
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        // Extract data included in the Intent
        CharSequence intentData = intent.getCharSequenceExtra("message");
        Toast.makeText(context, "RoeyApp received the Intent's message: "+intentData, Toast.LENGTH_LONG).show();
    }

}
