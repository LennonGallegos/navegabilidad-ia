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

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import utils.Robot;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class RobotAgentState extends SearchBasedAgentState {

    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";
    public static final String F = "F";
    public static final String G = "G";
    public static final String H = "H";
    public static final String I = "I";
    public static final String X = "X";
    public static final String K = "K";
    private ArrayList<String> nodosAdyacentes;


    /**
     * Actual agent position
     */
    String position;
    
    public Point2D.Double posicionXY;
    
    public Robot robot;
    public java.lang.Double distanciaRecorrida=0D;
    public Nodo objetivo;
    public java.lang.Double costoStep=0D;

    /**
     * This map has a point of the world (A, B, C, ...) as key, and a collection
     * of successors of that point.
     */
    public RobotAgentState() {
        this.initState();
    }

    @Override
    public RobotAgentState clone() {
        RobotAgentState newState = new RobotAgentState();
        newState.setPosition(position);
        newState.setDestino(objetivo.clone());
        newState.costoStep=java.lang.Double.valueOf(costoStep);
        newState.distanciaRecorrida=java.lang.Double.valueOf(distanciaRecorrida);
        newState.robot=robot.clone();
        newState.posicionXY=posicionXY;
        return newState;
    }

    @Override
    public void initState() {
        position = X;
        nodosAdyacentes = new ArrayList<String>();
        robot = new Robot();
    }

    @Override
    public void updateState(Perception p) { //Obtiene los nodos adyacentes actuales y los reemplaza por los que tenia
        RobotPerception perception = (RobotPerception) p;
        ArrayList<String> nodos = perception.getNodosAdyacentes();
        
        try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        

        quitarNodosAdyacentes();
        for (int i = 0; i < nodos.size(); i++) {
			addNodoAyacente(nodos.get(i));
		}
    	
    }

    @Override
    public String toString() {
        String str = "Posicion: " + position;

        return str;

    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof RobotAgentState)) {
            return false;
        }
        return position.equals(((RobotAgentState) obj).getPosition());
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public void addNodoAyacente(String nodo){
    	nodosAdyacentes.add(nodo);
    }
    
    public void quitarNodosAdyacentes(){
    	nodosAdyacentes.clear();
    }

	public ArrayList<String> getNodosAdyacentes() {
		return nodosAdyacentes;
	}

	public Nodo getDestino() {
		return objetivo;
	}

	public void setDestino(Nodo destino) {
		this.objetivo = destino;
	}

	public void setPosicionXY(Double pos) {
		this.posicionXY=pos;
		robot.setPosicion(pos);
		
	}

    
   


   
}
