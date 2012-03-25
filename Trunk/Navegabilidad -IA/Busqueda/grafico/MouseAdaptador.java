package grafico;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MouseAdaptador extends MouseAdapter {

	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		//System.out.println(arg0.getX());
		//super.mouseMoved(arg0);
	}
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		System.out.println(arg0.getX());
		if(arg0.getSource() instanceof PanelDibujo)
		{
			PanelDibujo panel = (PanelDibujo) arg0.getSource();
			panel.xFinal=arg0.getX();
			panel.yFinal=arg0.getY();
			panel.redibujarLinea();
			
		}
		//super.mouseDragged(arg0);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource() instanceof BotonPanelDibujo)
		{
			BotonPanelDibujo btn = (BotonPanelDibujo) arg0.getSource();
			if(btn.accion==BotonPanelDibujo.ACC_EXPORTAR)
				btn.ventana.imprimirLineas();
			else
				btn.ventana.panelDibujo.limpiarPanel();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource() instanceof PanelDibujo)
		{
			PanelDibujo panel = (PanelDibujo) arg0.getSource();
			panel.xInicial=arg0.getX();
			panel.yInicial=arg0.getY();
			
		}
		//super.mousePressed(arg0);
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getSource() instanceof PanelDibujo)
		{
			PanelDibujo panel = (PanelDibujo) arg0.getSource();
			if(arg0.getX()>0)
				panel.xFinal=arg0.getX();
			else
				panel.xFinal=0;
			if (arg0.getY()>0)
				panel.yFinal=arg0.getY();
			else
				panel.yFinal=0;
			
			if(Math.abs(panel.xFinal-panel.xInicial)<20)
				panel.xFinal=panel.xInicial;
			if(Math.abs(panel.yFinal-panel.yInicial)<20)
				panel.yFinal=panel.yInicial;
			panel.redibujarLinea();
			panel.addLinea();
		}
	}
	
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		
//		if(arg0.getSource() instanceof PanelDibujo)
//		{
//			PanelDibujo panel = (PanelDibujo) arg0.getSource();
//			panel.xInicial=arg0.getX();
//			panel.yInicial=arg0.getY();
//			
//		}
//		//super.mouseClicked(arg0);
//	}
	
	
}
