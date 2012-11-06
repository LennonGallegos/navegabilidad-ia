package grafico;

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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import utils.Robot;
import frsf.cidisi.faia.exceptions.PrologConnectorException;

public class CreaEscenario {
	public static	Robot robot;
    public static void main(String[] args) throws PrologConnectorException, InterruptedException, IOException {
    	
    	grafico.VentanaDibujo ventanaDibujo = new grafico.VentanaDibujo();
    	
    	File archivo = new File (".\\file.txt");
    	FileReader fr = new FileReader (archivo);
    	BufferedReader br = new BufferedReader(fr);
    	String linea = br.readLine();
    	while(linea!=null)
    	{
    		System.out.println(linea);
    		switch (linea.charAt(0))
    		{
    		case 'L':
    			Double x1,x2,y1,y2;
    			String[] inicio =linea.split(";");
    			inicio = inicio[0].split(",");
    			x1 = Double.valueOf(inicio[0].substring(2));
    			y1 = Double.valueOf(inicio[1].substring(0,inicio[1].length()-1));
    			String[] fin =linea.split(";");
    			fin = fin[1].split(",");
    			x2 = Double.valueOf(fin[0].substring(1));
    			y2 = Double.valueOf(fin[1].substring(0,fin[1].length()-1));
    			ventanaDibujo.panelDibujo.addLinea(new Point2D.Double(x1,y1), new Point2D.Double(x2,y2));
    			break;
    		case 'I':
    			Double xi,yi;
    			String[] pInicio =linea.split(",");
    			xi = Double.valueOf(pInicio[0].substring(2));
    			yi = Double.valueOf(pInicio[1].substring(0,pInicio[1].length()-1));
    			ventanaDibujo.panelDibujo.dibujarInicio(new Point2D.Double(xi,yi));
    			break;
    		case 'F':
    			Double xf,yf;
    			String[] pFin =linea.split(",");
    			xf = Double.valueOf(pFin[0].substring(2));
    			yf = Double.valueOf(pFin[1].substring(0,pFin[1].length()-1));
    			ventanaDibujo.panelDibujo.dibujarFin(new Point2D.Double(xf,yf));
    			break;
    		}
    		
    		linea=br.readLine();
    	}
		
		
		//System.out.println(fileReader);
		
    }
    
}