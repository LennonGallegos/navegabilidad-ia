package utils;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.math.BigDecimal;

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
	public final static int PRECISION =10;
	public final static int PRECISION_REDONDEO =10;
	private static int primeraVez=1;
	public final static double X_DEFAULT =ANCHO/2;
	public final static double Y_DEFAULT =ALTO/2;
	public final static int CANTIDAD_PAREDES =10;
	public final static int PASO=2;
	public final static int RADIO_NODO_INICIO=4;
	public final static int RADIO_NODO_FIN=6;
	public final static int GIRO=15;
	
	public static final Double calcAngle(Point2D p0, Point2D p1)
	{
	    Double x0 = p0.getX();
	    Double y0 = p0.getY();
	    Double x1 = p1.getX();
	    Double y1 = p1.getY();

	    // op = m sin(theta)
	    // ip = m cos(theta), where m = |P0| * |P1|

	    Double op = x1*y0-x0*y1;
	    Double ip = x0*x1+y0*y1;   

	    return redondear(Math.toDegrees(Math.atan2(op,ip)));
	}
	
    /**
     * @param punto1 del vector
     * @param punto2 del vector
     * @param punto3 que reemplazará al punto2 en el nuevo vector
     */
    public static double getAnguloRotacion(Point2D punto1, Point2D punto2, Point2D punto3) {
    	Double alfa = Math.atan((punto2.getY()-punto1.getY())/(punto2.getX()-punto1.getX()));
    	Double beta = Math.atan((punto3.getY()-punto1.getY())/(punto3.getX()-punto1.getX()));
    	Double angulo = beta - alfa;
		
		if ((punto3.getX()-punto1.getX()) < 0) {
			angulo = Math.PI+angulo;
		}
		if(punto2.getX()-punto1.getX()<0)
			angulo=angulo+135;
		return redondear(Math.toDegrees(angulo));
	}
    
    /**
     * @param direccion Linea en la que avanzara
     * @param step Cuanto avanzará
     * @return
     */
    public static Point2D.Double avanzarEnLineaRecta(Line2D.Double direccion, Point2D.Double pos, int step)
    {
    	double x1 = direccion.x1;
    	double y1 = direccion.y1;
    	double x2 = direccion.x2;
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
			Point2D.Double dirPuntoFinal = avanzarEnLineaRecta(direccion, new Point2D.Double(x,y), (int) Sensor.RADIO);
			direccion.x1=x; direccion.y1=y; direccion.x2=dirPuntoFinal.x; direccion.y2=dirPuntoFinal.y;
			primeraVez=1;
		}
		
    	return new Point2D.Double(x,y);
    }
    
    public static Double redondear(Double num)
    {
    	return BigDecimal.valueOf(num).setScale(MathAux.PRECISION_REDONDEO, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

}
