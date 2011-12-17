package utils;

/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Guti√©rrez, Jorge Roa
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

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.font.LayoutPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.LayoutStyle;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.state.datastructure.Graph;

public class Prueba {
	public static	Robot robot;
    public static void main(String[] args) throws PrologConnectorException, InterruptedException {
    	
    	robot = new Robot(new Point2D.Double(200,200));
//    	System.out.println(robot.direccion.getP1().distance(robot.direccion.getP2()));
//    	imprimirDireccion();
//    	robot.girarXGrados(90);
//    	imprimirDireccion();
//    	robot.girarXGrados(90);
//    	imprimirDireccion();
//    	robot.girarXGrados(45);
//    	imprimirDireccion();
//    	robot.girarXGrados(45);
//    	imprimirDireccion();
//    	robot.girarXGrados(45);
//    	imprimirDireccion();
//    	System.out.println(robot.direccion.getP1().distance(robot.direccion.getP2()));
    	
    	Grafico graf = new Grafico(640,480,Color.WHITE,robot);
    	JFrame frame = new JFrame("Prueba");
    	frame.setSize(640, 480);
    	frame.setLocationRelativeTo(null);
    	frame.setResizable(false);
    	frame.add(graf);
    	frame.setVisible(true);

		int i = 0;
		while (i<1000)
			{
			Thread.sleep(50);
			//robot.girarXGrados(22.5);
			robot.avanzar();
			System.out.println(robot.posicion.x);
			frame.repaint();
			}

    	
       
    }

    public static void imprimirDireccion()
    {
    	System.out.println("DirecciÛn actual de (" + robot.direccion.x1+" , " + robot.direccion.y1 
    			+ ") a (" + robot.direccion.x2 + " , " + robot.direccion.y2 + ")");
    	System.out.println("SENSOR Centro: ("+robot.sensor.zona.getCenterX()+" , "+robot.sensor.zona.getCenterY()+")");
    	System.out.println("ROBOT Angulo: "+robot.anguloActual);
    	System.out.println("SENSOR Angulo: "+robot.sensor.zona.getAngleStart());
    	System.out.println("SENSOR AnguloDireccion: "+robot.sensor.getDireccionAngular());
    	System.out.println("SENSOR MinY: "+robot.sensor.zona.getMinY()+"SENSOR MaxY: "+robot.sensor.zona.getMaxY());
    }
    
}