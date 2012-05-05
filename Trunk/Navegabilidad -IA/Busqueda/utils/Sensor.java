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
	private static double RADIO = 30;
	private static double EXTENSION_ANGULAR = 45;
	private static double EXTENSION_ANGULAR_PARCIAL = EXTENSION_ANGULAR/3;
	public Arc2D.Double zonaIzq,zonaFrontal,zonaDer;
	
	
	public Sensor() {
		super();
		zona = new Arc2D.Double();
		zonaIzq = new Arc2D.Double();
		zonaFrontal = new Arc2D.Double();
		zonaDer = new Arc2D.Double();
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
		zonaIzq = new Arc2D.Double();
		zonaFrontal = new Arc2D.Double();
		zonaDer = new Arc2D.Double();
		//this.zona.extent = 45;
		//this.zona.height = this.zona.width = 30;
		zona.setArcByCenter(posicion.x, posicion.y,	//zona.setArcByCenter(pos.x, (pos.y+(zona.height/2)), 
		RADIO, zona.start, EXTENSION_ANGULAR, Arc2D.PIE);
		
		this.posicion=posicion;
		//setPosicion(posicion);
		double restaAngulo =  (BigDecimal.valueOf(this.zona.extent/2).setScale(MathAux.PRECISION,RoundingMode.HALF_EVEN)).doubleValue();
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
		zonaDer.setArcByCenter(posicion.x, posicion.y, 
				RADIO, zona.start, EXTENSION_ANGULAR_PARCIAL, Arc2D.PIE);
		zonaFrontal.setArcByCenter(posicion.x, posicion.y, 
				RADIO, zonaDer.start+EXTENSION_ANGULAR_PARCIAL, EXTENSION_ANGULAR_PARCIAL, Arc2D.PIE);
		zonaIzq.setArcByCenter(posicion.x, posicion.y, 
				RADIO, zonaFrontal.start+EXTENSION_ANGULAR_PARCIAL, EXTENSION_ANGULAR_PARCIAL, Arc2D.PIE);
		
	}

	
	public Sensor(Point2D.Double posicion, Arc2D.Double zona) {
		super();
		this.posicion = posicion;
		this.zona = zona;
		zonaIzq = new Arc2D.Double();
		zonaFrontal = new Arc2D.Double();
		zonaDer = new Arc2D.Double();
		zonaDer.setArcByCenter(posicion.x, posicion.y, 
				RADIO, zona.start, EXTENSION_ANGULAR_PARCIAL, Arc2D.PIE);
		zonaFrontal.setArcByCenter(posicion.x, posicion.y, 
				RADIO, zonaDer.start+EXTENSION_ANGULAR_PARCIAL, EXTENSION_ANGULAR_PARCIAL, Arc2D.PIE);
		zonaIzq.setArcByCenter(posicion.x, posicion.y, 
				RADIO, zonaFrontal.start+EXTENSION_ANGULAR_PARCIAL, EXTENSION_ANGULAR_PARCIAL, Arc2D.PIE);
	}
	
	public double getDireccionAngular()
	{
		if(zona.extent==0)
			return zona.start;
		double anguloResult = zona.start + (BigDecimal.valueOf(this.zona.extent/2).setScale(MathAux.PRECISION,RoundingMode.HALF_EVEN)).doubleValue();
		if(anguloResult>=360)
			return anguloResult - 360;
		else
			return anguloResult;
	}
	
	public void setPosicion(Point2D.Double pos)
	{
		posicion = pos;
		zona.x = pos.x-zona.width/2;
		zona.y = pos.y-zona.height/2;
		zonaIzq.x = zonaFrontal.x = zonaDer.x = zona.x;
		zonaIzq.y = zonaFrontal.y = zonaDer.y = zona.y;
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
		zonaDer.start = zona.start;
		zonaFrontal.start = result+EXTENSION_ANGULAR_PARCIAL;
		zonaIzq.start = zonaFrontal.start+EXTENSION_ANGULAR_PARCIAL;
	}
	
	public void setExtensionAngular(double grados)
	{//FIXME: Si se usa agregar las 3 zonas independientes
		zona.extent = grados;
	}
	public void setAngulo(double anguloInicial, double extensionAngular)
	{//FIXME: Si se usa agregar las 3 zonas independientes
		zona.start = anguloInicial;
		zona.extent = extensionAngular;
	}
	
	public Sensor clone()
	{
		Sensor newSensor = new Sensor((Point2D.Double)this.posicion.clone(),(Arc2D.Double)this.zona.clone());
		return newSensor;
	}
	
	

}
