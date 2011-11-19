package poligonos;

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

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import acciones.*;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.Search;

public class RobotAgent extends SearchBasedAgent {

	 public RobotAgent() {
	        // Robot agent goal
	        RobotGoal goal = new RobotGoal();

	        // Robot agent state
	        RobotAgentState agentState = new RobotAgentState();
	        this.setAgentState(agentState);

	        // Robot agent actions
	        Vector<SearchAction> actions = new Vector<SearchAction>();
			int index = RobotEnvironmentState.nodos.size();
			for(int i=0;i<index;i++)
			{
				actions.addElement(new Ir(RobotEnvironmentState.nodos.get(i)));
			}
	        // Robot agent problem
	        Problem problem = new Problem(goal, agentState, actions);
	        this.setProblem(problem);
	    }

    @Override
    public Action selectAction() {
        // Breath first strategy
        BreathFirstSearch searchStrategy = new BreathFirstSearch();
//        DepthFirstSearch searchStrategy = new DepthFirstSearch();

        Search searchSolver = new Search(searchStrategy);

        // Set the search tree to be written in an XML file
        searchSolver.setVisibleTree(Search.GRAPHVIZ_TREE);

        // Set the search solver
        this.setSolver(searchSolver);

        // Run the actions selection process
        Action selectedAction = null;
        try {
            selectedAction = this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(RobotAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;
    }

    @Override
    public void see(Perception perception) {
        this.getAgentState().updateState(perception);
    }
    
    public RobotAgentState getAgentState(){
    	return (RobotAgentState)super.getAgentState();
    }
}
