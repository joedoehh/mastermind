package joedoe.mastermind;

import java.util.ArrayList;
import java.util.List;

import org.pmw.tinylog.Logger;

import com.google.common.base.Preconditions;

public class Solver {
	
	private int dimension;
	private Row target;
	private List<SolutionSpace> solutionSpaceHistory = new ArrayList<SolutionSpace>();
	private List<Row> solution = new ArrayList<Row>();

	public Solver(Row target) {
		Preconditions.checkNotNull(target);
		Preconditions.checkArgument(target.getDimension() > 0);
		this.target = target;
		this.dimension = target.getDimension();
	}	
	
	public List<Row> solve() {	
		List<SolutionSpace> solutionSpaceHistory = new ArrayList<SolutionSpace>();
		List<Row> solution = new ArrayList<Row>();
		SolutionSpace currentSolutionSpace = new SolutionSpace(dimension);
		Row currentSolution = null;
		while(true){
			// random pick solution			
			currentSolution = currentSolutionSpace.randomPick();			
			solutionSpaceHistory.add(currentSolutionSpace);
			solution.add(currentSolution);
			// exit if answer was found
			Logger.info("picking {} from solution space with {} elements", currentSolution, currentSolutionSpace.getSolutionSpaceElements().length);
			if (currentSolution.equals(target)) break;
			// derive next step reduced solution space
			currentSolution.rate(target);
			currentSolutionSpace = currentSolutionSpace.filter(currentSolution);
		}
		return solution;
	}
	
}
