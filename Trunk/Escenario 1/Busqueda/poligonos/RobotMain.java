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

public class RobotMain {

    public static void main(String[] args) throws PrologConnectorException {
    	
    	
    	Long s = System.currentTimeMillis();
    	
    	RobotAgent agent = new RobotAgent();
        RobotEnvironment environment = new RobotEnvironment();
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
        
        System.out.println("Tiempo transcurrido (Milisegundos): "+ (System.currentTimeMillis() - s));
    }
    
    //PARA QUE SE EJECUTE 20 VECES SEGUIDAS Y TOME EL TIEMPO EN CADA UNA
//    public static void main(String[] args) throws PrologConnectorException {
//    	String tiempos [];
//    	tiempos = new String[20];
//    	for (int i = 0; i < 20; i++) {
//			
//		
//    	Long s = System.currentTimeMillis();
//    	
//    	RobotAgent agent = new RobotAgent();
//        RobotEnvironment environment = new RobotEnvironment();
//        SearchBasedAgentSimulator simulator =
//                new SearchBasedAgentSimulator(environment, agent);
//        simulator.start();
//        
//        tiempos[i]= ""+(System.currentTimeMillis() - s);
//    	}
//    	for (int i = 0; i < tiempos.length; i++) {
//    		System.out.println(tiempos[i]);
//		}
//    }

}
