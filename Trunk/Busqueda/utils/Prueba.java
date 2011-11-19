package utils;

/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros GutiÃ©rrez, Jorge Roa
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

import frsf.cidisi.faia.exceptions.PrologConnectorException;

public class Prueba {
	public static	Robot robot;
    public static void main(String[] args) throws PrologConnectorException {
    	
    	robot = new Robot(new Point2D.Double(0,0));
    	System.out.println(robot.direccion.getP1().distance(robot.direccion.getP2()));
    	imprimirDireccion();
    	robot.girarXGrados(90);
    	imprimirDireccion();
    	robot.girarXGrados(90);
    	imprimirDireccion();
    	robot.girarXGrados(45);
    	imprimirDireccion();
    	robot.girarXGrados(45);
    	imprimirDireccion();
    	robot.girarXGrados(45);
    	imprimirDireccion();
    	System.out.println(robot.direccion.getP1().distance(robot.direccion.getP2()));
       
    }
    
    public static void imprimirDireccion()
    {
    	System.out.println("Dirección actual de (" + robot.direccion.x1+" , " + robot.direccion.y1 
    			+ ") a (" + robot.direccion.x2 + " , " + robot.direccion.y2 + ")");
    	System.out.println("SENSOR Centro: ("+robot.sensor.zona.getCenterX()+" , "+robot.sensor.zona.getCenterY()+")");
    	System.out.println("ROBOT Angulo: "+robot.anguloActual);
    	System.out.println("SENSOR Angulo: "+robot.sensor.zona.getAngleStart());
    	System.out.println("SENSOR AnguloDireccion: "+robot.sensor.getDireccionAngular());
    	System.out.println("SENSOR MinY: "+robot.sensor.zona.getMinY()+"SENSOR MaxY: "+robot.sensor.zona.getMaxY());
    }
    
}