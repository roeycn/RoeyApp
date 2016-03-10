package com.example.roey.roeyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by roey on 28/02/2016.
 * setContentView(R.layout.screen3) ,
 * we are giving the adapter -> the listview_layout.xml
 * all of the urls stored under array.xml
 *
 * when the user clicks the list -> its check for internet connection first.
 */
public class Screen3 extends Activity {


    // Array of strings storing country names
    String[] countries = new String[]{
            "India",
            "Pakistan",
            "Sri Lanka",
            "China",
            "Bangladesh",
            "Nepal",
            "Afghanistan",
            "North Korea",
            "South Korea",
            "Japan"
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
            R.drawable.flagin,
            R.drawable.flag_pakistan,
            R.drawable.flagsrilanka,
            R.drawable.flagch,
            R.drawable.flagbdlarge,
            R.drawable.flagnapal,
            R.drawable.flagafghanistan,
            R.drawable.flagkn,
            R.drawable.flagsouthkorea,
            R.drawable.flagjp

    };

    // Array of strings to store currencies
    String[] currency = new String[]{
            "Indian Rupee",
            "Pakistani Rupee",
            "Sri Lankan Rupee",
            "Renminbi",
            "Bangladeshi Taka",
            "Nepalese Rupee",
            "Afghani",
            "North Korean Won",
            "South Korean Won",
            "Japanese Yen"
    };

    // Array of url strings - stored at array.xml
    String[] urls ;
    WebView ourBrow;
    int itemPosition = -1;
    TextView ivLoading ;
    TextView ivStart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);

        urls = getResources().getStringArray(R.array.urls);
        ivLoading = (TextView) findViewById(R.id.ivLoading);
        ivStart = (TextView) findViewById(R.id.ivStart);
        ivStart.setVisibility(View.VISIBLE);

        ourBrow = (WebView) findViewById(R.id.wvBrowser);
        // enable javascript
        ourBrow.getSettings().setJavaScriptEnabled(true);
        //enable completly option to zoom in\out
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        //set normal view port
        ourBrow.getSettings().setUseWideViewPort(true);
        ourBrow.setWebViewClient(new OurViewClient());


        try {
            ourBrow.loadUrl("https://en.wikipedia.org/wiki/List_of_sovereign_states");
        }catch (Exception e){
            e.printStackTrace();
        }

        // Each row in the list stores country name, currency and flag
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 10; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("txt", "Country : " + countries[i]);
            hm.put("cur", "Currency : " + currency[i]);
            hm.put("flag", Integer.toString(flags[i]));
            aList.add(hm);

        }

        // Keys used in Hashmap
        String[] from = { "flag","txt","cur" };

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt,R.id.cur};
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        final SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);
        // Getting a reference to listview of main.xml layout file
        final ListView listView = ( ListView ) findViewById(R.id.listView);
        // Setting the adapter to the listView
        listView.setAdapter(adapter);
        //   listView.setItemsCanFocus(true);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //    listView.setBackgroundColor(Color.CYAN) ;







        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {

                ivStart.setVisibility(View.GONE);
                ivLoading.setVisibility(View.VISIBLE);
                // ListView Clicked item index
                itemPosition = position;
                // Show Alert
                Toast.makeText(getApplicationContext(), "Position :" + itemPosition, Toast.LENGTH_SHORT).show();
                //   listView.setCacheColorHint(Color.BLUE);
                view.setSelected(true);
                //  ListView.setSelection(position);
                        try {
                            ourBrow.loadUrl(urls[itemPosition]);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

               isInternetAvailable();


                }

        });



    }









    public class OurViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView v, String url) {
            v.loadUrl(url);
            return true ;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            //hide loading image
            findViewById(R.id.ivLoading).setVisibility(View.GONE);
            //show webview
            findViewById(R.id.wvBrowser).setVisibility(View.VISIBLE);
        }
    }



        public void isInternetAvailable()
        {
            String TAG = null;
            NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

            if (info == null)
            {
                Log.d(TAG, "no internet connection");
                // internet connection toast
                Toast.makeText(getApplicationContext(), "no internet connection", Toast.LENGTH_LONG).show();
                // internet connection pop-up
                Intent i =  new Intent("com.example.roey.roeyapp.POPUP") ;
                startActivity(i);
            }
            else
            {
                if(info.isConnected())
                {
                    Log.d(TAG," internet connection available...");

                }
                else
                {
                    Log.d(TAG," internet connection");

                }

            }

    }
}