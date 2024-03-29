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

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import grafico.Grafico;
import grafico.VentanaSimulacion;

import java.awt.Color;
import java.awt.geom.Point2D;

import utils.MathAux;

public class RobotMain {

	public static Grafico graf ;
	public static VentanaSimulacion frame;
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws PrologConnectorException, InterruptedException {
    	
    	
    	Long s = System.currentTimeMillis();
    	
    	RobotAgent agent = new RobotAgent();
    	agent.getAgentState().setPosicionXY(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.X_INDEX]);
        RobotEnvironment environment = new RobotEnvironment();
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        initInterfaz(agent.getAgentState());
        Thread.sleep(300);
        hilo.start();
        simulator.start();
        Thread.sleep(300);
        hilo.stop();
        System.out.println(hilo.isAlive());
        System.out.println("Tiempo transcurrido (Milisegundos): "+ (System.currentTimeMillis() - s));
        System.out.println("Distancia recorrida: "+ MathAux.redondear(agent.getAgentState().distanciaRecorrida));
    }
	private static void initInterfaz(RobotAgentState agentState) {
		graf = new Grafico(MathAux.ANCHO,MathAux.ALTO,Color.WHITE,agentState.robot);
        graf.setNodos(RobotEnvironmentState.nodos);
        graf.setParedes(RobotEnvironmentState.paredes);
        graf.setObstaculos(RobotEnvironmentState.obstaculos);
        frame = new VentanaSimulacion("Prueba",graf);
    	frame.setVisible(true);
		
	}
	
	static Thread hilo = new Thread(new Runnable() {
	public void run() {
		int i = 0;
		while(i<20000)
		{
			try {
				Thread.sleep(100);
				frame.repaint();
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
});


	

}
