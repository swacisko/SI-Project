package asop;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;

public class TimerPanel extends JPanel{
    
    TimerPanel(){
        setSize( DEFAULT_WIDTH, DEFAULT_HEIGHT );
        setLayout( new BorderLayout(0,0) );
            
        addSlider();
        panelborder = BorderFactory.createTitledBorder( new LineBorder( Color.RED,5,true ), "Simulation pace" );
        setBorder( panelborder );
        
        addTimerButton();
    }
    
    private void addTimerButton(){
        JButton button = new JButton( "Start or stop simulation" );
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( timer.isRunning() ) timer.stop();
                else timer.start();
            }
        });
        add( button, BorderLayout.SOUTH );
    }
    
    public void setRoundTimer( roundTimer timer ){
        this.timer = timer;
        timer.addActionListener(timer);
        slider.addChangeListener(timer);        
    }
    
    private void addSlider(){
        int maxVal = 30;
        slider = new JSlider(0, maxVal,5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setMinorTickSpacing( 1 );
        slider.setMajorTickSpacing( 10 );
        Dictionary<Integer, Component> labelTable = new Hashtable< Integer, Component>();
        for( int i=0; i<=maxVal; i+=10 ) labelTable.put(i, new JLabel( "" + i/10 + " s" ) );
        slider.setLabelTable(labelTable);
        add( new NorthSliderPanel(), BorderLayout.NORTH ); // i add only a label with instructions
        add( slider, BorderLayout.CENTER ); // i add slider to the panel        
    }
    
    public void setAsopGameSet( AsopGameSet gameset ){
        this.gameset = gameset;
    }
    private AsopGameSet gameset = null;
    
    private final int DEFAULT_WIDTH = 300, DEFAULT_HEIGHT = 100;
    private JSlider slider;
    private roundTimer timer;
    private Border panelborder;
}


//********************************************************************************** NORTH SLIDER PANEL  !!

class NorthSliderPanel extends JPanel{

    public NorthSliderPanel() {
        setSize(100,100);
        add( new JLabel( "Please select pace of the simulation (seconds/round)"), BorderLayout.CENTER );
    }
    
    
    
}
