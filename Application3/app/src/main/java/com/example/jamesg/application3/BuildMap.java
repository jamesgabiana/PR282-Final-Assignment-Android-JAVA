package com.example.jamesg.application3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class BuildMap extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_map);

        Button create = (Button) findViewById(R.id.btnCreateMap);
        create.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                EditText theWidth = (EditText)findViewById(R.id.mapWidth);
                EditText theHeight = (EditText) findViewById(R.id.mapHeigth);
                startActivity(new Intent(getApplicationContext(), EditMap.class));

                int theWidthValue = Integer.parseInt(theWidth.getText().toString());
               int theHeightValue = Integer.parseInt(theHeight.getText().toString());

                Intent intent = new Intent(BuildMap.this, EditMap.class);
                intent.putExtra("theWidth", theWidthValue);
                intent.putExtra("theHeight", theHeightValue);
                startActivity(intent);

            }
        });
    }



}
