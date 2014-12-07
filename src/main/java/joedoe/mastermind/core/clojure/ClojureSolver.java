package joedoe.mastermind.core.clojure;

import java.util.ArrayList;
import java.util.List;

import joedoe.mastermind.types.Row;
import joedoe.mastermind.types.SolutionSpace;

import org.pmw.tinylog.Logger;

import clojure.lang.RT;
import clojure.lang.Var;

import com.google.common.base.Preconditions;

public class ClojureSolver {

	private String[] values;
	private List<SolutionSpace> solutionSpaceHistory = new ArrayList<SolutionSpace>();
	private List<Row> solution = new ArrayList<Row>();
	private Var clojureSolve;

	public ClojureSolver(Row target) throws Exception {
		Preconditions.checkNotNull(target);
		Preconditions.checkArgument(target.getDimension() > 0);
		this.values = target.getColorAsStrings();
		RT.loadResourceScript("joedoe/mastermind/core/clojure/clojure_mastermind_solver.clj");
		clojureSolve = RT.var("joedoe.mastermind.core.clojure", "solve");
	}	
	
	public void solve() {
		Object result = clojureSolve.invoke(values);
		Logger.debug("clojure answered with ", result);			
	}

	public List<SolutionSpace> getSolutionSpaceHistory() {
		return solutionSpaceHistory;
	}

	public List<Row> getSolution() {
		return solution;
	}
	
}
