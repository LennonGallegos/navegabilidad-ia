package acciones;

import poligonos.Nodo;
import poligonos.RobotAgentState;
import poligonos.RobotEnvironmentState;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;



public class irX extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
    	RobotAgentState agentState = (RobotAgentState) s;
        
        int index = RobotEnvironmentState.nodos.indexOf(new Nodo("X"));
              	
		if (!RobotEnvironmentState.nodos.get(index).esAdyacente(agentState.getPosition()))
			return null;
        
    	agentState.setPosition(RobotAgentState.X);
        return agentState;
    }

    /**
     * This method updates the agent state and the real world state. Actualizar el estado del ambiente
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        
        RobotEnvironmentState enviromentState = (RobotEnvironmentState) est;
        RobotAgentState robotState = (RobotAgentState) ast;
        enviromentState.setPosicionActualRobot(robotState.getPosition());
        
        int index = RobotEnvironmentState.nodos.indexOf(new Nodo("X"));
        
        
        
		if (!RobotEnvironmentState.nodos.get(index).esAdyacente(robotState.getPosition()))
			return null;
		
        
        this.execute((SearchBasedAgentState) ast);
        return enviromentState;
    }

    @Override
    public Double getCost() {
        return new Double(0);
    }

    @Override
    public String toString() {
        return "irX";
    }
}
