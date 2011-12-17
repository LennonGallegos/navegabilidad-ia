package utils;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * @author cpereyra
 *
 */
public class MathAux {
	
	public final static int NORTE = 0;
	public final static int ESTE = 1;
	public final static int SUR = 2;
	public final static int OESTE = 3;
	public final static int ANCHO = 640;
	public final static int ALTO = 480;
	
	
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
	
    /**
     * @param punto1 del vector
     * @param punto2 del vector
     * @param punto3 que reemplazará al punto2 en el nuevo vector
     */
    public static double getAnguloRotacion(Point2D punto1, Point2D punto2, Point2D punto3) {

		double alfa = Math.atan((punto2.getY()-punto1.getY())/(punto2.getX()-punto1.getX()));
		double beta = Math.atan((punto3.getY()-punto1.getY())/(punto3.getX()-punto1.getX()));
		System.out.println(alfa);
		System.out.println(beta);
		double angulo = beta - alfa;

		if ((punto3.getX()-punto1.getX()) < 0) {
			angulo = Math.PI+angulo;
		}
		return Math.toDegrees(angulo);
		
		
	}

}
