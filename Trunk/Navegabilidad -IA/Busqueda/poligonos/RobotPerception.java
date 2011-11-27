package poligonos;
/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa,
 * Luis Ignacio Larrateguy y Milton Pividori.
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

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class RobotPerception extends Perception {

    public static int UNKNOWN_PERCEPTION = -1;
    public static int EMPTY_PERCEPTION = 0;
    public static int ENEMY_PERCEPTION = 1;
    public static int FOOD_PERCEPTION = 2;
    public static int NORTE = 0;
    public static int ESTE = 1;
    public static int SUR= 2;
    public static int OESTE = 3;

    private int leftSensor;
    private int topSensor;
    private int rightSensor;
    private int bottomSensor;
    private int energy;

    private int direccion;
    private ArrayList<String> nodosAdyacentes;
    
    //TODO: Hacer esto de los sensores.

    public RobotPerception() {
        energy = 50;
        direccion = NORTE;
        nodosAdyacentes = new ArrayList<String>();
    }

    public RobotPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agent, Environment environment) {
    	RobotAgent robotAgent = (RobotAgent) agent;
        RobotEnvironment robotEnvironment = (RobotEnvironment) environment;
        RobotEnvironmentState environmentState =
                robotEnvironment.getEnvironmentState();
        
        String posicionActual = robotAgent.getAgentState().position;
        
        this.setNodosAdyacentes(robotEnvironment.getPercepcion360(posicionActual));
        
//        int row = environmentState.getAgentPosition()[0];
//        int col = environmentState.getAgentPosition()[1];
//
//        this.setTopSensor(robotEnvironment.getTopCell(row, col));
//        this.setLeftSensor(robotEnvironment.getLeftCell(row, col));
//        this.setRightSensor(robotEnvironment.getRightCell(row, col));
//        this.setBottomSensor(robotEnvironment.getBottomCell(row, col));
    }

    // The following methods are Robot-specific:

    public int getLeftSensor() {
        return leftSensor;
    }

    public void setLeftSensor(int leftSensor) {
        this.leftSensor = leftSensor;
    }

    public int getTopSensor() {
        return topSensor;
    }

    public void setTopSensor(int topSensor) {
        this.topSensor = topSensor;
    }

    public int getRightSensor() {
        return rightSensor;
    }

    public void setRightSensor(int rightSensor) {
        this.rightSensor = rightSensor;
    }

    public int getBottomSensor() {
        return bottomSensor;
    }

    public void setBottomSensor(int bottomSensor) {
        this.bottomSensor = bottomSensor;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        str.append("Nodos posibles: ");
        for (int i = 0; i < nodosAdyacentes.size(); i++) {
			str.append("\n \t\t" + nodosAdyacentes.get(i));
		}
//        str.append("Energy: " + this.energy);
//        str.append("; ");
//        str.append("Left Sensor: " + this.leftSensor);
//        str.append("; ");
//        str.append("Up Sensor: " + this.topSensor);
//        str.append("; ");
//        str.append("Right Sensor: " + this.rightSensor);
//        str.append("; ");
//        str.append("Down Sensor: " + this.bottomSensor);
        return str.toString();
    }

	public ArrayList<String> getNodosAdyacentes() {
		return nodosAdyacentes;
	}

	public void setNodosAdyacentes(ArrayList<String> nodosAdyacentes) {
		this.nodosAdyacentes = nodosAdyacentes;
	}
}