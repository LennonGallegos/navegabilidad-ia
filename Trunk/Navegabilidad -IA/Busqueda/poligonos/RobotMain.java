package poligonos;

/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa
 * y Milton Pividori.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.Color;

import utils.Grafico;
import utils.MathAux;
import utils.Ventana;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class RobotMain {

	public static Grafico graf ;
	public static Ventana frame;
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws PrologConnectorException, InterruptedException {
    	
    	
    	Long s = System.currentTimeMillis();
    	
    	RobotAgent agent = new RobotAgent();
        RobotEnvironment environment = new RobotEnvironment();
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        initInterfaz(agent.getAgentState());
        frame.repaint();
        Thread.sleep(300);
        hilo.start();
        simulator.start();
        Thread.sleep(300);
        frame.repaint();
        hilo.stop();
        System.out.println(hilo.isAlive());
        System.out.println("Tiempo transcurrido (Milisegundos): "+ (System.currentTimeMillis() - s));
        System.out.println("Distancia recorrida: "+ agent.getAgentState().distanciaRecorrida);
    }
	private static void initInterfaz(RobotAgentState agentState) {
		graf = new Grafico(MathAux.ANCHO,MathAux.ALTO,Color.WHITE,agentState.robot);
        graf.setNodos(RobotEnvironmentState.nodos);
        graf.setParedes(RobotEnvironmentState.paredes);
        frame = new Ventana("Prueba",graf);
    	frame.setVisible(true);
		
	}
	
	static Thread hilo = new Thread(new Runnable() {
	public void run() {
		int i = 0;
		while(i<2000)
		{
			try {
				Thread.sleep(100);
				frame.repaint();
//				System.out.println("REPAINT");
				i++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
});


	

}
