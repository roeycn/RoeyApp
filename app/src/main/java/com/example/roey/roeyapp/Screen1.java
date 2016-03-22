package com.example.roey.roeyapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;



public class Screen1 extends Activity implements View.OnClickListener {

    public static boolean drawEverything = true;
    GraphicsView graphics;
    Button bColorSelect;
    Button bRandom;
    Button bClean;
    ImageButton bSave;
    ImageButton bSendMail;
    boolean canW , canR ;

    public static String filename = "MySharedString";
    //EditText sharedData;
    File path = null;

    public static Screen1 mInstance = null;
    private String state;

    final Context context = this;


    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        //      setContentView(new GraphicsView(this));
        setContentView(R.layout.screen1);
        graphics = (GraphicsView) findViewById(R.id.snake);

        PopupSaveScreen1 SaveScreen1 = new PopupSaveScreen1();

        drawEverything = true;


        bColorSelect = (Button) findViewById(R.id.bColorSelect);
        bRandom = (Button) findViewById(R.id.bRANDOM);
        bClean = (Button) findViewById(R.id.bCLEAN);
        bSave = (ImageButton) findViewById(R.id.ibSave);
        bSendMail = (ImageButton) findViewById(R.id.ibSendMail);
   //     sharedData = (EditText) findViewById(R.id.etSavedName);

        bColorSelect.setOnClickListener(this);
        bRandom.setOnClickListener(this);
        bClean.setOnClickListener(this);
        bSave.setOnClickListener(this);
        bSendMail.setOnClickListener(this);

        checkState();

        mInstance = this;





    }

    private void checkState() {
        state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // read and write
            Toast toast = Toast.makeText(getApplicationContext(),"can read and can write - ok", Toast.LENGTH_SHORT );
            toast.show();
            canW = canR = true ;
        } else if (state.equals((Environment.MEDIA_MOUNTED_READ_ONLY))) {
            // read but cant write
            Toast toast = Toast.makeText(getApplicationContext(),"can read - ok ,cannot write ", Toast.LENGTH_SHORT );
            toast.show();
            canW = false;
            canR = true ;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "cannot read  ,cannot write ", Toast.LENGTH_SHORT);
            toast.show();
            canW = canR = false;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawEverything = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bColorSelect:



                graphics.clean = 0;
                graphics.random = 0;
                graphics.drawEnabled = true;

                // Android Material Color Picker Dialog
                // adding compile 'com.pes.materialcolorpicker:library:1.0.+' to  build.gradle file
                // https://github.com/Pes8/android-material-color-picker-dialog

             //   graphics.r = 255;
             //   graphics.g = 0;
             //   graphics.b = 0;


                final ColorPicker cp = new ColorPicker(Screen1.this, graphics.r, graphics.g, graphics.b);

                /* Show color picker dialog */
                cp.show();

            /* On Click listener for the dialog, when the user select the color */
                Button okColor = (Button)cp.findViewById(R.id.okColorButton);

                okColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

        /* You can get single channel (value 0-255) */
                        graphics.r = cp.getRed();
                        graphics.g = cp.getGreen();
                        graphics.b = cp.getBlue();



        /* Or the android RGB Color (see the android Color class reference) */
             //           selectedColorRGB = cp.getColor();
                        bColorSelect.setBackgroundColor(cp.getColor());

                        cp.dismiss();
                    }
                });



                break;

            case R.id.bRANDOM:

                graphics.clean = 0;
                graphics.random = 1;
                graphics.drawEnabled = true;
                break;

            case R.id.bCLEAN:

                graphics.clean = 1;
                graphics.invalidate();
                graphics.drawEnabled = false;
                break;

            case R.id.ibSave:


                if (canW == canR == true ) {

                    // we will go to popupsave activity and display it as dialog.

                    Intent i = new Intent("com.example.roey.roeyapp.POPUPSAVEDSCREEN1");
                    startActivity(i);

                }

                    // another way to display dialog:
                    /*
                    graphics.invalidate();
                    // create a Dialog component
                    final Dialog dialog = new Dialog(context);
                    Button bDismiss = (Button) dialog.findViewById(R.id.dismiss);
                    Button bSave = (Button)dialog.findViewById(R.id.bsave);
                    //tell the Dialog to use the dialog.xml as it's layout description
                    dialog.setContentView(R.layout.popupsavescreen1);
                    dialog.setTitle("Android Custom Dialog Box");
                    bDismiss.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    bSave.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            saveImage();
                        }
                    });
                    dialog.show();
                }
                */

                 //   saveImage();
                break;

            case R.id.ibSendMail:

/*
                graphics.invalidate();
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                graphics.setDrawingCacheEnabled(true);
                graphics.buildDrawingCache();
                Bitmap bitmap = graphics.getDrawingCache();


                Intent data = new Intent();
                int columnIndex;
                String  attachmentFile;

                Uri URI = null;
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                cursor.moveToFirst();
                columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                attachmentFile = cursor.getString(columnIndex);
                Log.e("Attachment Path:", attachmentFile);
                URI = Uri.parse("file://" + attachmentFile);
                cursor.close();



                String pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,"title", null);
*/

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"roeyc@fourieredu.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Roey App - my draw");
                emailIntent.putExtra(Intent.EXTRA_TEXT   , "Take a look at what that i have painted");
             //   emailIntent.putExtra(Intent.EXTRA_STREAM, bitmap);
              //  emailIntent.putExtra(Intent.EXTRA_STREAM, URI);

                try {
                    startActivity(Intent.createChooser( emailIntent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Screen1.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                break;


        }
    }

/*
    public void saveImage() {
        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String f = sharedData.getText().toString();
        File imageFile = new File(path, f + ".png");
        try {
            // option 1
            //  graphics.layout(0, 0, graphics.getMeasuredWidth(), graphics.getMeasuredHeight());
            //   Bitmap bitmap = Bitmap.createBitmap(graphics.getMeasuredWidth(), graphics.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            // option 2
            graphics.setDrawingCacheEnabled(true);
            graphics.buildDrawingCache();
            Bitmap bitmap = graphics.getDrawingCache();

            FileOutputStream os = new FileOutputStream(imageFile);
            //   ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            //  PNG is a lossless format, the compression factor (100) is ignored
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            //   os.write(bytes.toByteArray());
            os.flush();
            os.close();
            //    MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "image", "bla bla bla");
            Toast t = Toast.makeText(Screen1.this, "File has been saved", Toast.LENGTH_LONG);
            t.show();
            // update files for the user to use
            MediaScannerConnection.scanFile(Screen1.this,
                    new String[]{imageFile.toString()},
                    null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Toast t = Toast.makeText(Screen1.this, "Scan Complete", Toast.LENGTH_LONG);
                            t.show();
                        }
                    });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */
    
}



