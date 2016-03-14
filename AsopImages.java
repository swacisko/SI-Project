
package asop;
import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;


public class AsopImages {
    
    public AsopImages(){
    }
    
    public static void createImages(){
        String s = "robot";
        String ss, path = "\\\\files\\students\\s384201\\Desktop\\ASOP\\src\\images\\";
        houseimages = new Image[0];
        robotimages = new Image[0];
        
        for(int i=1; i<=20; i++){
            ss = path+s+i+".jpg";
            try{
                robotimages = addImage( robotimages, ImageIO.read( new File( ss ) ) );
            }
            catch( IOException e ){
                e.printStackTrace();
            }
        }
        
        s = "house";
        for(int i=1; i<=12; i++){
            ss = path+s+i+".jpg";
            try{
                houseimages = addImage( houseimages, ImageIO.read( new File( ss ) ) );
            }
            catch( IOException e ){
                e.printStackTrace();
            }
        }
        
        try{
            sandbagimage = ImageIO.read( new File( path+"sandbag.jpg" ) );
            warehouseimage = ImageIO.read( new File( path+"warehouse.jpg" ) );
        }
        catch( IOException e ){
            e.printStackTrace();
        }
        
    }
    
    public static Image getRandomImage( Image[] table ){
        if( table.length <= 0 ){
            System.out.println( "table.length = " + table.length );
            return null;
        }
        Random r = new Random();
        return table[ r.nextInt( table.length ) ];
    }
    
    
    public static Image[] addImage( Image[] table, Image image ){
        Image[] temp = new Image[ table.length+1 ];
        for(int i=0; i<table.length; i++) temp[i] = table[i];
        temp[ table.length ] = image;
        return temp;
    }
    
    public static Image[] getHouseImages(){ return houseimages; }
    public static Image getWarehouseImage(){ return warehouseimage; }
    public static Image[] getRobotImages(){ return robotimages; }
    public static Image getSandbagImage(){ return sandbagimage; }
    
    static Image[] houseimages;
    static Image warehouseimage;
    static Image sandbagimage;
    static Image[] robotimages;
}
