

/*
This is component which represents only the map of the world on which our robots move and execute their commands

*/

package asop;
import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;


public class asopGridComponent extends JComponent {

    public asopGridComponent( int width, int height ) {
        setSize(new Dimension(width,height));
        
        initializeMapFields();
        
        addMouseListener( mousehandler );
    }
    
    
    @Override
    public void paintComponent( Graphics g ){
        Graphics2D gr = (Graphics2D)g;
        drawMapFields(gr);        
        drawRobots(gr);        
    }
    
    public MapField[][] getMap(){ return mapa; }
    
    public MapField getMapField( int w, int h ){
        return mapa[w][h];
    }    
    public void setMapField( int w, int h, MapField field ){
        mapa[w][h] = field;
    }    
    public int getMapWidth(){ return mapWidth; }
    public int getMapHeiht(){ return mapHeight; }    
    public void initializeMapFields(){
        final int VALUE = 30;
        //MapField.setFieldSize( min( width,height )/VALUE ); // here i set the field size - in this case there will be about 30 squaresin that component
        
        int FS = MapField.getFieldSize();
        int hh = getHeight() / FS - VALUE/7;
        int ww = getWidth() / FS - VALUE/15;
        mapWidth = ww;
        mapHeight = hh;
        
        mapa = new MapField[ww][hh];
        for( int h = 0; h < hh; h++ ){
            for( int w = 0; w < ww; w++ ){
                mapa[w][h] = new MapField( new Position( w*FS, h*FS ) );
            }            
        }        
    }
    
    private void drawMapFields( Graphics2D g ){
        for( MapField[] it : mapa ){
            for( MapField tt : it ){
                tt.drawField(g);
            }        
        }
    }
    private void drawRobots( Graphics2D g ){
        Robot[] robots = gameset.getRobots();
        if( robots == null ) return;
        for( Robot r : robots ){
            r.drawRobot(g);
        }
    }
    
    public void setBuildNow( int state ){ buildNow = state;  }
  //  public void setEraseNow( boolean state ){ eraseNow = state; }
    
    private MapField getCorrespondingMapField( double w, double h ){
        int fieldsize = MapField.getFieldSize();
        for( int i=0; i<mapWidth; i++ ){
            for(int k=0; k<mapHeight; k++){
                if( w >= i* fieldsize && w <= (i+1)*fieldsize && h >= k*fieldsize && h <= (k+1)*fieldsize ) return mapa[i][k];
            }
        }
        
        return null;
    }
     
    public void setAsopGameSet( AsopGameSet gameset ){
        this.gameset = gameset;
        for( MapField[] it : mapa ){
            for( MapField itr : it ){
                itr.setAsopGameSet(gameset);
            }
        }
    }
    
    private AsopGameSet gameset = null;
    private MapField[][] mapa;
    private int mapWidth = 0, mapHeight = 0;
    private MouseHandler mousehandler = new MouseHandler();
    
    private int buildNow = 0;
   // private boolean eraseNow = false;
    
    
    //*************************************************************************************           MOUSE ACTIONS
    
     private class MouseHandler extends MouseAdapter{   // x-8    y-30
        @Override
        public void mousePressed( MouseEvent event ){
            Point2D eventPoint = new Point2D.Double( event.getPoint().getX(), event.getPoint().getY() ); // why do i obtain wrong coordinates?
           
            
        }
        
        @Override
        public void mouseClicked( MouseEvent event ){
            Point2D eventPoint = new Point2D.Double( event.getPoint().getX(), event.getPoint().getY() ); // why do i obtain wrong coordinates?
            MapField field = getCorrespondingMapField( eventPoint.getX(), eventPoint.getY() );
            if( field == null ) return;
            if( buildNow == AsopConstants.ADD_ROBOT ) gameset.addRobot( new Robot( field.getPosition() ));
            else if( buildNow == AsopConstants.ERASE_ROBOT ) gameset.removeRobot( field );
            else if( buildNow >= AsopConstants.BUILD_WAREHOUSE && buildNow <= AsopConstants.BUILD_HOUSE ) field.setIsBuilding(buildNow);
            else if( buildNow == AsopConstants.ERASE_BUILDING ) field.setIsBuilding( buildNow );
            repaint();
        }
        
        @Override
        public void mouseReleased( MouseEvent event ){
            Point2D eventPoint = new Point2D.Double( event.getPoint().getX(), event.getPoint().getY() );
            
            
        }
        
    } // end of MouseHandler class
    
    private class MouseMotionHandler implements MouseMotionListener {
        @Override
        public void mouseMoved( MouseEvent event ){
        }
        
        @Override
        public void mouseDragged( MouseEvent event ){
            Point2D eventPoint = new Point2D.Double( event.getPoint().getX() , event.getPoint().getY() ); // // why do i obtain wrong coordinates?
                        
        }
        
        
    } // end of MouseMotionHandler class
            
}
