package asop;

import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.event.*;

//**********************************************************************  BUILD PANEL
/*
this panel functions as a command center for building the map

*/

public class BuildPanel extends JPanel {
    
    public BuildPanel(){
        setSize( DEFAULT_WIDTH, DEFAULT_HEIGHT );
        
        panelborder = BorderFactory.createMatteBorder(14,16,16,16, Color.GREEN.darker());
        panelborder = BorderFactory.createTitledBorder(panelborder, "Map options");
        setBorder( panelborder );
        
        setLayout( new GridLayout(1,3) );
        addJRadioButtons();
        
        addFieldSizePanel();
    }
    
    private void addJRadioButton( String name, final int buildstate){        
        JRadioButton button = new JRadioButton( name, false  );
        button.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent event ){
                grid.setBuildNow(buildstate);
            }
        });
        buttongroup.add(button);
        buttonpanel.add(button);
    }
    
    
    private void addJRadioButtons(){
        buttonpanel = new ButtonPanel();        
        buttongroup = new ButtonGroup();
        
        addJRadioButton("Do not build", 0 );
        addJRadioButton("Build house", AsopConstants.BUILD_HOUSE );
        addJRadioButton("Build warehouse", AsopConstants.BUILD_WAREHOUSE );
        addJRadioButton("Erase building", AsopConstants.ERASE_BUILDING );
        
        addJRadioButton( "Add robot", AsopConstants.ADD_ROBOT );
        addJRadioButton("Erase robot", AsopConstants.ERASE_ROBOT);
        add( buttonpanel );        
    }
    
    private void addFieldSizePanel(){
        fieldsizepanel = new FieldSizePanel();
        
        fieldsizepanel.add( new JLabel( "Please select fieldsize. The greater fieldsize, the smaller the map." ) );
        
        JSlider slider = new JSlider(15,75, MapField.getFieldSize() );
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(2);
        slider.addChangeListener(fieldsizepanel);
        
        JButton button = new JButton( "ACCEPT CHANGES" );
        button.addActionListener(fieldsizepanel);
        
        fieldsizepanel.add(slider);
        fieldsizepanel.add(button);
        add(fieldsizepanel);
    }
    
    public void addGridPanel( asopGridComponent grid ){
        this.grid = grid;
    }
    
    public void setAsopGameSet( AsopGameSet gameset ){
        this.gameset = gameset;
    }
    private AsopGameSet gameset = null;    
    private asopGridComponent grid;
    
    private ButtonGroup buttongroup = null;
    private ButtonPanel buttonpanel = null;
    private FieldSizePanel fieldsizepanel = null;
    
    private final int DEFAULT_WIDTH = 300, DEFAULT_HEIGHT = 100;
    private Border panelborder;
    
    
    
    
    
    
    
    
    
    
    
//********************************************************************************************   INNER CLASSES
    public class ButtonPanel extends JPanel{
        public ButtonPanel(){
            setLayout( new GridLayout(4,2) );
            setBorder( BorderFactory.createBevelBorder( BevelBorder.LOWERED ) );
        }            
    }


//****************************************************************************

    public class FieldSizePanel extends JPanel implements ChangeListener, ActionListener{
        public FieldSizePanel(){
            setLayout( new GridLayout(3,1) );
            setBorder( BorderFactory.createBevelBorder( BevelBorder.RAISED ) );
        }

        @Override
        public void actionPerformed(ActionEvent e){
           if( JOptionPane.showConfirmDialog(this, "Your map will be changed, do you agree?" ) >= 1 ) return;
           MapField.setFieldSize( sliderfieldsize );
           gameset.clearRobots();
           gameset.getAsopFrame().getAsopComp().getGrid().initializeMapFields();
           gameset.associateMap();
           gameset.getAsopFrame().repaint();
        }

        @Override
        public void stateChanged( ChangeEvent event ){
            JSlider sld = (JSlider) event.getSource();
            sliderfieldsize = sld.getValue();
        }

        private int sliderfieldsize = MapField.getFieldSize();
    }

}


//****************************************************************************




