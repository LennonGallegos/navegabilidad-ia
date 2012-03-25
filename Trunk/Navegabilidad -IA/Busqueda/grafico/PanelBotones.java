package grafico;

/**
* @version 1.0 21-04-2005
* @author Tamara Ramirez Andrade, Jaime Diaz Rojas 	
*/

//Se incluyen los paquetes necesarios para la compilacion del programa.
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelBotones extends JPanel{

	public JButton listo;
	private buttonAction actionB;
	public double yInicial;
	public double yFinal;
	
	public PanelBotones(int a, int b, Color c) {
		 actionB = new buttonAction();
		setSize(a/8,b);
		setBackground(c);
		listo = new JButton();
		listo.setSize(a/8, 20);
		listo.setText("Listo");
		listo.addActionListener(actionB);
		listo.setVisible(true);
	}
	
	private class buttonAction implements ActionListener
	{
		

		public buttonAction() {
			super();
		}

		public void actionPerformed(ActionEvent arg0) {
			int i =0;
			i++;
			
		}
		
	}
	
	
}
