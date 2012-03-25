package grafico;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import poligonos.Nodo;
import utils.Robot;

@SuppressWarnings("serial")
public class Grafico extends JPanel {
	
	public Arc2D arco=null;
	private Robot agente=null;
	private Nodo destino=null;
	private ArrayList<Nodo> nodos=null;
	private ArrayList<Line2D.Double> paredes;
	public Grafico(int a, int b, Color c,Robot age) {
	
		setSize(a,b);
		setBackground(c);
		setAlignmentX(JPanel.CENTER_ALIGNMENT);
		setAlignmentY(JPanel.CENTER_ALIGNMENT);
		agente=age;
	}
	
	public Grafico(int ancho, int alto, Color c, Robot robot, Nodo nodo1) {

		setSize(ancho,alto);
		setBackground(c);
		setAlignmentX(JPanel.CENTER_ALIGNMENT);
		setAlignmentY(JPanel.CENTER_ALIGNMENT);
		agente=robot;
		destino=nodo1;
		
	}

	public void paint( Graphics g )
	{
		//int x=10, y=30,largo=100;
		//int ANCHO=MathAux.ANCHO,ALTO=MathAux.ALTO;
		super.paint( g ); // llamar al método paint de la superclase
		Graphics2D g2d = ( Graphics2D ) g; // convertir g a Graphics2D
		g2d.setPaint(Color.black);	//Color
		g2d.setStroke(new BasicStroke(2.0f)); //Ancho de borde
		
//		g2d.draw(new Line2D.Double(ANCHO/2-(largo/2), ALTO/2, ANCHO/2-(largo/2), ALTO/2-largo));
//		g2d.draw(new Line2D.Double(ANCHO/2-(largo/2), ALTO/2-largo, ANCHO/2-(largo/2)+largo, ALTO/2-largo));
//		g2d.draw(new Line2D.Double(ANCHO/2-(largo/2)+largo, ALTO/2-largo, ANCHO/2-(largo/2)+largo, ALTO/2));
//		
		if(agente != null)
			{
			g2d.setColor(Color.BLUE);
			g2d.draw(agente.sensor.zona);
			g2d.draw(agente.direccion);
			g2d.setStroke(new BasicStroke(3.0f)); //Ancho de borde
			g2d.draw(agente.zona);
			//g2d.draw(new Ellipse2D.Double(agente.posicion.x-5,agente.posicion.y-5,10,10));
			g2d.setStroke(new BasicStroke(2.0f)); //Ancho de borde
			}
		if(destino != null)
		{
			g2d.setColor(Color.RED);
			g2d.draw(destino.zona);
		}
		
		if(nodos != null && nodos.size()>0)
		{
			g2d.setColor(Color.GREEN);
			for (Nodo nodo : nodos) {
				if(nodo.nombre.compareTo("I")==0)
				{
					g2d.setStroke(new BasicStroke(4.0f));
					g2d.setColor(Color.RED);
					g2d.draw(nodo.zona);
					g2d.setColor(Color.GREEN);
					g2d.setStroke(new BasicStroke(2.0f));
					continue;
				}
				g2d.draw(nodo.zona);
			}
		}
		
		if(paredes != null && paredes.size()>0)
		{
			g2d.setColor(Color.BLACK);
			for (Line2D.Double pared : paredes) {
				g2d.draw(pared);
			}
		}
	}

	public Nodo getDestino() {
		return destino;
	}

	public void setDestino(Nodo destino) {
		this.destino = destino;
	}

	public ArrayList<Nodo> getNodos() {
		return nodos;
	}

	public void setNodos(ArrayList<Nodo> nodos) {
		this.nodos = nodos;
	}
	
	public ArrayList<Line2D.Double> getParedes() {
		return paredes;
	}

	public void setParedes(ArrayList<Line2D.Double> paredes) {
		this.paredes = paredes;
	}
	
	
	


}
