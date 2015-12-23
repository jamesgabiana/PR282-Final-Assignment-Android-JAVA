package com.example.jamesg.application3;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Shapes shapes;
    MapGrid mapgrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shapes = new Shapes(this);
        shapes.setGameMode(true);
        shapes.allMyWalls.clear();
        shapes.allMyBoxes.clear();
        shapes.allMyWalls.clear();
        shapes.setMap();

        setContentView(shapes);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


