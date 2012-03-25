package grafico;


import javax.swing.JFrame;

import utils.MathAux;

public class VentanaSimulacion extends JFrame {

	public VentanaSimulacion(String string, Grafico graf) {
		super();
		this.setSize(MathAux.ANCHO, MathAux.ALTO);
		this.setTitle("Navegabilidad");
    	this.setLocationRelativeTo(null);
    	this.setResizable(false);
    	this.add(graf);
	}

}
