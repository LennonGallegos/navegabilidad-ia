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

import frsf.cidisi.faia.state.EnvironmentState;

public class RobotEnvironmentState extends EnvironmentState {

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
    
    
    /*Matriz que representa las adyacencias entre los distintos nodos. La primer columna corresponde a un nodo en particular
     * y el resto a los que son adyacentes a �ste.*/
    public static final String[][] ADYACENCIAS = new String[][]{
        {A, B, G},
        {B, A, C, E, X},
        {C, B, D, F, X},
        {D, C, H},
        {E, B, F, X},
        {F, C, E, X},
        {G, A, H, I},
        {H, D, G, I},
        {I, G, H},
        {X, B, C, E, F}
        };
    public static ArrayList<Nodo> nodos;
    private String posicionActualRobot;
    private ArrayList<String> nodosAdyacentesActuales;

    /**
     * This map has a point of the world (A, B, C, ...) as key, and a collection
     * of successors of that point.
     */
    
    RobotEnvironmentState() {
        ;
    }

    @Override
    public Object clone() {
        return null;
    }

    @Override
    public void initState() {
        /**
         * In this matrix the first element of each row represents a position
         * in the map and the seccessors of that position.
         */
    	nodos = new ArrayList<Nodo>();
    	for (int i = 0; i < ADYACENCIAS.length; i++) {
    		Nodo nodo = new Nodo(ADYACENCIAS[i][0]);
    		for (int j = 1; j < ADYACENCIAS[i].length; j++) {
    			Nodo nodoAdy = new Nodo(ADYACENCIAS[i][j]);
    			nodo.addAdyacente(nodoAdy);
			}
    		nodos.add(nodo);
		}
    	posicionActualRobot = X;
    }
    

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        // Returns always true. This method is not used.
        return true;
    }

	public static ArrayList<Nodo> getNodos() {
		return nodos;
	}

	public static void setNodos(ArrayList<Nodo> nodos) {
		RobotEnvironmentState.nodos = nodos;
	}
	
		public boolean agregarObstaculoEntre(Nodo a, Nodo b){
	    	
	    	String nombreA = a.getNombre();
	    	String nombreB = b.getNombre();
	      	
	    	int indexA = nodos.indexOf(a);
	    	int indexB = nodos.indexOf(b);
	    	
	    	if(!nodos.get(indexA).esAdyacente(b))
	    		return false;
	    	
	    	nodos.get(indexA).quitarAdyacente(nombreB);
	    	nodos.get(indexB).quitarAdyacente(nombreA);
	    	
	    	return true;
	    	    	
	    }
	   
	    public boolean quitarObstaculoEntre(Nodo a, Nodo b){
	    	
	    	int indexA = nodos.indexOf(a);
	    	int indexB = nodos.indexOf(b);
	    	
	    	if(!nodos.get(indexA).esAdyacente(b))
	    		return false;
	    	
	    	nodos.get(indexA).addAdyacente(a);
	    	nodos.get(indexB).addAdyacente(b);
	    	
	    	return true;
	    	    	
	    }
	    
	    public boolean agregarObstaculoEntre(String a, String b){
	    	
	    	int indexA = nodos.indexOf(new Nodo(a));
	    	int indexB = nodos.indexOf(new Nodo(b));
	    	
	    	if(!nodos.get(indexA).esAdyacente(b))
	    		return false;
	    	
	    	nodos.get(indexA).quitarAdyacente(b);
	    	nodos.get(indexB).quitarAdyacente(a);
	    	return true;
	    	    	
	    }
	   
	    public boolean quitarObstaculoEntre(String a, String b){
	    	    	
	    	int indexA = nodos.indexOf(new Nodo(a));
	    	int indexB = nodos.indexOf(new Nodo(b));
	    	
	    	if(!nodos.get(indexA).esAdyacente(b))
	    		return false;
	    	
	    	nodos.get(indexA).addAdyacente(b);
	    	nodos.get(indexB).addAdyacente(a);
	    	
	    	return true;
	    	    	
	    }

		public ArrayList<String> getPercepcion360(String posicion) {
			int index = nodos.indexOf(new Nodo(posicion));
			ArrayList<String> adyacentes = new ArrayList<String>();
			ArrayList<Nodo> adyacentesNodo = nodos.get(index).getAdyacentes();
			int len = adyacentesNodo.size();
			for (int i = 0; i < len; i++) {
				adyacentes.add(adyacentesNodo.get(i).getNombre());
			}
			
			return adyacentes;
		}

		public String getPosicionActualRobot() {
			return posicionActualRobot;
		}

		public void setPosicionActualRobot(String posicionActualRobot) {
			this.posicionActualRobot = posicionActualRobot;
		}

		public ArrayList<String> getNodosAdyacentesActuales() {
			return nodosAdyacentesActuales;
		}

		public void setNodosAdyacentesActuales(ArrayList<String> nodosAdyacentesActuales) {
			this.nodosAdyacentesActuales = nodosAdyacentesActuales;
		}
	    
	    
    
    
}