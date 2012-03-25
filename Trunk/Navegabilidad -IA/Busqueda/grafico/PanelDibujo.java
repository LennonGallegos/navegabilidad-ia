package grafico;

/**
* @version 1.0 21-04-2005
* @author Tamara Ramirez Andrade, Jaime Diaz Rojas 	
*/

//Se incluyen los paquetes necesarios para la compilacion del programa.
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 	En esta clase se definen los metodos necesarios para crear los paneles y se le asocia el listener al panel de
	dibujo. Se implementa el metodo obtener accion, para recibir la accion proveniente desde los botones definidos
	en la clase editor. Ademas, se definen metodos para crear lineas, rectangulos, circulos y elipses para luego 
	ser dibujados.	Se implementaron los metodos para trasaladar y escalar el dibujo. Para rotar, se utiliza un 
	metodo de la clase Graphics2D, para ello se crea un metodo que se encarga de entregar el angulo en que sera 
	rotado el dibujo.
*/

public class PanelDibujo extends JPanel{

	public double xInicial;
	public double xFinal;
	public double yInicial;
	public double yFinal;
	
	
/**
* @param accion Se almacena la accion del boton.
* @param p1 punto obtenido al hacer click en el panel de dibujo.
* @param p2 punto obtenido al presionar el boton del mouse.
* @param p3 punto obtenido al arrastrar o al soltar el boton del mouse.
* @param Linea Se crea el objeto Linea.
* @param Rectangulo Se crea el objeto Rectangulo.
* @param Circulo Se crea el objeto Circulo.
* @param Elipse Se crea el objeto Elipse.
* @param lineas arreglo donde se almacenan las lineas creadas.
* @param rectangulos arreglo donde se almacenan los rectangulos creados.
* @param circulos arreglo donde se almacenan los circulos creados.
* @param elipses arreglo donde se almacenan las elipses creadas.
* @param x Variable utilizada para guardar cordenada x.
* @param y Variable utilizada para guardar cordenada y.
* @param w Variable utilizada para guardar el ancho.
* @param h Variable utilizada para guardar el alto.
* @param radio Variable utilizada para guardar el radio del circulo.
* @param d1 Variable utilizada para guardar distancia.
* @param d2 Variable utilizada para guardar distancia.
* @param d3 Variable utilizada para guardar distancia. 
* @param j Variable auxiliar. 
* @param cont Variable auxiliar. 
* @param alfa Variable usada para guardar un angulo.
* @param beta Variable usada para guardar un angulo.
* @param angulo Variable usada para guardar el angulo de rotacion.
*/	
	//Es importante inicializar los objetos accion, Linea, Rectangulo, Circulo, Elipse, para evitar problemas con
	//el metodo paintComponent cuando se dibuja el frame con el editor de dibujo.
	private String accion = "NADA";
	private Point2D p1 = new Point2D.Double(0,0);
	private Point2D p2,p3;
	private Line2D Linea=null;
	private Rectangle2D Rectangulo=null;
	private Ellipse2D Circulo=null;
	private Ellipse2D Elipse=null;
	public ArrayList lineas = new ArrayList();
	private ArrayList rectangulos = new ArrayList();
	private ArrayList circulos = new ArrayList();
	private ArrayList elipses = new ArrayList();
	private double x,y,w,h,radio,d1,d2,d3;
	private int j, cont=0;
	private double alfa,beta,angulo;

/**
* Constructor de los paneles.
* @param a Se fija el ancho del panel.
* @param b Se fija el largo del panel.
* @param c Determina el color de fondo del panel.
* @param i Variable que indica a que panel asociar el listener.
*/			
	public PanelDibujo(int a, int b, Color c) {
	
		setSize(a,b);
		setBackground(c);
	}
/**
* Metodo encargado de recibir las acciones de los botones.
* @param a Se obtiene la accion del boton.
*/
	public void obteneraccion(String a) {
		accion=a;
		System.out.println("accion es: "+accion);
		if (accion.equals("BORRAR")) {	//Si la accion es borrar, se vacian los arreglos, lo que produce que se
			lineas.clear();					//borren de la pantalla. Para usar el boton de borrar, se debe clickar
			rectangulos.clear();				//y luego pinchar en el panel de dibujo, de esta forma se borran los dibujos.
			circulos.clear();
			elipses.clear();
		}
	}

/**
* Metodo encargado de crear lineas.
* @param punto1 Se obtiene el punto inicial de la linea.
* @param punto2 Se obtiene el punto final de la linea.
*/
	public void linea(Point2D punto1, Point2D punto2){
		Linea = new Line2D.Double(punto1,punto2);
	}

/**
* Metodo encargado de crear Rectangulos.
* @param punto1 Se obtiene el punto inicial del rectangulo.
* @param punto2 Se obtiene el punto final de la rectangulo.
*/	
	public void rectangulo(Point2D punto1, Point2D punto2){
		w = punto2.getX() - punto1.getX();
		h = punto2.getY() - punto1.getY();
		
		//Dependiendo en que direccion se dibuje el rectangulo, se debe obtener siempre el punto inicial que
		//corresponde a la esquina superior izquierda del rectangulo.
		if ((punto2.getX()>punto1.getX()) && (punto2.getY()>punto1.getY())) {
			x=punto1.getX();
			y=punto1.getY();
		} else if ((punto2.getX()<punto1.getX()) && (punto2.getY()<punto1.getY())) {
				x=punto2.getX();
				y=punto2.getY();
			} else if ((punto2.getX()<punto1.getX()) && (punto2.getY()>punto1.getY())) {
					x=punto2.getX();
					y=punto1.getY();
				}else if ((punto2.getX()>punto1.getX()) && (punto2.getY()<punto1.getY())) {
						x=punto1.getX();
						y=punto2.getY();
					}
		Rectangulo = new Rectangle2D.Double(x,y,Math.abs(w),Math.abs(h));
	}

/**
* Metodo encargado de crear Circulos.
* @param punto1 Se obtiene el centro del circulo.
* @param punto2 Se obtiene el punto final del circulo.
*/		
	public void circulo(Point2D punto1, Point2D punto2){
		radio = Math.sqrt(Math.pow(punto2.getX()-punto1.getX(),2)+Math.pow(punto2.getY()-punto1.getY(),2));
		
		//Se crea un circulo ingresando el mismo ancho y largo, ademas, al desplazarlo en el radio, se logra
		//dibujar el circulo desde el centro.
		Circulo = new Ellipse2D.Double(punto1.getX()-radio,punto1.getY()-radio,2*radio,2*radio);
	}

/**
* Metodo encargado de crear Elipses.
* @param punto1 Se obtiene el centro de la elipse.
* @param punto2 Se obtiene el punto final de la elipse.
*/			
	public void elipse(Point2D punto1, Point2D punto2){
		w = Math.abs(punto2.getX() - punto1.getX());
		h = Math.abs(punto2.getY() - punto1.getY()); 
		
		Elipse = new Ellipse2D.Double(punto1.getX()-w,punto1.getY()-h,2*w,2*h);
	}
	
/**
* Metodo encargado de dibujar las respectivas figuras creadas.
*/	
	public void paintComponent(Graphics g) {  
 	 	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D)g;

		if (Linea != null) g2.draw((Line2D)Linea);
		if (Rectangulo !=null) g2.draw((Rectangle2D)Rectangulo);
		if (Circulo != null) g2.draw((Ellipse2D)Circulo);
		if (Elipse !=  null) g2.draw((Ellipse2D)Elipse);
		if (accion.equals("ROTAR")) g2.rotate(angulo,p1.getX(),p1.getY());	//Con este metodo se rota el dibujo.
		
		for (int i = 0; i < lineas.size(); i++)
   		g2.draw((Line2D)lineas.get(i));
			
		for (int i = 0; i < rectangulos.size(); i++)
     		g2.draw((Rectangle2D)rectangulos.get(i));
		
		for (int i = 0; i < circulos.size(); i++)
			g2.draw((Ellipse2D)circulos.get(i));
		
		for (int i = 0; i < elipses.size(); i++)
			g2.draw((Ellipse2D)elipses.get(i));
	}

/**
* Metodo para trasaladar el dibujo.
* @param punto1 Se obtiene el primer punto.
* @param punto2 Se obtiene el segundo punto donde se trasladara el dibujo.
*/
	public void trasladar(Point2D punto1, Point2D punto2){
		x = (punto2.getX()-punto1.getX());
		y = (punto2.getY()-punto1.getY());
		
		//Para trasladar se ingresan las nuevas coordenadas y se crea nuevamente el objeto correspondiente.
		for (int i = 0; i < lineas.size(); i++) {
   		Linea = ((Line2D)lineas.get(i));
			Line2D linea_paso = new Line2D.Double(Linea.getX1()+x,Linea.getY1()+y,Linea.getX2()+x,Linea.getY2()+y);
			lineas.set(i,linea_paso);
		}
		
		for (int i = 0; i < rectangulos.size(); i++) {
   		Rectangulo = ((Rectangle2D)rectangulos.get(i));
			Rectangle2D rectangulo_paso = new Rectangle2D.Double(Rectangulo.getX()+x,Rectangulo.getY()+y,Rectangulo.getWidth(),Rectangulo.getHeight());
			rectangulos.set(i,rectangulo_paso);
		}
		
		for (int i = 0; i < circulos.size(); i++) {
   		Circulo = ((Ellipse2D)circulos.get(i));
			Ellipse2D circulo_paso = new Ellipse2D.Double(Circulo.getX()+x,Circulo.getY()+y,Circulo.getWidth(),Circulo.getHeight());
			circulos.set(i,circulo_paso);
		}
		
		for (int i = 0; i < elipses.size(); i++) {
   		Elipse = ((Ellipse2D)elipses.get(i));
			Ellipse2D elipse_paso = new Ellipse2D.Double(Elipse.getX()+x,Elipse.getY()+y,Elipse.getWidth(),Elipse.getHeight());
			elipses.set(i,elipse_paso);
		}
	}
/**
* Metodo para escalar.
* @param punto1 Con respecto a este punto se escalara el dibujo.
* @param punto2 Se define el siguiente punto para escalar.
* @param punto3 Con este punto y el punto 1 se puede determinar el factor de escalamiento.
*/
	public void escalar(Point2D punto1, Point2D punto2, Point2D punto3) {
		
		if (j==0) {
			d1 = Math.sqrt(Math.pow(punto1.getX()-punto2.getX(),2)+Math.pow(punto1.getY()-punto2.getY(),2));	//dist entre punto1 y punto2
			d2 = Math.sqrt(Math.pow(punto2.getX()-punto3.getX(),2)+Math.pow(punto2.getY()-punto3.getY(),2));	//dist entre punto2 y punto3
			d3 = Math.sqrt(Math.pow(punto1.getX()-punto3.getX(),2)+Math.pow(punto1.getY()-punto3.getY(),2));	//dist entre punto1 y punto3
			j=j+1;
		}
		else {
			d3 = Math.sqrt(Math.pow(punto1.getX()-punto3.getX(),2)+Math.pow(punto1.getY()-punto3.getY(),2));
			d2 = Math.abs(d1-d3);
		}
		
		if (d3 < d1) d2 = 1 - d2/500;		//Se definen los factores de escalamiento
		else d2 = 1 + d2/500;

		//Se redibujan los objetos con los respectivos factores de escalamiento y cuidando que el escalamiento
		//se realiza con respecto al punto inicial.				
		for (int i = 0; i < lineas.size(); i++) {
   		Linea = ((Line2D)lineas.get(i));
			Line2D linea_paso = new Line2D.Double(d2*(Linea.getX1()-punto1.getX())+punto1.getX(),d2*(Linea.getY1()-punto1.getY())+punto1.getY(),d2*(Linea.getX2()-punto1.getX())+punto1.getX(),d2*(Linea.getY2()-punto1.getY())+punto1.getY());
			lineas.set(i,linea_paso);
		}
		
		for (int i = 0; i < rectangulos.size(); i++) {
  			Rectangulo = ((Rectangle2D)rectangulos.get(i));
			Rectangle2D rectangulo_paso = new Rectangle2D.Double(d2*(Rectangulo.getX()-punto1.getX())+punto1.getX(),d2*(Rectangulo.getY()-punto1.getY())+punto1.getY(),Rectangulo.getWidth()*d2,Rectangulo.getHeight()*d2);
			rectangulos.set(i,rectangulo_paso);
		}
		
		for (int i = 0; i < circulos.size(); i++) {
  			Circulo = ((Ellipse2D)circulos.get(i));
			Ellipse2D circulo_paso = new Ellipse2D.Double(d2*(Circulo.getX()-punto1.getX())+punto1.getX(),d2*(Circulo.getY()-punto1.getY())+punto1.getY(),Circulo.getWidth()*d2,Circulo.getHeight()*d2);
			circulos.set(i,circulo_paso);
		}
		
		for (int i = 0; i < elipses.size(); i++) {
  			Elipse = ((Ellipse2D)elipses.get(i));
			Ellipse2D elipse_paso = new Ellipse2D.Double(d2*(Elipse.getX()-punto1.getX())+punto1.getX(),d2*(Elipse.getY()-punto1.getY())+punto1.getY(),Elipse.getWidth()*d2,Elipse.getHeight()*d2);
			elipses.set(i,elipse_paso);
		}
	}
	
/**
* Metodo para rotar. Con este metodo se obtienen el angulo de rotacion y luego es entregado al metodo de Graphics2D
* encargado de rotar el dibujo.
* @param punto1 Con respecto a este punto se rotara el dibujo. Ademas, es utilizado para obtener el angulo de rotacion.
* @param punto2 Este punto sirve apra poder determinar el angulo de rotacion.
* @param punto3 Este punto sirve apra poder determinar el angulo de rotacion.
*/
	public void rotar(Point2D punto1, Point2D punto2, Point2D punto3) {

		alfa = Math.atan((punto2.getY()-punto1.getY())/(punto2.getX()-punto1.getX()));
		beta = Math.atan((punto3.getY()-punto1.getY())/(punto3.getX()-punto1.getX()));
		
		angulo = beta - alfa;

		if ((punto3.getX()-punto1.getX()) < 0) {
			angulo = Math.PI+angulo;
		}
		
	}

	public void redibujarLinea()
	{
		linea(new Point2D.Double(xInicial,yInicial), new Point2D.Double(xFinal,yFinal));
		this.repaint();
	}
	
	@SuppressWarnings("unchecked")
	public void addLinea()
	{
		Line2D lineaAux= (Line2D) Linea.clone();
		lineas.add(lineaAux);
		this.repaint();
	}
	
	public void quitarUltimaLinea()
	{
		if(lineas.size()>0)
		{
			lineas.remove(lineas.size()-1);
			Linea = null;
			this.repaint();
		}
	}
	
	public void limpiarPanel()
	{
		while(lineas.size()>0)
		{
			lineas.remove(lineas.size()-1);
			
		}
		Linea = null;
		this.repaint();
	}

}
