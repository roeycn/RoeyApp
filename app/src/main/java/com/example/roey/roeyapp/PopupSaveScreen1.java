package com.example.roey.roeyapp;


import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by roey on 03/03/2016.
 */
public class PopupSaveScreen1 extends Activity implements View.OnClickListener {

    EditText fileName ;
    Button bDismiss;
    Button bSave ;
    File path = null;
    public boolean saveOk = false ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupsavescreen1);

        bDismiss = (Button)findViewById(R.id.dismiss);
        bSave = (Button)findViewById(R.id.bsave);
        fileName = (EditText) findViewById(R.id.etFileName);

    //    fileName.setOnClickListener(this);
        bDismiss.setOnClickListener(this);
        bSave.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.etFileName:

        //        fileName.requestFocus();
          //      InputMethodManager imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);
           //     imm.showSoftInput(fileName, InputMethodManager.SHOW_IMPLICIT);

             ///   fileName.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
             //   fileName.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
                break;

            case R.id.dismiss:

                finish();
                break;


            case R.id.bsave:
                if(Screen1.mInstance != null){
                 //   Screen1.mInstance.graphics
                    Screen1.mInstance.graphics.invalidate();
                    path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    String f = fileName.getText().toString();
                    File imageFile = new File(path, f + ".png");
                    try {
                        // option 1
                        //  graphics.layout(0, 0, graphics.getMeasuredWidth(), graphics.getMeasuredHeight());
                        //   Bitmap bitmap = Bitmap.createBitmap(graphics.getMeasuredWidth(), graphics.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                        // option 2
                        Screen1.mInstance.graphics.setDrawingCacheEnabled(true);
                        Screen1.mInstance.graphics.buildDrawingCache();
                        Bitmap bitmap = Screen1.mInstance.graphics.getDrawingCache();

                        FileOutputStream os = new FileOutputStream(imageFile);
                        //   ByteArrayOutputStream bytes = new ByteArrayOutputStream();

                        //  PNG is a lossless format, the compression factor (100) is ignored
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                        //   os.write(bytes.toByteArray());
                        os.flush();
                        os.close();
                        //    MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "image", "bla bla bla");
                        Toast t = Toast.makeText(PopupSaveScreen1.this, "File has been saved", Toast.LENGTH_LONG);
                        t.show();
                        // update files for the user to use
                        MediaScannerConnection.scanFile(PopupSaveScreen1.this,
                                new String[]{imageFile.toString()},
                                null,
                                new MediaScannerConnection.OnScanCompletedListener() {
                                    @Override
                                    public void onScanCompleted(String path, Uri uri) {
                                        Toast t = Toast.makeText(PopupSaveScreen1.this, "Scan Complete", Toast.LENGTH_LONG);
                                        t.show();
                                    }
                                });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                }

            saveOk = true ;

             finish();
        }
    }


