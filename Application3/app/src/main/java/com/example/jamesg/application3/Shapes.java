package com.example.jamesg.application3;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JamesG on 28/06/2015.
 */
public class Shapes extends View {

    public Shapes(Context context) {
        super(context);
       // context.getString(R.string.title_activity_display_map);
        this.context = context;

    }

    List<DrawBox> allMyBoxes = new ArrayList<DrawBox>();
    List<DrawWall> allMyWalls = new ArrayList<DrawWall>();
    List<DrawTarget> allMyTargets = new ArrayList<DrawTarget>();

    MapGrid mapgrid = new MapGrid();

    DrawPlayer player;
    float cellX, cellY;
    int mapwidth;
    int mapheight;
    public Boolean iniMap = true;
    private Boolean iniBox = true;
    DrawBox currentBox;
    Boolean isBoxMoved = false;
    int currentBoxNum;
    float iniPosXP, iniPosYP;
    public static String map;
    DrawPlayer circle;
    Context context;
    private Boolean gameMode, moveR,moveL,moveT,moveB;
    DrawBox initialBox;

    String test;

    public void setGameMode(Boolean input){
        this.gameMode = input;
    }

    public void setIniMap(Boolean input){
            iniMap = input;
    }


    public void setMap(){
        if(gameMode == true){
            map = "#########|#-------#|#-------#|#-------#|#--@$---#|#-------#|#---.---#|#-------#|#########";
            mapwidth = mapgrid.mapWidth(map);
            mapheight = mapgrid.mapHeight(map);
        } else {
            map = DisplayMap.theMap;
            mapwidth = mapgrid.mapWidth(map);
            mapheight = mapgrid.mapHeight(map);
        }
    }

    public Paint paintBox(Paint paint) {
        paint.setColor(Color.BLACK);
        return paint;
    }

    public Paint paintPlayer(Paint paint) {
        paint.setColor(Color.BLACK);
        return paint;
    }


    public DrawPlayer setCircle(DrawPlayer circle) {
        this.circle = circle;
        return circle;
    }

    public void drawWall(Canvas canvas, DrawWall wall) { // MY OWN CODE
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(wall.leftSide, wall.topSide, wall.rightSide, wall.bottomSide, paint);
    }

    public void drawBox(Canvas canvas, DrawBox box) {// MY OWN CODE
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        canvas.drawRect(box.leftSide, box.topSide, box.rightSide, box.bottomSide, paint);

    }

    public void drawTarget(Canvas canvas,DrawTarget target ) { //MY OWN CODE
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        canvas.drawLine(target.iniX, target.iniY, target.endX, target.endY, paint);
        canvas.drawLine(target.iniX2, target.iniY2, target.endX2, target.endY2, paint);

    }

    public void drawCircle(Canvas canvas, DrawPlayer player) {//MY OWN CODE

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        canvas.drawCircle(player.x, player.y, player.radius, paint);
    }

    private void paintGrid(Canvas canvas) {//MY OWN CODE
        allMyTargets.clear();
        allMyWalls.clear();
        allMyBoxes.clear();

        float sWidth = getWidth();
        float sHeight = getHeight();
        float xleft, ytop, xright, ybot, initialLeftX;

        if (sWidth <= sHeight) {
            xleft = 0;
            cellX = sWidth / mapwidth;
            cellY = cellX;
            ytop = (sHeight / 2) - (cellY * (mapwidth/2));
            initialLeftX = xleft;
        } else {

            cellX = sHeight / mapwidth;
            cellY = cellX;
            xleft = (sWidth / 2) - (cellY * (mapheight / 2));
            ytop = 0;
            initialLeftX = xleft;
        }

        int l = 0;
        for (int i = 0; i < mapheight; i++) {
            //System.out.print(i+"i," + " ");

            for (int j = 0; j < mapwidth; j++) {
                String oneChar = map.substring(l, l + 1);
                char c = oneChar.charAt(0);
                //System.out.println(j+"j," + " "+ c +"c"+ " ");
                if (c != '|') {
                    if (c == '#') {
                        xright = xleft + cellX;
                        ybot = ytop + cellY;
                        DrawWall wall = new DrawWall(xleft, ytop, xright, ybot);
                        drawWall(canvas, wall);

                        allMyWalls.add(wall);

                        xleft += cellX;

                    } else if (c == '@') {
                        if (iniMap == true) {
                            xright = xleft + cellX;
                            ybot = ytop + cellY;
                            player = new DrawPlayer(xleft + (cellX / 2), ytop + (cellY / 2), cellX / 2);
                            drawCircle(canvas, player);

                            xleft += cellX;
                        } else {

                            drawCircle(canvas,player);
                            xleft += cellX;
                            iniMap = false;

                        }

                    } else if (c == '$') {
                       if (iniMap == true || iniBox == true) {
                            xright = xleft + cellX;
                            ybot = ytop + cellY;
                            DrawBox box = new DrawBox(xleft, ytop, xright, ybot);
                            drawBox(canvas, box);
                            allMyBoxes.add(box);

                        } else if (iniMap == false && iniBox == false) {
                           drawBox(canvas, currentBox);
                           allMyBoxes.set(currentBoxNum, currentBox);
                        }


                        xleft += cellX;
                    } else if (c == '-'|| c == ' ') {
                        xright = xleft + cellX;
                        ybot = ytop + cellY;
                        xleft += cellX;
                    } else if (c == '.') {
                        xright = xleft + cellX;
                        ybot = ytop + cellY;
                        DrawTarget target = new DrawTarget(xleft, ytop, xright, ybot, xright, ytop, xleft, ybot);
                        drawTarget(canvas, target);
                        allMyTargets.add(target);

                        xleft += cellX;
                    } else if (c == '*') {
                        xright = xleft + cellX;
                        ybot = ytop + cellY;
                        DrawTarget target = new DrawTarget(xleft, ytop, xright, ybot, xright, ytop, xleft, ybot);
                        drawTarget(canvas, target);
                        allMyTargets.add(target);

                        DrawBox box = new DrawBox(xleft, ytop, xright, ybot);
                        drawBox(canvas, box);
                        allMyBoxes.add(box);

                        xleft += cellX;
                    }else if (c == '+') {
                        xright = xleft + cellX;
                        ybot = ytop + cellY;
                        DrawTarget target = new DrawTarget(xleft, ytop, xright, ybot, xright, ytop, xleft, ybot);
                        drawTarget(canvas, target);
                        allMyTargets.add(target);

                        DrawPlayer player = new DrawPlayer(xleft + (cellX / 2), ytop + (cellY / 2), cellX / 2);
                        drawCircle(canvas, player);

                        xleft += cellX;
                    }
                    if (l < map.length() - 1) {

                        l++;
                    }

                } else { // if character is '|' do not do the loop, move on to another character
                    if (j < mapwidth) {
                        //System.out.println("break");
                        if (j == 0) { // character '|' must not be included on j loop and i loop
                            i--;
                        }

                        xleft = initialLeftX;
                        ytop += cellY;

                        l++;
                        break;
                    }
                }
            }
        }

    }
    public void rePaintMap(Canvas canvas){


        if (iniMap == false){
            for(int i = 0; i<allMyWalls.size();i++){
                drawWall(canvas, allMyWalls.get(i));
            }


            for (int j = 0; j < allMyBoxes.size(); j++) {
                drawBox(canvas, allMyBoxes.get(j));
            }


            for(int l = 0; l<allMyTargets.size();l++){
                drawTarget(canvas, allMyTargets.get(l));
            }



            drawCircle(canvas,player);
        }
    }

    public void matchWall(){
        for(int k = 0; k<allMyWalls.size();k++){
            DrawWall wall = allMyWalls.get(k);
            if(Math.ceil(wall.leftSide) == Math.ceil(currentBox.leftSide) && Math.ceil(wall.topSide) == Math.ceil(currentBox.topSide)){
                currentBox = initialBox;
                player.x = iniPosXP;
                player.y = iniPosYP;
                isBoxMoved = false;

                break;
            }
        }
        if(currentBox != null) {
            allMyBoxes.set(currentBoxNum, currentBox);
        }
        /*if(allMyBoxes.size() > 1 && isBoxMoved == true){
            for (int i = 0; i < allMyBoxes.size(); i++) {
                DrawBox box = allMyBoxes.get(i);
                if(i == currentBoxNum){
                        i++;
                } else if (Math.ceil(box.leftSide) == Math.ceil(currentBox.leftSide) && Math.ceil(box.topSide) == Math.ceil(currentBox.topSide)) {
                    currentBox = initialBox;
                    player.x = iniPosXP;
                    player.y = iniPosYP;
                    isBoxMoved = false;
                    break;
                }
            }
        }*/

    }



   public void GameLogic (){

       for(int j = 0; j<allMyWalls.size(); j++){
           float xwallL = allMyWalls.get(j).leftSide;
           float xwallR = allMyWalls.get(j).rightSide;
           float xwallT = allMyWalls.get(j).topSide;
           float xwallB = allMyWalls.get(j).bottomSide;
           float playerxr = player.x + cellX / 2;
           float playerxl = player.x - cellX / 2;
           float playeryt = player.y - cellY/2;
           float playeryb = player.y + cellY/2;

           if(Math.ceil(playerxr) == Math.ceil(xwallR) && Math.ceil(playeryt) == Math.ceil(xwallT)){
               player.x = iniPosXP;
               player.y = iniPosYP;

              break;
          }

       }

        for(int i=0;i<allMyBoxes.size(); i++) {
          //  DrawBox box = new DrawBox(allMyBoxes.get(i).leftSide, allMyBoxes.get(i).topSide,allMyBoxes.get(i).rightSide,allMyBoxes.get(i).bottomSide);
           float xleft = allMyBoxes.get(i).leftSide;
            float xright = allMyBoxes.get(i).rightSide;
            float ytop = allMyBoxes.get(i).topSide;
            float ybot = allMyBoxes.get(i).bottomSide;

            double playerL = Math.ceil(player.x - cellX / 2);
            double playerR = Math.ceil(player.x + cellX / 2);
            double playerT = Math.ceil(player.y - cellX / 2);
            double playerB = Math.ceil(player.y + cellX / 2);

            isBoxMoved = false;

            if (moveR == true && playerL != Math.ceil(xright) && playerR == Math.ceil(xright) && playerT == Math.ceil(ytop)){
                 xleft = xleft + cellX;
                 xright = xright + cellX;
                isBoxMoved = true;
               currentBox = new DrawBox(xleft,ytop,xright,ybot);
               iniBox = false;
                currentBoxNum = i;
                matchWall();
                break;

           } else if (moveL == true && playerR != Math.ceil(xleft) && playerL == Math.ceil(xleft) && playerT == Math.ceil(ytop)) {
                xleft = xleft - cellX;
                xright = xright - cellX;
                isBoxMoved = true;

                currentBox = new DrawBox(xleft,ytop,xright,ybot);

                iniBox = false;

                currentBoxNum = i;

                matchWall();

                break;

            } else if (moveT == true && playerB != Math.ceil(ytop) && playerL == Math.ceil(xleft) && playerT == Math.ceil(ytop)) {
                ytop = ytop - cellY;
                ybot = ybot - cellY;
                isBoxMoved = true;

                currentBox = new DrawBox(xleft,ytop,xright,ybot);

                iniBox = false;

                currentBoxNum = i;
                matchWall();
                break;

            }else if (moveB == true && playerT != Math.ceil(ybot) && playerL == Math.ceil(xleft) && playerT == Math.ceil(ytop)) {
                ytop = ytop + cellY;
                ybot = ybot + cellY;
                isBoxMoved = true;

                currentBox = new DrawBox(xleft, ytop, xright, ybot);

                iniBox = false;

                currentBoxNum = i;
                matchWall();
                break;
            }

       }
    }

    public void onDraw(Canvas canvas) {
        //drawSquare(canvas);
        if(iniMap == false) {
            rePaintMap(canvas);

        } else {
            test = "NOPE";
            paintGrid(canvas);

        }
        //demoToast();
    }


    public boolean onTouchEvent(MotionEvent event) { //automatically called when screen is touched

        int width = getWidth();
        int height = getHeight();

        int y = (int) event.getY();
        int x = (int) event.getX();
        CharSequence text = "";


        if (y < player.y && x < (player.x + cellX) && x > (player.x - cellX)) {
            moveT = true;
            moveB= false;
            moveL = false;
            moveR = false;
            iniPosYP = player.y;
            iniPosXP = player.x;
           player.y = player.y - cellY;
            GameLogic();
            text = "top";
        } else if (y > player.y && x < (player.x + cellX) && x > (player.x - cellX)) {
            moveT = false;
            moveB= true;
            moveL = false;
            moveR = false;
            iniPosYP = player.y;
            iniPosXP = player.x;
            player.y = player.y + cellY;
            GameLogic();

            text = "bottom";
        } else if (x < player.x && y < (player.y + cellY) && y > (player.y - cellY)) {
            moveT = false;
            moveB= false;
            moveL = true;
            moveR = false;
            text = "left";
            iniPosXP = player.x;
            iniPosYP = player.y;
            player.x = player.x - cellX;
            GameLogic();

        } else if (x > player.x && y < (player.y + cellY) && y > (player.y - cellY)) {
            moveT = false;
            moveB= false;
            moveL = false;
            moveR = true;
            text = "right";
            iniPosXP = player.x;
            iniPosYP = player.y;
            player.x = player.x + cellX;
            GameLogic();
        }

        //Integer.toString(y)
        int duration = Toast.LENGTH_SHORT;
        final Toast toast = Toast.makeText(context, "" + text, duration);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();

            }
        }, 500);
        iniMap = false;

        invalidate();

        return false;
    }




}
