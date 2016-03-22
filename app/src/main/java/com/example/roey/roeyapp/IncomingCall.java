package com.example.roey.roeyapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class IncomingCall extends BroadcastReceiver
{

    Context mContext;
    String number;
    public static IncomingCall mInstance = null;


    @Override
    public void onReceive(Context mContext, Intent intent)
    {
        try
        {

            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            mInstance = this;


            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
            {


            //    mobileNumber = intent.getExtras().getString("number");

                number =intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);


                mContext.startActivity(new Intent(mContext, Screen5.class).putExtra("number", number)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                Toast.makeText(mContext, "Phone Is Ringing: " + number, Toast.LENGTH_LONG).show();
/*
                Intent i = new Intent();
                i.setClassName("com.example.roey." , "com.example.roey.roeyapp.SCREEN5");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
*/


            }

            if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
                Toast.makeText(mContext, "Call Recieved", Toast.LENGTH_LONG).show();
                // Your Code
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE))
            {

                Toast.makeText(mContext, "Phone Is Idle", Toast.LENGTH_LONG).show();
                // Your Code

            }
        }
        catch(Exception e)
        {
            //your custom message
        }

    }

}