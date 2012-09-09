package utils;

public class Location2DSimple {
	public static final String GREEN="green";
	public static final String RED="red";
	public static final int[] ARRIBA= {-1,0};
	public static final int[] ABAJO= {1,0};
	public static final int[] IZQ= {0,-1};
	public static final int[] DER= {0,1};
	public static final int[] NADA= {0,0};
	
	static String[][] world= {	{GREEN, 	GREEN, 	GREEN},
								{GREEN,		RED,	GREEN},
								{GREEN, 	GREEN, 	GREEN}};
	static double [][] probability={{0.11,0.11,0.11},
									{0.11,0.11,0.11},
									{0.11,0.11,0.11}};//Belief
	
	/***
	 * 0,0 -> Sin movimiento
	 * 0,1 -> 1 a la der
	 * 0,-1 -> 1 a la izq
	 * 1,0 -> 1 abajo
	 * -1,0 -> 1 arriba
	 */
	static int [][] movimientos = {NADA};
	static String [] sensos={RED};
	static double pCorrectSense=1;//Probabilidad de que haya leido bien el sensor
	static double pIncorrectSense=1-pCorrectSense;//Probabilidad de que el sensor haya leido mal
	static double pCorrectMove=1;
	static double pOverShoot=(1-pCorrectMove)/2;
	static double pUnderShoot=pOverShoot;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int [] mov = {0,0};
		probability=sensar(probability,RED);
		probability=move(probability,mov);

		System.out.print("\n[ ");
		for (int i = 0; i < probability.length; i++) {
			System.out.println("[");
			for (int j = 0; j < probability[i].length; j++) {
				System.out.print(probability[i][j]+", ");
			}
			System.out.println("]");
		}
		System.out.print("]");
		
		
	}

	public static double[][] sensar(double[][] probabilidades, String sensado)
	{
		double[][] probAux = probabilidades;
		double suma=0d;
		for (int i = 0; i<world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if(sensado==world[i][j])
					probAux[i][j]=probabilidades[i][j]*pCorrectSense;
				else
					probAux[i][j]=probabilidades[i][j]*pIncorrectSense;
				
				suma+=probAux[i][j];
				
			}
			
		}
		System.out.println();
		System.out.println("Sensar:");
		for (int i = 0; i < probAux.length; i++) {
			for (int j = 0; j < probAux[i].length; j++) {
				probAux[i][j]=probAux[i][j]/suma;
				System.out.print(probAux[i][j]+" - ");	
			}
		}
			
		System.out.println();
		return probAux;
	}
	
	public static double[][] sensar(double[][] probabilidades, String[] sensado)
	{
		double[][] probAux = probabilidades;
		for (int k = 0; k < sensado.length; k++) {
			System.out.print("[");
			
			for (int i = 0; i < probAux.length; i++) {
				System.out.println("[");
				for (int j = 0; j < probAux[i].length; j++) {
					System.out.print(probAux[i][j]+", ");
				}
				System.out.println("]");
			}
			
			System.out.print("] - "+sensado[k]);
			probAux=sensar(probAux, sensado[k]);
		}
		return probAux;
	}
	
	public static double[] move(double[] p, int U)
	{//No funciona para size = U
		if(U==0)
			return p;
		double[] probAux = p.clone();
		int size=probAux.length;
		int salto = size-U;
		double s=0d;
		for (int i = 0; i < size; i++) {
			
			s = pCorrectMove * p[salto % size];
			s = s + pOverShoot * p[(salto-1) % size];
			s = s + pUnderShoot * p[(salto+1) % size];
				                        
			probAux[i]=s;
			salto++;
		}
		System.out.println("Move:");
		System.out.print("[ ");
		for (double d : probAux) {
			System.out.print(d+" ");	
		}
		System.out.print("]");
		return probAux;
	}
	
	public static double[][] move(double[][] p, int [] mov)
	{//No funciona para size = U
		int U=0;
		double[][] probAux = p.clone();
		if(mov[0]==0 && mov[1]==0)
			return p;
		if(mov[0]>mov[1])//ARRIBA O ABAJO
		{
			
		}
		if(mov[0]>mov[1])//IZQUIERDA O DERECHA
		{
			for (int i = 0; i < probAux.length; i++) {
				int size=probAux[i].length;
				int salto = size-U;
				double s=0d;
				for (int j = 0; i < size; i++) {
					
					s = pCorrectMove * p[i][salto % size];
					s = s + pOverShoot * p[i][(salto-1) % size];
					s = s + pUnderShoot * p[i][(salto+1) % size];
						                        
					probAux[i][j]=s;
					salto++;
				}	
			}
		}	
		
		
		System.out.println("Move:");
		System.out.print("[ ");
		for (int i = 0; i < probAux.length; i++) {
			System.out.println("[");
			for (int j = 0; j < probAux[i].length; j++) {
				System.out.print(probAux[i][j]+", ");
			}
			System.out.println("]");
		}
		System.out.print("]");
		return probAux;
	}
	
}
