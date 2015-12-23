package com.example.jamesg.application3;

import android.graphics.drawable.shapes.Shape;

/**
 * Created by JamesG on 29/06/2015.
 */
public class MapGrid {

    public int mapHeight(String map){//My own Code
        int l=0, k=0;
        for(int i=0;i<map.length();i++){
            //System.out.print(i+"i," + " ");
            String oneChar = map.substring(l, l + 1);
            char c = oneChar.charAt(0);

            if(c == '|'){
                k++;
            }

            l++;
        }

        return k+1;
    }

    public int mapWidth(String map){//My Own Code
        int l=0, k=0, j=0,m=0;
        //System.out.print(map.length());
        for(int i=0;i<map.length();i++){

            String oneChar = map.substring(l, l + 1);
            char c = oneChar.charAt(0);
            //System.out.print(c + " "+i+"i," + " ");
            if (c != '|'){
                m++;
                if(m>j){
                    j = m;
                    //System.out.println(m+"m," + " "+j+"j"+" ");
                }
            }else{

                m=0;
                i--;
            }

            if (l>= map.length()-1){
                break;
            } else{
                l++;
            }
            //System.out.println(l+"l," + " ");
        }

        return j;
    }

}
