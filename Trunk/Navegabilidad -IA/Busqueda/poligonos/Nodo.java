package poligonos;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;

public class Nodo {
	
	public Point2D.Double posicion;
	public ArrayList<Nodo> adyacentes;
	public String nombre;
	public Ellipse2D.Double zona;
	public static final int DIAMETRO_DEFAULT = 10;
	
	
	
	public Nodo() {
		super();
		posicion = new Point2D.Double();
		adyacentes = new ArrayList<Nodo>();
		nombre = "";
		zona = new Ellipse2D.Double(posicion.getX(),posicion.getY(),DIAMETRO_DEFAULT,DIAMETRO_DEFAULT);
	}
	
	public Nodo(Point2D.Double point2D, String nombre) {
		super();
		this.posicion = point2D;
		this.nombre = nombre;
		adyacentes = new ArrayList<Nodo>();
		zona = new Ellipse2D.Double(posicion.getX()-DIAMETRO_DEFAULT/2,posicion.getY()-DIAMETRO_DEFAULT/2,DIAMETRO_DEFAULT,DIAMETRO_DEFAULT);
	}
	public Nodo(String nombre) {
		super();
		this.nombre = nombre;
		posicion = new Point2D.Double();
		adyacentes = new ArrayList<Nodo>();
		zona = new Ellipse2D.Double(posicion.getX(),posicion.getY(),DIAMETRO_DEFAULT,DIAMETRO_DEFAULT);
	}

	public ArrayList<Nodo> getAdyacentes() {
		return adyacentes;
	}
	public void setAdyacentes(ArrayList<Nodo> adyacentes) {
		this.adyacentes = adyacentes;
	}
	public Point2D getPosicion() {
		return posicion;
	}
	public void setPosicion(Point2D.Double point2D) {
		this.posicion = point2D;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void addAdyacente(Nodo n1){
		adyacentes.add(n1);
	}
	public void addAdyacente(String n1){
		adyacentes.add(new Nodo(n1));
	}
	public boolean esAdyacente(String nodoAdy){
		if(this.adyacentes.contains(new Nodo(nodoAdy)))
			return true;
		else
			return false;
	}
	public boolean esAdyacente(Nodo nodoAdy){
		if(this.adyacentes.contains(nodoAdy))
			return true;
		else
			return false;
	}
	

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Nodo other = (Nodo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	public void quitarAdyacente(String nombre)
	{
		int index = adyacentes.indexOf(new Nodo(nombre));
		adyacentes.remove(index);
	}
	
	@Override
	public String toString()
	{
		return nombre + " ("+posicion.getX()+" , "+posicion.getY()+")";
	}
	
	public Nodo clone()
	{
		Nodo newNodo = new Nodo(nombre);
		newNodo.posicion = (Point2D.Double) posicion.clone();
		newNodo.zona = (Double) zona.clone();
		newNodo.adyacentes = new ArrayList<Nodo>();
		for (int i = 0; i < this.adyacentes.size(); i++) {
			newNodo.adyacentes.add(this.adyacentes.get(i).clone());
		}
		return newNodo;
	}
	

}
