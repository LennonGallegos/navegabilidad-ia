package grafico;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class KeyAdaptador extends KeyAdapter {

	boolean ctrl = false;
	long ctrlTime = 0;
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.isControlDown())
		{
			ctrl = true;
			ctrlTime = arg0.getWhen();
		}
		super.keyPressed(arg0);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(ctrl && arg0.getKeyCode() == arg0.VK_Z) 
		{
			if((arg0.getWhen()-ctrlTime)<250)
			{
				PanelDibujo panel = (PanelDibujo)((VentanaDibujo) arg0.getSource()).panelDibujo;
				panel.quitarUltimaLinea();
			}
			ctrl = false;
			ctrlTime = 0;
		}
			
		super.keyReleased(arg0);
	}
	
}
