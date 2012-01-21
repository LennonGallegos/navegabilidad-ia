package utils;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Robot {
	
	public Sensor sensor;
	public Line2D.Double direccion;
	public Point2D.Double posicion;
	public double anguloActual;
	


	public Robot(Sensor sensor, Line2D.Double direccion,
			Point2D.Double posicion) {
		super();
		this.sensor = sensor;
		this.direccion = direccion;
		this.posicion = posicion;
	}



	public Robot(Point2D.Double posicion) {
		super();
		this.sensor = new Sensor(posicion, MathAux.NORTE);
		this.posicion = posicion;
		//Empieza con una dirección "Norte".
		this.direccion = new Line2D.Double(posicion, 
				new Point2D.Double(posicion.x,posicion.y-60));
		this.anguloActual = 90;
	}
	
	
	public Robot() {
		super();
		this.posicion =  new Point2D.Double(MathAux.X_DEFAULT,MathAux.Y_DEFAULT);
		this.sensor = new Sensor(this.posicion, MathAux.NORTE);
		//Empieza con una dirección "Norte".
		this.direccion = new Line2D.Double(this.posicion, 
				new Point2D.Double(this.posicion.x,this.posicion.y-60)); //60 es la longitud de la linea direccional (irrelevante)
		this.anguloActual = 90;
	}



	public void girarXGrados(double giro)
	{
		this.anguloActual -= giro;
		if(anguloActual >= 360)
			anguloActual -= 360;
		double radianes = (BigDecimal.valueOf(Math.toRadians(anguloActual-180)).setScale(MathAux.PRECISION,RoundingMode.HALF_EVEN)).doubleValue();
		//double xFinal = direccion.x1 + Math.round(Math.cos(radianes));
		double xFinal = direccion.x1 + BigDecimal.valueOf(Math.toDegrees(
				(BigDecimal.valueOf(Math.cos(radianes)).setScale(MathAux.PRECISION,RoundingMode.HALF_EVEN)).doubleValue()
				)).setScale(MathAux.PRECISION,RoundingMode.HALF_EVEN).doubleValue();
		//double yFinal = direccion.y1 + Math.round(Math.sin(radianes));
		double yFinal = direccion.y1 + BigDecimal.valueOf(Math.toDegrees(
				(BigDecimal.valueOf(Math.sin(radianes)).setScale(MathAux.PRECISION,RoundingMode.HALF_EVEN)).doubleValue()
				)).setScale(MathAux.PRECISION,RoundingMode.HALF_EVEN).doubleValue();

		this.direccion = new Line2D.Double(direccion.x1,direccion.y1,xFinal,yFinal);
		System.out.println(anguloActual);
		this.sensor.girarXGrados(giro);
		
	}
	
	public void avanzar()
	{
		this.posicion=MathAux.avanzarEnLineaRecta(direccion,posicion, 5);
		this.sensor.setPosicion(this.posicion);
		
	}
	
	public void setOrientacion(int orientacion)
	{
		if(orientacion == MathAux.NORTE)
		{
			this.sensor = new Sensor(posicion, MathAux.NORTE);
			this.direccion = new Line2D.Double(posicion, 
					new Point2D.Double(posicion.x,posicion.y-60));
			this.anguloActual = 90;
		}
	}



}
