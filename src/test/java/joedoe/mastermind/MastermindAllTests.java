package joedoe.mastermind;

import joedoe.mastermind.core.SolverTest;
import joedoe.mastermind.types.RowRatingTest;
import joedoe.mastermind.types.RowTest;
import joedoe.mastermind.types.SolutionSpaceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RowRatingTest.class, RowTest.class, SolutionSpaceTest.class, SolverTest.class })
public class MastermindAllTests {

}
