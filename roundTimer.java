package asop;

import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;

public class roundTimer extends javax.swing.Timer implements ChangeListener, ActionListener{
    
    public roundTimer( int delay, ActionListener listener ){
        super(delay, listener);
    }   
    
    @Override
    public void stateChanged( ChangeEvent event ){
        JSlider sld = (JSlider) event.getSource();
        setDelay( 100*sld.getValue() );
        if( sld.getValue() % 1000 <= 10 ) System.out.println( "Zmieniono wartosc slidera na " + sld.getValue() );
    }
    
    @Override
    public void actionPerformed( ActionEvent event ){        
        gameset.nextRound();        
    }
    
    void setAsopGameSet( AsopGameSet gameset ){ this.gameset = gameset; }
    
    private AsopGameSet gameset = null;
}

//**********************************************************************************   TIMER PANEL !!
