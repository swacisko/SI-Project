
package asop;

public class Position {
    public Position( int w, int h ){
        this.h = h;
        this.w = w;
    }
    
    public void setPosition( int w, int h ){
        this.h = h;
        this.w = w;
    }
    
    public boolean equals( Position pos ){
        return ( h == pos.getH() && w == pos.getW() );
    }
    
    public int getH(){ return h; }
    public int getW(){ return w; }
    
    private int h, w;
}
