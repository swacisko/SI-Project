package asop;

import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;

public class controlPanel extends JPanel {
    
    public controlPanel( int width, int height ){
        setSize( new Dimension(width,height) );
        
        setLayout( new GridLayout(4,1,0,0) );
        timerpanel = new TimerPanel();
        add( timerpanel );
        
        buildpanel = new BuildPanel();
        add( buildpanel );
        
        
        floodpanel = new FloodPanel();
        add( floodpanel );
        
        robotpanel = new RobotActionsPanel();
        addPanelSwitchButtons();
        
    }
    
    @Override
    public void paintComponent( Graphics g ){
       /* Graphics2D gr = (Graphics2D)g;
        gr.setFont( new Font( "Serif", Font.BOLD, 25 ) );
        gr.drawString( "CONTROL PANEL IN PROGRESS", getWidth()/5, 50 );
        */
                
    }
    
    public void setRoundTimer( roundTimer timer ){
        timerpanel.setRoundTimer(timer);        
    }
    
    public void addGridPanelToBuildPanel( asopGridComponent grid ){
        buildpanel.addGridPanel( grid );
    }
    
    public void switchToRobotPanel(){
        removeAll();
        setLayout( new BorderLayout(0,0) );
        add(robotpanel, BorderLayout.CENTER);
        revalidate();
    }
    
    public void switchToControlPanel(){
        removeAll();
        setLayout( new GridLayout(4,1,0,0) );
        add( timerpanel );
        add( buildpanel );
        add( floodpanel );
        add( switchButton );
        revalidate();
    }
    
    private void addPanelSwitchButtons(){
        switchButton = new JButton("Switch to robot actions");
        switchButton.addActionListener( new ActionListener(){
           @Override
           public void actionPerformed( ActionEvent event ){
               switchToRobotPanel();
               repaint();
           } 
        });
        add(switchButton);
        
        JButton but2 = new JButton( "Switch to control panel" );
        but2.addActionListener( new ActionListener(){
           @Override
           public void actionPerformed( ActionEvent event ){
               switchToControlPanel();
               repaint();
           } 
        });
        robotpanel.addSwitchPanelButton(but2);
    }
    
    public void setAsopGameSet( AsopGameSet gameset ){
        this.gameset = gameset;
        timerpanel.setAsopGameSet(gameset);
        buildpanel.setAsopGameSet(gameset);
        floodpanel.setAsopGameSet(gameset);
        robotpanel.setAsopGameSet(gameset);
    }
    private AsopGameSet gameset = null;
    
    private TimerPanel timerpanel;
    private BuildPanel buildpanel;
    private FloodPanel floodpanel;
    private JButton switchButton;
    private RobotActionsPanel robotpanel;
}

//********************

