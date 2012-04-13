package grafico;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdaptador extends MouseAdapter {

	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		//System.out.println(arg0.getX());
		//super.mouseMoved(arg0);
	}
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(arg0.getSource() instanceof PanelDibujo)
		{
			PanelDibujo panel = (PanelDibujo) arg0.getSource();
			panel.xFinal=arg0.getX();
			panel.yFinal=arg0.getY();
			if(panel.objetivo==PanelDibujo.UNION_OBJ||panel.objetivo==PanelDibujo.LINEA_OBJ)
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
				btn.ventana.exportarATxt();
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
			if(panel.objetivo==PanelDibujo.INICIO_OBJ)
			{
				panel.dibujarInicio();
			}
			if(panel.objetivo==PanelDibujo.FIN_OBJ)
			{
				panel.dibujarFin();
			}
		}
		//super.mousePressed(arg0);
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getSource() instanceof PanelDibujo)
		{
			PanelDibujo panel = (PanelDibujo) arg0.getSource();
			if(panel.objetivo==PanelDibujo.LINEA_OBJ)
			{	
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
			else if(panel.objetivo==PanelDibujo.UNION_OBJ)
			{
				panel.redibujarLinea();
				panel.addUnion();			
			}
			else if(panel.objetivo==PanelDibujo.NODO_OBJ)
			{
				panel.addNodo();
			}
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
