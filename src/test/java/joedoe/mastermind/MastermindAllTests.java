package joedoe.mastermind;

import joedoe.mastermind.core.SolverTest;
import joedoe.mastermind.core.clojure.ClojureSolver;
import joedoe.mastermind.types.RowRatingTest;
import joedoe.mastermind.types.RowTest;
import joedoe.mastermind.types.SolutionSpaceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RowRatingTest.class, RowTest.class, SolutionSpaceTest.class,
		SolverTest.class, ClojureSolver.class })
public class MastermindAllTests {

}
