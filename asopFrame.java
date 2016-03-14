package asop;


import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;


public class asopFrame extends JFrame{
    
    asopFrame(){
        setTitle( "Automatyczny system ochrony przeciwpowodziowej" );
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        setSize( d.width, d.height );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        
        asopComp = new AsopFrameComponent( d.width, d.height );    
        
        
        add( asopComp ); 
        
    }
    
    public void addTimer( roundTimer timer ){
        asopComp.setRoundTimer(timer);
    }
    
    public AsopFrameComponent getAsopComp(){ return asopComp;}  
    
    public void setAsopGameSet( AsopGameSet gameset ){ 
        this.gameset = gameset;
        asopComp.setAsopGameSet(gameset);
    }
    
    private AsopFrameComponent asopComp;
    private AsopGameSet gameset = null;
    
}
