package com.example.jamesg.application3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayMap extends ActionBarActivity {

    LoadMap loadmap;
    public static String theMap;
    Shapes shapes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_display_map);
        shapes = new Shapes(this);

        shapes.setIniMap(true);
        shapes.setGameMode(false);
        shapes.allMyWalls.clear();
        shapes.allMyBoxes.clear();
        shapes.allMyWalls.clear();



        //loadmap = new LoadMap(this);

        Bundle bundle = getIntent().getExtras();
        String theMessage = bundle.getString("map");
        theMap = theMessage;
        shapes.setMap();


       // loadmap.toastDisplay();
       // loadmap.setBackgroundColor(Color.CYAN);
        setContentView(shapes);
    }

    public class LoadMap extends View {
        Paint paint = new Paint();
        Context context;

        public LoadMap(Context context) {
            super(context);
            this.context = context;
        }

        MapGrid mapgrid = new MapGrid();

        //int mapH = mapgrid.mapHeight(theMap);
       // int mapWidth = mapgrid.mapHeight(theMap);

       public int mapH(String map){
            int mapH = mapgrid.mapHeight(map);

           return mapH;
       }

        public void toastDisplay(){
            int duration = Toast.LENGTH_LONG;
            final Toast toast = Toast.makeText(context, "" +  mapH(theMap), duration);
            toast.show();
        }

    }


}