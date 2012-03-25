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

import java.util.ArrayList;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class RobotEnvironment extends Environment {

	public static int sum = 0;
    public RobotEnvironment() {
        // Create the environment state
        this.environmentState = new RobotEnvironmentState();
        environmentState.initState();
   }

    @Override
    public Perception getPercept() {
    	RobotPerception perception = new RobotPerception();
    	
//    	switch (sum) {
//		case 1:
//			if(this.getEnvironmentState().agregarObstaculoEntre("A", "B"))
//	    		System.out.println("agregarObstaculoEntre(A, B)");
//	    	else
//	    		System.out.println("A y B ya no son adyacentes.");
//			break;
//			
//		case 2:
//			this.getEnvironmentState().quitarObstaculoEntre("A", "B");
//			if(this.getEnvironmentState().agregarObstaculoEntre("C", "D"))
//	    		System.out.println("agregarObstaculoEntre(C, D)");
//	    	else
//	    		System.out.println("C y D ya no son adyacentes.");
//			break;
//
//		default:
//			break;
//		}
    	sum++;
    	perception.setNodosAdyacentes(this.getPercepcion360(this.getEnvironmentState().getPosicionActualRobot()));
//    	agregarObstaculoEntre("A", "B");
        return perception;
    }
    
    public ArrayList<String> getPercepcion360(String posicion) {
        return ((RobotEnvironmentState) this.environmentState)
                .getPercepcion360(posicion);
    }
    
    @Override
    public RobotEnvironmentState getEnvironmentState() {
        return (RobotEnvironmentState) super.getEnvironmentState();
    }
    
    
    @Override
    public String toString() {
        return "";
    }
}
