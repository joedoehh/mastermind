package joedoe.mastermind.core.clojure;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import joedoe.mastermind.types.Color;
import joedoe.mastermind.types.Row;

import org.junit.Test;

import com.google.common.base.Preconditions;

public class ClojureSolverTest {

	@Test
	public void testLength1() {
		testLengthN(new Row(Color.BLUE));
	}

	@Test
	public void testLength2() {
		testLengthN(new Row(Color.BLUE, Color.GREEN));
	}

	@Test
	public void testLength3() {
		testLengthN(new Row(Color.BLUE, Color.GREEN, Color.GREY));
	}

	@Test
	public void testLength4() {
		testLengthN(new Row(Color.BLUE, Color.GREEN, Color.GREY, Color.ORANGE));
	}
	
	private void testLengthN(Row target) {
		Preconditions.checkNotNull(target);
		ClojureSolver solver;
		try {
			solver = new ClojureSolver(target);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		solver.solve();
		// last element in result is expected to be target
		assertThat(solver.getSolution().get(solver.getSolution().size()-1), is(target));
	}
}
