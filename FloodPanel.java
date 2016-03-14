package asop;

import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;
import javax.swing.border.*;


public class FloodPanel extends JPanel{
    
    public FloodPanel(){
        setSize( DEFAULT_WIDTH, DEFAULT_HEIGHT );
        setLayout( new BorderLayout(0,0) );

        panelborder = BorderFactory.createMatteBorder(14,16,16,16, Color.BLUE);
        panelborder = BorderFactory.createTitledBorder(panelborder, "Flood options");
        setBorder( panelborder );
    }
    
    
    public void setAsopGameSet( AsopGameSet gameset ){
        this.gameset = gameset;
    }
    private AsopGameSet gameset = null;
    
    
    private final int DEFAULT_WIDTH = 300, DEFAULT_HEIGHT = 100;
    private Border panelborder;
}
