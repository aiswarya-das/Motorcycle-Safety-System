package com.example.motorcyclesafetysystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Broadcast_example extends AppCompatActivity {
private static final int MY_PERMISSION_REQUEST_RECEIVE_SMS = 0;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    TextView msgtv,numbertv;

    MyReceiver myReceiver = new MyReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
             msgtv.setText(msg);
             numbertv.setText(phoneNo);
            //gotoUrl("https://www.google.com/maps/?q=10.455386,76.091200");
           // String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 10.455386,76.091200);
           // System.out.println(uri);
              String uri = "geo:"+msg;
            Intent intnt = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            if(phoneNo.equals("+919605229547")) {
                context.startActivity(intnt);
            }
        }
    };
   /* private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);startActivity(new Intent(Intent.ACTION_VIEW,uri));
        }
    */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver,new IntentFilter(SMS_RECEIVED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_example);

        msgtv = findViewById(R.id.msgtxt);
        numbertv = findViewById(R.id.phonetxt);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {


            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSION_REQUEST_RECEIVE_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case MY_PERMISSION_REQUEST_RECEIVE_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Not permitted!", Toast.LENGTH_SHORT).show();
            }

        }

    }
}