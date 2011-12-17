package utils;

import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author cpereyra
 *
 */
public class Sensor {
	
	public Point2D.Double posicion;
	public Arc2D.Double zona;
	
	public Sensor() {
		super();
		zona = new Arc2D.Double();
		posicion = new Point2D.Double();
	}
	
	/**
	 * @param posicion: Ubiación del vértice del cono
	 * @param direccionInicial: Hacia dónde apuntará el sensor inicialmente
	 * Por defecto el ángulo de percepción será de 45°.
	 */
	public Sensor(Point2D.Double posicion, int direccionInicial ) {
		super();
		this.zona = new Arc2D.Double();
		this.zona.extent = 45;
		this.zona.height = this.zona.width = 30;
		setPosicion(posicion);
		double restaAngulo =  (BigDecimal.valueOf(this.zona.extent/2).setScale(2,RoundingMode.HALF_EVEN)).doubleValue();
		switch(direccionInicial)
		{
			case MathAux.NORTE:
				zona.start = 90-restaAngulo;
				break;
			case MathAux.ESTE:
				zona.start = 0-restaAngulo;
				break;
			case MathAux.SUR:
				zona.start = 270-restaAngulo;
				break;
			case MathAux.OESTE:
				zona.start = 180-restaAngulo;
				break;
			
		}
		
	}

	public Sensor(Arc2D.Double zona) {
		super();//FIXME: Corregir
		this.zona = zona;
		this.posicion = new Point2D.Double(zona.x,zona.y);
	}
	
	public Sensor(Point2D.Double posicion, Arc2D.Double zona) {
		super();
		this.posicion = posicion;
		this.zona = zona;
	}
	
	public double getDireccionAngular()
	{
		if(zona.extent==0)
			return zona.start;
		double anguloResult = zona.start + (BigDecimal.valueOf(this.zona.extent/2).setScale(2,RoundingMode.HALF_EVEN)).doubleValue();
		if(anguloResult>=360)
			return anguloResult - 360;
		else
			return anguloResult;
	}
	
	public void setPosicion(Point2D.Double pos)
	{
		posicion = pos;
		if(zona.height!=0)
		zona.setArcByCenter(pos.x, pos.y,	//zona.setArcByCenter(pos.x, (pos.y+(zona.height/2)), 
				zona.height, zona.start, zona.extent, Arc2D.PIE);
	}
	
	public void setAngulo(double anguloInicial, double extensionAngular)
	{
		zona.start = anguloInicial;
		zona.extent = extensionAngular;
	}
	
	public void girarXGrados(double giro)
	{
		double result = zona.start + giro;
		if(result > 360)
			result -= 360;
		else
			if(result < 0)
				result += 360;
		
		zona.start = result;
	}
	
	public void setExtensionAngular(double grados)
	{
		zona.extent = grados;
	}
	
	

}
