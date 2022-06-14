package com.example.motorcyclesafetysystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    Button lock,unlock,track,report;
    String date;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference("Location");
    SimpleDateFormat simpleDateFormat;
    ReceiveSms receiveSms = new ReceiveSms() {

        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);

            String label = "Location";
            String uriBegin = "geo:" + msgBody;
            String query = msgBody + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=17";
            Uri uri = Uri.parse(uriString);
            Intent intnt = new Intent(Intent.ACTION_VIEW, uri);
            if (msg_from.equals("+919496934460")) {
                Calendar calendar = Calendar.getInstance();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
                    date = simpleDateFormat.format(calendar.getTime());
                }
                HelperClass helperClass = new HelperClass(uriString,date);
                root.child(date).setValue(helperClass);

                context.startActivity(intnt);
            }
           }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiveSms,new IntentFilter(SMS_RECEIVED));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiveSms);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lock = (Button) findViewById(R.id.lock);
        unlock = (Button) findViewById(R.id.unlock);
        track = (Button) findViewById(R.id.track);
        report = (Button)findViewById(R.id.report);


        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserList.class);
                startActivity(intent);
            }
        });


        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){

                    sendMessage();

                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},100);

                }
            }
        });

        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){


                    sendMessage2();

                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},101);

                }
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){


                    sendMessage3();

                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},102);

                }
            }
        });

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&&checkSelfPermission(Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},1000);
        }
    }


    private void sendMessage() {
        String phone = "9496934460";
        String msg = "lock";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone,null,msg,null,null);
        Toast.makeText(getApplicationContext(),"locked!",Toast.LENGTH_LONG).show();

    }
    private void sendMessage2() {
        String phone = "9496934460";
        String msg = "unlck";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone,null,msg,null,null);
        Toast.makeText(getApplicationContext(),"Unlocked!",Toast.LENGTH_LONG).show();

    }
    private void sendMessage3() {
        String phone = "9496934460";
        String msg = "track";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone,null,msg,null,null);
        Toast.makeText(getApplicationContext(),"Track!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode ==100&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

            sendMessage();

        }
        else if (requestCode ==101&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendMessage2();
        }
        else if (requestCode ==102&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendMessage3();
        }
        else if (requestCode ==1000&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(getApplicationContext(), "permission denied!", Toast.LENGTH_SHORT).show();
        }
    }
}