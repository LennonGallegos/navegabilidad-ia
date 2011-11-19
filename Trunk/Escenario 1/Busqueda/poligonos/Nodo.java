package poligonos;

import java.awt.Point;
import java.util.ArrayList;

public class Nodo {
	
	private Point posicion;
	private ArrayList<Nodo> adyacentes;
	private String nombre;
	
	
	
	public Nodo() {
		super();
		posicion = new Point();
		adyacentes = new ArrayList<Nodo>();
		nombre = "";
	}
	
	public Nodo(Point posicion, String nombre) {
		super();
		this.posicion = posicion;
		this.nombre = nombre;
		adyacentes = new ArrayList<Nodo>();
	}
	public Nodo(String nombre) {
		super();
		this.nombre = nombre;
		posicion = new Point();
		adyacentes = new ArrayList<Nodo>();
	}

	public ArrayList<Nodo> getAdyacentes() {
		return adyacentes;
	}
	public void setAdyacentes(ArrayList<Nodo> adyacentes) {
		this.adyacentes = adyacentes;
	}
	public Point getPosicion() {
		return posicion;
	}
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
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
	
	
	
	
	
	
	

}
