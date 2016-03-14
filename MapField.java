
package asop;

import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class MapField {
    
    public MapField( Position pos ){
        position = pos;
    }
    
    public Position getPosition(){ return position; }
    public void setPosition( Position pos ){ position = pos; }
    public void setPosition( int h, int w ){ position = new Position(h,w); }
    
    public static int getFieldSize(){ return fieldSize; }
    public static void setFieldSize(int size){ fieldSize = size; }
    
    // function returns number of sandbags laid on that map field
    public int getSandbags(){ return sandbags; }
    public int increaseSandbags(){ sandbags++; return sandbags; }
    public int decreaseSandbags(){ sandbags--; return sandbags; }
    
    public boolean isBuilding(){ return isBuilding; }
    public void setIsBuilding( int state ){
        if( (isBuilding || isWarehouse) && state >= 1 ) return;
        if( state >= 1 ){
            isBuilding = true;
            image = AsopImages.getRandomImage( AsopImages.getHouseImages() );
        }
        if( state == 1 ){ // if the building is supposed to be a warehouse
            isWarehouse = true;
            sandbags = 50;
            image = AsopImages.getWarehouseImage();
        }
        else if( state == -1 ){
            if( isWarehouse ) sandbags = 0;
            isWarehouse = false;
            isBuilding = false;
            image = null;
            color = Color.ORANGE;
        }
    }
    
    public boolean isAccessibleForRobot(){
        if( isBuilding && !isWarehouse ) return false;
        else return true;
    }
    
    public Robot[] getRobotsOnField(){
        Robot[] robots = gameset.getRobots();
        Robot[] temp = new Robot[0];
        for( Robot r : robots ){
            if( r.getPosition().equals( position ) ){
                temp = Arrays.copyOf( temp, temp.length+1 );
                temp[ temp.length-1 ] = r;
            }
        }
        return temp;
    }
    
    public int howManyRobotsOnField(){
        return getRobotsOnField().length;
    }
    
    
    public boolean isWarehouse(){ return isWarehouse; }
 /*   public void setIsWarehouse( boolean state ){
        if(state) isBuilding = state;
        isWarehouse = state;
        image = AsopImages.getWarehouseImage();
    }*/
        
    
    public void setColor( Color col ){ color = col;}
    public Color getColor(){ return color;}
    
    public void setAsopGameSet( AsopGameSet gameset ){ this.gameset = gameset; }
    
    
    public void drawField( Graphics2D gr ){
        Rectangle2D.Double r = new Rectangle2D.Double( position.getW(), position.getH(), fieldSize, fieldSize );      
        
        if( image != null && !image.equals( AsopImages.getSandbagImage() ) ){
            if( isWarehouse ) color = Color.BLACK;
            else color = Color.RED;
            gr.setColor(color);
            gr.fill(r);
            gr.drawImage( image, (int)position.getW() + fieldSize/10, (int)position.getH()+fieldSize/10, 9*fieldSize/10, 9*fieldSize/10, null );
        }
      /*  else if( isBuilding ){
            image = AsopImages.getRandomImage( AsopImages.getHouseImages() );
            gr.drawImage( image, (int)position.getW(), (int)position.getH(), fieldSize, fieldSize, null );
        }*/
        else if( sandbags > 0 ){
            image = AsopImages.getSandbagImage();
            gr.drawImage( image, (int)position.getW(), (int)position.getH(), fieldSize, fieldSize, null );
        }
        else{
            gr.setColor( color );
            gr.fill(r);
        }
       
        gr.setColor( Color.BLACK );
        gr.draw(r);     
        
    }
    
    
    private static int fieldSize = 35;
    private Position position = null;    
    private int sandbags = 0;
    private boolean isBuilding = false; 
    private boolean isWarehouse = false;
    private boolean isFlooded = false;
    private Color color = Color.ORANGE;
    private Image image = null;
    
    private AsopGameSet gameset = null;
}
