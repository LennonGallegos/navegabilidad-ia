package acciones;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;

import poligonos.Nodo;
import poligonos.RobotAgentState;
import poligonos.RobotEnvironmentState;
import poligonos.RobotMain;
import utils.MathAux;
import utils.Robot;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;



public class Ir extends SearchAction {

	private Nodo destino;
	private Double costo;
	
	
    public Ir(Nodo destino) {
		super();
		this.destino = destino;
	}

	/**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
	
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        poligonos.RobotAgentState agentState = (poligonos.RobotAgentState) s;
       
        int index = RobotEnvironmentState.nodos.indexOf(destino);
              	
		if (!RobotEnvironmentState.nodos.get(index).esAdyacente(agentState.getPosition()))
			return null;
        
		setCosto(agentState.posicionXY);
		agentState.costoStep=costo;
		//System.out.println("Costo Step:"+agentState.costoStep);
		agentState.distanciaRecorrida+=costo;
		agentState.setPosition(destino.getNombre());
		agentState.posicionXY=this.destino.posicion;
       
        return agentState;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    	RobotEnvironmentState environmentState = (RobotEnvironmentState) est;
        RobotAgentState robotState = (RobotAgentState) ast;
        this.execute((SearchBasedAgentState) ast); 
        this.setPosicionAgente(robotState,environmentState);
        environmentState.setPosicionActualRobot(robotState.getPosition());
        System.out.println("Distancia parcial: "+MathAux.redondear(costo)+"\tDistancia total: " +
        		MathAux.redondear(robotState.distanciaRecorrida));
        return environmentState;
    }

    @Override
    public Double getCost() {
        return new Double(0);
    }
    public void setCosto(Point2D.Double pos) {
        costo = BigDecimal.valueOf(pos.distance(this.destino.posicion)).setScale(MathAux.PRECISION, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    @Override
    public String toString() {
        return "Ir a "+destino;
    }
    
    private void setPosicionAgente(RobotAgentState robotState, RobotEnvironmentState environmentState)
    {
    	Point2D.Double posNueva = new Point2D.Double();
    	 if(robotState.getPosition()=="A")
			posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.A_INDEX];
			else  if(robotState.getPosition()=="B")
				posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.B_INDEX];
         	else
         	 if(robotState.getPosition()=="C")
         		posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.C_INDEX];
         		 else
            	 if(robotState.getPosition()=="D")
            		 posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.D_INDEX];
          	else
            	 if(robotState.getPosition()=="E")
            		 posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.E_INDEX];
          	else
            	 if(robotState.getPosition()=="F")
            		 posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.F_INDEX];
          	else
            	 if(robotState.getPosition()=="G")
            		 posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.G_INDEX];
          	else
            	 if(robotState.getPosition()=="H")
            		 posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.H_INDEX];
          	else
            	 if(robotState.getPosition()=="I")
            		 posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.I_INDEX];
            		 else
            	 if(robotState.getPosition()=="X")
            		 posNueva = RobotEnvironmentState.POSICIONES[RobotEnvironmentState.X_INDEX];
    	 posNueva.x-=Nodo.DIAMETRO_DEFAULT/4;
    	 posNueva.y-=Nodo.DIAMETRO_DEFAULT/4;
    	 robotState.robot.setOrientacion(MathAux.NORTE);
    	 double anguloRot=MathAux.getAnguloRotacion(robotState.robot.direccion.getP1(), robotState.robot.direccion.getP2(), posNueva);
    	 if(robotState.getPosition()!="X")
    		 robotState.robot.girarXGrados(-anguloRot);
		 try {
		boolean seDesvio=false;
		ArrayList<Integer> senso;
		while (robotState.robot.posicion.distance(destino.posicion)>1)//(!robotState.robot.zona.intersects(destino.zona.getBounds2D()))
		{	
			Thread.sleep(100);
			senso=robotState.robot.sensar(environmentState);
			Point2D p1=robotState.robot.posicion;
			Point2D p2;
			
			if(senso.size()==0) //Si no se percibio nada
			{
				if(seDesvio)
				{
				reOrientar(robotState.robot, posNueva);
				seDesvio=false;
				}
				//Por ahora tengo que reorientarlo todo el tiempo pq si no se pierde.
				reOrientar(robotState.robot, posNueva);
				robotState.robot.avanzar(environmentState);
			}
			else //Si se percibio algo
			{
//				if(senso.contains(Robot.TOCANDO) )
//				{
//					while(senso.contains(Robot.TOCANDO))
//					{
//						robotState.robot.retroceder(environmentState);
//						senso=robotState.robot.sensar(environmentState);						
//					}
//				}
				if(senso.contains(Robot.FRENTE) )
				{
					seDesvio=true;
					if(!senso.contains(Robot.IZQUIERDA))	//Si no tengo algo a la izquierda giro hacia alli
						robotState.robot.girarXGrados(MathAux.GIRO);
					else if(!senso.contains(Robot.DERECHA))	//Si no tengo algo a la derecha giro hacia alli
						robotState.robot.girarXGrados(-MathAux.GIRO);
					else									//De lo contrario giro, pero sin avanzar
					{
						robotState.robot.girarXGrados(-MathAux.GIRO);
						continue;
					}
					
					robotState.robot.avanzar(environmentState);
					continue;
				}
				
				if(senso.contains(Robot.IZQUIERDA) && !senso.contains(Robot.DERECHA))
				{
					robotState.robot.girarXGrados(-MathAux.GIRO);
					seDesvio=true;
					robotState.robot.avanzar(environmentState);
					p2=robotState.robot.posicion;
					if(p1.distance(p2)>5)
						System.out.println("Se paso");
					continue;
				}
				
				if(senso.contains(Robot.DERECHA) && !senso.contains(Robot.IZQUIERDA))
				{
					robotState.robot.girarXGrados(MathAux.GIRO);
					seDesvio=true;
					robotState.robot.avanzar(environmentState);
					p2=robotState.robot.posicion;
					if(p1.distance(p2)>5)
						System.out.println("Se paso");
					continue;
				}
				
				if(senso.contains(Robot.DERECHA) && senso.contains(Robot.IZQUIERDA))
				{
					robotState.robot.avanzar(environmentState);
					continue;
				}
			}
			
		}
    	 robotState.robot.setPosicion(posNueva);
		 } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    public void reOrientar(Robot robot,Point2D.Double posDestino){
    	double anguloRot=MathAux.getAnguloRotacion(robot.direccion.getP1(), robot.direccion.getP2(), posDestino);
   	 	//if(robotState.getPosition()!="X")
   	 		robot.girarXGrados(-anguloRot);
    }
}
