package com.example.jamesg.application3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Intro extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button play = (Button) findViewById(R.id.btnStartGame);
        play.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

        Button build = (Button) findViewById(R.id.btnBuildMap);
        build.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BuildMap.class));

            }
        });
    }



}