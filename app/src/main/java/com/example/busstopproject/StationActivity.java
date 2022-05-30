package com.example.busstopproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class StationActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void onClick(View v){
        Intent i1 = null;
        switch(v.getId()) {
            case R.id.button1:
                i1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)10-111-2222"));
                break;
            case R.id.button2:
                i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.833531, 127.151538"));
                break;
            case R.id.button3:
                i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bu.ac.kr"));
                break;

        }
        startActivity(i1);
    }

}