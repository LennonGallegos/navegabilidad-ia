package utils;

public class Location {
	public static final String GREEN="green";
	public static final String RED="red";
	
	static String[] world= {GREEN,RED,RED,GREEN,GREEN};
	static double [] probability={0.2,0.2,0.2,0.2,0.2};
	static double [] probabilitySimple={0,1,0,0,0};
	static double pHit=0.6;//Probabilidad de que haya leido bien el sensor
	static double pMiss=0.2;//Probabilidad de que el sensor haya leido mal
	static String [] sensos={RED,GREEN};
	static String senso=RED;
	static double pOverShoot=0.1;
	static double pUnderShoot=0.1;
	static double pCorrect=0.8;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		probability=sensar(probability,RED);
		probability=move(probability, 1);
		probability=sensar(probability,RED);
		probability=move(probability, 1);

		System.out.print("\n[ ");
		for (double d : probability) {
			System.out.print(MathAux.redondear(d)+" ");	
		}
		System.out.print("]");
		
		
	}

	public static double[] sensar(double[] probabilidades, String sensado)
	{
		double[] probAux = probabilidades;
		double suma=0d;
		for (int i = 0; i<probabilidades.length; i++) {
			if(sensado==world[i])
				probAux[i]=probabilidades[i]*pHit;
			else
				probAux[i]=probabilidades[i]*pMiss;
			suma+=probAux[i];
		}
		System.out.println();
		for (int i = 0; i < probAux.length; i++) {
			probAux[i]=probAux[i]/suma;
			System.out.print(probAux[i]+" - ");	
		}
			
		System.out.println();
		return probAux;
	}
	
	public static double[] sensar(double[] probabilidades, String[] sensado)
	{
		double[] probAux = probabilidades;
		for (int i = 0; i < sensado.length; i++) {
			System.out.print("[");
			for (double d : probAux) {
				System.out.print(d+" ");	
			}
			System.out.print("] - "+sensado[i]);
			probAux=sensar(probAux, sensado[i]);
		}
		return probAux;
	}
	
	public static double[] move(double[] p, int U)
	{//No funciona para size = U
		if(U==0)
			return p;
		double[] probAux = p.clone();
		int size=probAux.length;
		double aux=0d;
		int dif=0;
		int salto = size-U;
		double s=0d;
		for (int i = 0; i < size; i++) {
			
			s = pCorrect * p[salto % size];
			s = s + pOverShoot * p[(salto-1) % size];
			s = s + pUnderShoot * p[(salto+1) % size];
				                        
			probAux[i]=s;
			salto++;
		}
		System.out.print("[ ");
		for (double d : probAux) {
			System.out.print(d+" ");	
		}
		System.out.print("]");
		return probAux;
	}
	
	
}
