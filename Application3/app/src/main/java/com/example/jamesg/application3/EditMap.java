package com.example.jamesg.application3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditMap extends Activity implements View.OnClickListener {

    final String []Filemap = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_map);

        EditText theMapString = (EditText) findViewById(R.id.mapString);
        theMapString.setGravity(Gravity.CENTER);
        StringBuilder file = new StringBuilder();
        Intent intent = getIntent();
        int theWidth = intent.getIntExtra("theWidth", 1);
        int theHeight = intent.getIntExtra("theHeight", 1);

        for (int i = 0; i < theWidth; i++) {
            if (i > 0) {
                file.append('\n');
            }
            for (int x = 0; x < theHeight; x++) {
                file.append('-');
            }
        }
        String theFile = file.toString();
        theMapString.setText(theFile);


        Button doSaveBtn = (Button) findViewById(R.id.btnCreate);

        doSaveBtn.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {

               EditText theC = (EditText) findViewById(R.id.mapString);
               String theCode = theC.getText().toString();
               String Filemap = theCode.replace('\n', '|');
               Intent intent = new Intent(getApplicationContext(), DisplayMap.class);
               intent.putExtra("map", Filemap);

               startActivity(intent);
           }
        });

    }

    @Override
    public void onClick(View v) {

    }


}