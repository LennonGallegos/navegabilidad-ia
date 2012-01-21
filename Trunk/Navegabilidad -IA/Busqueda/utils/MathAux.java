package utils;

import java.awt.Point;
import java.awt.geom.Line2D;
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
	public final static int PRECISION =3;
	private static int primeraVez=1;
	public final static double X_DEFAULT =ANCHO/2;
	public final static double Y_DEFAULT =ALTO/2;
	public final static int CANTIDAD_PAREDES =10;
	
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
    
    /**
     * @param direccion Linea en la que avanzara
     * @param step Cuanto avanzará
     * @return
     */
    public static Point2D.Double avanzarEnLineaRecta(Line2D.Double direccion, Point2D.Double pos, int step)
    {
    	double x1 = direccion.x1;
    	double x2 = direccion.x2;
    	double y1 = direccion.y1;
    	double y2 = direccion.y2;
    	double x = 0;
    	double y = 0;
    	
    	if((x2-x1)==0)
		{
    		x = pos.x;
    		if(y2>y1) //Hacia el Sur 
    			y = pos.y+step;
    		else if(y2<y1) //Hacia el Norte
    				y = pos.y-step;
    			
    		
		}
    	else if((y2-y1)==0)
		{
			y = pos.y;
			if(x2>x1) //Hacia la derecha 
    			x = pos.x+step;
    		else if(x2<x1) //Hacia la izquierda
    				x = pos.x-step;
		}
			
    	else
		{
    		if(x2>x1)
    			x = pos.x+step;
    		else
    			x = pos.x-step;	
    		y = ((y2-y1)/(x2-x1))*(x-x1)+y1;
		}
    	if(primeraVez==1)
    		{
    			primeraVez=0;
    			Point2D.Double dirPuntoFinal = avanzarEnLineaRecta(direccion, new Point2D.Double(x,y), 30);
    			direccion.x1=x; direccion.y1=y; direccion.x2=dirPuntoFinal.x; direccion.y2=dirPuntoFinal.y;
    			primeraVez=1;
    		}
		
    	return new Point2D.Double(x,y);
    }
    

}
