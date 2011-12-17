package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Grafico extends JPanel {
	
	public Arc2D arco=null;
	private Robot agente=null;

	public Grafico(int a, int b, Color c,Robot age) {
	
		setSize(a,b);
		setBackground(c);
		setAlignmentX(JPanel.CENTER_ALIGNMENT);
		setAlignmentY(JPanel.CENTER_ALIGNMENT);
		agente=age;
	}
	
	public void paint( Graphics g )
	{
		int x=10, y=30,largo=100;
		int ANCHO=MathAux.ANCHO,ALTO=MathAux.ALTO;
		super.paint( g ); // llamar al método paint de la superclase
		Graphics2D g2d = ( Graphics2D ) g; // convertir g a Graphics2D
		g2d.setPaint(Color.black);	//Color
		g2d.setStroke(new BasicStroke(2.0f)); //Ancho de borde
		
		g2d.draw(new Line2D.Double(ANCHO/2-(largo/2), ALTO/2, ANCHO/2-(largo/2), ALTO/2-largo));
		g2d.draw(new Line2D.Double(ANCHO/2-(largo/2), ALTO/2-largo, ANCHO/2-(largo/2)+largo, ALTO/2-largo));
		g2d.draw(new Line2D.Double(ANCHO/2-(largo/2)+largo, ALTO/2-largo, ANCHO/2-(largo/2)+largo, ALTO/2));
		
		if(agente != null)
			g2d.draw(agente.sensor.zona);
	}


}
