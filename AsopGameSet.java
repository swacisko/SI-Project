package asop;

import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;

/*
this is the game set - in this class a map of fields, set of robots, buildings etc is stored

*/

public class AsopGameSet{
    
    public AsopGameSet(){
       AsopImages.createImages();
       robots = new Robot[0];
       showGameWindow();        
    }
    
    private void showGameWindow(){
         EventQueue.invokeLater(new Runnable(){            
            @Override
            public void run(){                 
                frame = new asopFrame();
                frame.setVisible(true);
                frame.setResizable(false); 
                
                setRoundTimer();
                setAsopGameSet();
                associateMap();
            }            
        });
    }    
    
    public void setMap( MapField[][] mapka ){ mapa = mapka; }
    public void associateMap(){
        mapa = frame.getAsopComp().getGrid().getMap();
    }
    
    public Robot[] getRobots(){ return robots; }
    public void addRobot( Robot rob ){
        robots = Arrays.copyOf(robots, robots.length+1 );
        robots[ robots.length-1 ] = rob;
    }
    public Robot getRobot( int ID ){
        for( Robot r: robots ) if( r.getID() == ID ) return r;
        return null;
    }
    public void removeRobot( int ID ){
        for( int i=0; i<robots.length; i++ ){
            if( robots[i].getID() == ID ){
                robots[i] = robots[ robots.length-1 ];
                robots = Arrays.copyOf(robots, robots.length-1 );
                break;
            }
        }
        
        for( Robot r : robots ){
            if( r.getID() > ID ) r.setID( r.getID()-1 );            
        }
        Robot.decreaseNextID();
    }
    // removes robot on the given field
    public void removeRobot( MapField field ){
        for( Robot r : robots ){
            if( r.getPosition().equals( field.getPosition() ) ){
                removeRobot( r.getID() );
                return;
            }
        }
    }
    
    public void clearRobots(){
        robots = new Robot[0];
    }
    
    public void moveRobots(){
        Random rnd = new Random();
        
        for( Robot r : robots ){
            switch( rnd.nextInt(4) ){
                case 0:{
                    r.moveUp();
                    break;
                }
                case 1:{
                    r.moveRight();
                    break;
                }
                case 2:{
                    r.moveDown();
                    break;                    
                }
                case 3:{
                    r.moveLeft();
                    break;
                }
                default:{
                    
                }                    
            }
            
        }
    }
    
    public void setTimeToNextFlood( int time ){ timeToNextFlood = time;   }
    
    public asopFrame getAsopFrame(){ return frame; }
    
    public static MapField[][] getMap(){ return mapa; }
    public static MapField getMapField( int w, int h ){ return mapa[w][h];    } 
    public static MapField getMapField( Position pos ){
        if( mapa == null ){
            System.out.println( "mapa = null" );
            return null;
        }  
        for( MapField[] it : mapa ){
            for( MapField itr : it ){
                if( itr.getPosition().equals( pos ) ) return itr;
            }
        }
        
        return null;
    }   
    public void setMapField( int w, int h, MapField field ){ mapa[w][h] = field;  }
    
    private void setAsopGameSet(){
        frame.setAsopGameSet(this);
    }
    
    private void setRoundTimer(){
        timer = new roundTimer( 500,timer );
        timer.setAsopGameSet(this); 
        frame.addTimer(timer);
       // timer.start();
    }
    private roundTimer getRoundTimer(){ return timer; }
    
    private void ColourNextField(){
        Random r = new Random();
        int a = r.nextInt(256), b = r.nextInt(256), c = r.nextInt(256);
        int w = frame.getAsopComp().getGrid().getMapWidth(), h = frame.getAsopComp().getGrid().getMapHeiht();
        int d = r.nextInt(w), e = r.nextInt(h);
        
        frame.getAsopComp().getGrid().getMapField( 0,0 ).setColor( new Color(a,b,c) );
        System.out.println("Minela kolejna sekunda");
    }
    
    public void nextRound(){
        ColourNextField();        
        moveRobots();
                
        frame.repaint();        
    }
    
    
    
    private roundTimer timer = null;
    private asopFrame frame = null;
    private static MapField[][] mapa = null;
    private Robot[] robots = null;    
    private int timeToNextFlood;
}
