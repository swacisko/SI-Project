package asop;

import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import static java.lang.Math.*;
import javax.swing.*;


/*
class represenst robot actions
if a robot is free od duty - has no current action to execute - it can take a new action to do from the stack of actions 
robot always pulls an action with the greatest priority, and such action, so it can be executed

*/
public abstract class RobotAction implements Comparable {
    
    RobotAction( int prior ){
        priority = prior;
    }
    
    // sorting in non-increasing order
    int compare( RobotAction oth ){
        if( priority > oth.priority ) return -1;
        else if( priority == oth.priority ) return 0;
        else return 1;
    }
    
    abstract boolean isExecutable();
    abstract void executeAction();
    
    int priority;
    
}
