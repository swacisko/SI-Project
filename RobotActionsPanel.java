
package asop;
import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;



public class RobotActionsPanel extends JPanel {
    
    public RobotActionsPanel(){
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        setSize( d.width/2, d.height );
        setLayout( new BorderLayout() );
        
        add( new JTextArea("WITAM W PALNELU ROBOTACTIONS",30,30), BorderLayout.CENTER );
        
    }
    
    public void addSwitchPanelButton( JButton button ){
        add( button, BorderLayout.SOUTH );
    }
    
    public void setAsopGameSet( AsopGameSet gameset ){
        this.gameset = gameset;
    }
    
    private AsopGameSet gameset = null;
        
}
