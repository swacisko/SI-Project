package asop;

import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;


public class AsopFrameComponent extends JComponent {
    
    public AsopFrameComponent( int width, int height ){
        setSize( new Dimension( width,height ) );
        setLayout( new GridLayout(1,2,0,0) );
        
        
        grid = new asopGridComponent(min(width/2,height), height);
        add(grid);  
        
        controlpanel = new controlPanel( width/2, height );
        add( controlpanel );
        
        addGridPanelToBuildPanel();           
    }
    
    @Override
    public void paintComponent( Graphics g ){
        Graphics2D gr = (Graphics2D)g;
        
    }
    
    public void setRoundTimer( roundTimer timer ){
        controlpanel.setRoundTimer(timer);
    }
    
    public asopGridComponent getGrid(){ return grid; }
    
    private void addGridPanelToBuildPanel(){
        controlpanel.addGridPanelToBuildPanel( grid );
    }
    
    public void setAsopGameSet( AsopGameSet gameset ){
        this.gameset = gameset;
        grid.setAsopGameSet(gameset);
        controlpanel.setAsopGameSet(gameset);
    }
    private AsopGameSet gameset = null;
    private asopGridComponent grid = null;
    private controlPanel controlpanel = null;
    
    
}
