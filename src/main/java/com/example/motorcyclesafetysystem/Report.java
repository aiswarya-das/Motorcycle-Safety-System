package com.example.motorcyclesafetysystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Report extends AppCompatActivity {
    TextView location;
    String loc;
   // private FirebaseDatabase db = FirebaseDatabase.getInstance();
    //private DatabaseReference root = db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        location = (TextView) findViewById(R.id.link);
        location.setClickable(true);
        location.setMovementMethod(LinkMovementMethod.getInstance());
        Intent intent = getIntent();
        loc = intent.getStringExtra("loc");
        String text = "<a href='http://www.google.com'> Google </a>";
        location.setText(Html.fromHtml(text));

       // loc = intent.getStringExtra("loc");
        //location.setText("location");
        /*location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(loc);
                Intent intnt = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(intnt);
            }
        });*/
    }
}