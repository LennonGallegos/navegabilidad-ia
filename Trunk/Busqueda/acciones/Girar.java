package acciones;

import poligonos.RobotAgentState;
import poligonos.RobotEnvironmentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Girar extends SearchAction{
	
	private double giro;
	
	
	public Girar(double giro) {
		super();
		this.giro = giro;
	}

	/**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
    	
        poligonos.RobotAgentState agentState = (poligonos.RobotAgentState) s;
       
        agentState.robot.girarXGrados(giro);
              	
//		if (!RobotEnvironmentState.nodos.get(index).esAdyacente(agentState.getPosition()))
//			return null;
////        if (agentState.getPosition().compareTo(poligonos.RobotAgentState.A)!=0) {
////            return null;
////        }
//        
//    	agentState.setPosition(destino.getNombre());
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

    @Override
    public String toString() {
        return "irA";
    }

}
