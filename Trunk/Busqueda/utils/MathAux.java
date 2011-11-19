package utils;

import java.awt.Point;

import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 * @author cpereyra
 *
 */
public class MathAux {
	
	public final static int NORTE = 0;
	public final static int ESTE = 1;
	public final static int SUR = 2;
	public final static int OESTE = 3;
	
	
	public static final float calcAngle(Point p0, Point p1)
	{
	    float x0 = (float)p0.getX();
	    float y0 = (float)p0.getY();
	    float x1 = (float)p1.getX();
	    float y1 = (float)p1.getY();

	    // op = m sin(theta)
	    // ip = m cos(theta), where m = |P0| * |P1|

	    float op = x1*y0-x0*y1;
	    float ip = x0*x1+y0*y1;   

	    return (float)Math.toDegrees(Math.atan2(op,ip));
	}

}
