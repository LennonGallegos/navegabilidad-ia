package grafico;

import javax.swing.JButton;


public class BotonPanelDibujo extends JButton {

	public static String ACC_LIMPIAR="LIMPIAR";
	public static String ACC_EXPORTAR="EXPORTAR";
	public VentanaDibujo ventana;
	public String accion;

	public BotonPanelDibujo(VentanaDibujo ventana,String accion) {
		super();
		this.ventana = ventana;
		this.accion = accion;
	}
	
	
}
