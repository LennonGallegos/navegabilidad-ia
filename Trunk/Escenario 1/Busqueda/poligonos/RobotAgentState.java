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
    
    public Robot robot;

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
        return newState;
    }

    @Override
    public void initState() {
        position = X;
        nodosAdyacentes = new ArrayList<String>();
//        nodosAdyacentes.add(B);
//        nodosAdyacentes.add(E);
//        nodosAdyacentes.add(F);
//        nodosAdyacentes.add(C);

    }

    @Override
    public void updateState(Perception p) {
        RobotPerception perception = (RobotPerception) p;
        ArrayList<String> nodos = perception.getNodosAdyacentes();
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

    
   


   
}
