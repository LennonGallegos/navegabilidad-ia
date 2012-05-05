package utils;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.Point2D.Double;
import java.math.BigDecimal;
import java.math.RoundingMode;

import poligonos.RobotEnvironmentState;

public class Robot {
	
	public Sensor sensor;
	public Line2D.Double direccion;
	public Point2D.Double posicion;
	public Ellipse2D.Double zona;
	public double anguloActual;
	public static int IZQUIERDA=-1;
	public static int FRENTE=0;
	public static int DERECHA=1;
	public static int NADA=555;
	


	public Robot(Sensor sensor, Line2D.Double direccion,
			Point2D.Double posicion) {
		super();
		this.sensor = sensor;
		this.direccion = direccion;
		this.posicion = posicion;
		this.zona =new Ellipse2D.Double(posicion.x-5,posicion.y-5,10,10);
	}



	public Robot(Point2D.Double posicion) {
		super();
		this.sensor = new Sensor(posicion, MathAux.NORTE);
		this.posicion = posicion;
		//Empieza con una dirección "Norte".
		this.direccion = new Line2D.Double(posicion, 
				new Point2D.Double(posicion.x,posicion.y-60));
		this.anguloActual = 90;
		this.zona =new Ellipse2D.Double(posicion.x-5,posicion.y-5,10,10);
	}
	
	
	public Robot() {
		super();
		this.posicion =  new Point2D.Double(MathAux.X_DEFAULT,MathAux.Y_DEFAULT);
		this.sensor = new Sensor(this.posicion, MathAux.NORTE);
		//Empieza con una dirección "Norte".
		this.direccion = new Line2D.Double(this.posicion, 
				new Point2D.Double(this.posicion.x,this.posicion.y-60)); //60 es la longitud de la linea direccional (irrelevante)
		this.anguloActual = 90;
		this.zona =new Ellipse2D.Double(posicion.x-5,posicion.y-5,10,10);
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
		this.sensor.girarXGrados(giro);
		
	}
	
	public void avanzar()//Avanza sin percepciones de los sensores
	{
		this.posicion=MathAux.avanzarEnLineaRecta(direccion,posicion, MathAux.PASO);
		this.sensor.setPosicion(this.posicion);
		this.zona =new Ellipse2D.Double(posicion.x-5,posicion.y-5,10,10);
		
	}
	
	public void avanzar(RobotEnvironmentState environmentState)//Avanza percibiendo
	{
//		int senso=sensar(environmentState);
//		if(senso==DERECHA)
//			girarXGrados(10);
		this.posicion=MathAux.avanzarEnLineaRecta(direccion,posicion, MathAux.PASO);
		this.sensor.setPosicion(this.posicion);
		this.zona =new Ellipse2D.Double(posicion.x-5,posicion.y-5,10,10);
//		System.out.println(senso);
		
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
	
	public void setPosicion(Point2D.Double point)
	{
		this.posicion= point;
		this.zona =new Ellipse2D.Double(posicion.x-5,posicion.y-5,10,10);
		this.sensor = new Sensor(posicion, MathAux.NORTE);
		
	}
	
	public Robot clone()
	{
		Robot newRobot = new Robot((Double) this.posicion.clone());
		newRobot.anguloActual = this.anguloActual;
		newRobot.direccion = (java.awt.geom.Line2D.Double) this.direccion.clone();
		newRobot.sensor = this.sensor.clone();
		newRobot.zona = (java.awt.geom.Ellipse2D.Double) this.zona.clone();
		
		return newRobot;
	}
	
	public int sensar(RobotEnvironmentState environment)
	{//TODO: Estaria bueno que devuelve valores del -10 al 10 de acuerdo al grado de cercania del obstaculo al robot
		for (Shape obstaculo : environment.obstaculos) {
			if(sensor.zonaIzq.intersects( obstaculo.getBounds2D()))
				return IZQUIERDA;
			if(sensor.zonaFrontal.intersects(obstaculo.getBounds2D()))
				return FRENTE;
			if(sensor.zonaDer.intersects(obstaculo.getBounds2D()))
				return DERECHA;
			if(obstaculo instanceof Line2D)
			{
				if(sensor.zonaIzq.getBounds2D().intersectsLine((Line2D) obstaculo))
					return IZQUIERDA;
				if(sensor.zonaFrontal.getBounds2D().intersectsLine((Line2D) obstaculo))
					return FRENTE;
				if(sensor.zonaDer.getBounds2D().intersectsLine((Line2D) obstaculo))
					return DERECHA;
			}
		}
		return NADA;
	}



}
