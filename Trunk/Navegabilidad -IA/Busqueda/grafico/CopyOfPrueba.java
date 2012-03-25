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

import java.awt.Color;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.print.DocFlavor.READER;
import javax.swing.JButton;
import javax.swing.JFrame;

import utils.Robot;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.util.FileOperations;

public class CopyOfPrueba {
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
    		linea=br.readLine();
    	}
		
		
		//System.out.println(fileReader);
		
		
		
    	
    }
    
}