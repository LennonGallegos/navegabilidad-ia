package utils;

import java.awt.HeadlessException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Ventana extends JFrame {


	public Ventana(String titulo,Grafico graf) throws HeadlessException {
		super(titulo);
    	this.setSize(MathAux.ANCHO,MathAux.ALTO);
    	this.setLocationRelativeTo(null);
    	this.setResizable(false);
    	this.add(graf);
	}
	
	

}
