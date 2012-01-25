package acciones;

import java.awt.geom.Point2D;
import java.math.BigDecimal;

import poligonos.Nodo;
import poligonos.RobotAgentState;
import poligonos.RobotEnvironmentState;
import poligonos.RobotMain;
import utils.MathAux;
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
        
		setCosto(agentState.robot.posicion);
		agentState.distanciaRecorrida+=costo;
		agentState.setPosition(destino.getNombre());
        this.setPosicionAgente(agentState);
        return agentState;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    	RobotEnvironmentState enviromentState = (RobotEnvironmentState) est;
        RobotAgentState robotState = (RobotAgentState) ast;
        this.execute((SearchBasedAgentState) ast);
        enviromentState.setPosicionActualRobot(robotState.getPosition());

        return enviromentState;
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
        return "Ir a "+destino+" Distancia: "+costo;
    }
    
    private void setPosicionAgente(RobotAgentState robotState)
    {
    	 if(robotState.getPosition()=="A")
 			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.A_INDEX]);
         else  if(robotState.getPosition()=="B")
 			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.B_INDEX]);
         	else
         	 if(robotState.getPosition()=="C")
      			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.C_INDEX]);
          	else
            	 if(robotState.getPosition()=="D")
         			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.D_INDEX]);
          	else
            	 if(robotState.getPosition()=="E")
         			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.E_INDEX]);
          	else
            	 if(robotState.getPosition()=="F")
         			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.F_INDEX]);
          	else
            	 if(robotState.getPosition()=="G")
         			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.G_INDEX]);
          	else
            	 if(robotState.getPosition()=="H")
         			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.H_INDEX]);
          	else
            	 if(robotState.getPosition()=="I")
         			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.I_INDEX]);
          	else
            	 if(robotState.getPosition()=="X")
         			robotState.robot.setPosicion(RobotEnvironmentState.POSICIONES[RobotEnvironmentState.X_INDEX]);
         
         RobotMain.frame.repaint();
    }
}
