
package asop;
import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;



public class Robot {
    
    public Robot(  Position pos ){
        ID = getFreeID();
        image = AsopImages.getRandomImage( AsopImages.getRobotImages() );
        position = pos;
    }
    
    public Position getPosition(){ return position; }
    public void setPosition( Position pos ){ position = pos; }
    
    public int getFreeID(){
        nextID++;
        return nextID;
    }
    
    public static void decreaseNextID(){ nextID--; }
    
    private boolean moveRobot( Position pos ){
        MapField field = AsopGameSet.getMapField(pos);
        if( field == null || field.isAccessibleForRobot() == false ) return false;
        position = pos;
        return true;
    }
    
    // return true, if the robot can move left
    public boolean moveLeft(){ return moveRobot( new Position( position.getW()-MapField.getFieldSize(), position.getH() ) );    }
    
    public boolean moveRight(){ return moveRobot( new Position( position.getW()+MapField.getFieldSize(), position.getH() ) );   }
    
    public boolean moveUp(){ return moveRobot( new Position( position.getW(), position.getH()-MapField.getFieldSize() ) );    }
    
    public boolean moveDown(){ return moveRobot( new Position( position.getW(), position.getH()+MapField.getFieldSize() ) );    }
    
    public int getID(){ return ID; }
    public void setID( int ID ){ this.ID = ID; }
    
    public void drawRobot( Graphics2D gr ){
        if( image == null ){
            gr.setColor( Color.BLACK );
            gr.fill( new Rectangle2D.Double( position.getW(), position.getH(), MapField.getFieldSize(), MapField.getFieldSize() ) );
        }
        else{
            gr.drawImage( image, (int)position.getW(), (int)position.getH(), MapField.getFieldSize(), MapField.getFieldSize(), null );
        }
        
    }
    
    
    private static int nextID = 0;
    private int ID;
    private Position position;
    private RobotAction[] myActions;
    private Image image = null;
    
}
