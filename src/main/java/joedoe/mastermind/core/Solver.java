package joedoe.mastermind.core;

import java.util.ArrayList;
import java.util.List;

import joedoe.mastermind.types.Row;
import joedoe.mastermind.types.SolutionSpace;

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
		Row currentRow = null;
		while(true){
			// random pick solution			
			currentRow = currentSolutionSpace.randomPick();			
			solutionSpaceHistory.add(currentSolutionSpace);
			solution.add(currentRow);			
			// exit if answer was found
			Logger.info("picking {} from solution space with {} elements", currentRow, currentSolutionSpace.getSolutionSpaceElements().length);
			if (currentRow.equals(target)) break;
			// derive next step reduced solution space
			target.rate(currentRow);
			currentRow.setRating(target.getRating());
			currentSolutionSpace = currentSolutionSpace.filterByRating(currentRow);
			Logger.debug("filtered solution space has {} elements after filtering by rating", currentSolutionSpace.getSolutionSpaceElements().length);
			// add current row to solution and remove all guessed rows from solution space
			currentSolutionSpace.remove(solution);
			Logger.debug("filtered solution space has {} elements after removing old misses", currentSolutionSpace.getSolutionSpaceElements().length);			
		}
		return solution;
	}
	
}
