package joedoe.mastermind;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Preconditions;

public class SolverTest {

	@Test
	public void testLength1() {
		testLengthN(new Row(Color.BLUE));
	}
	
	private void testLengthN(Row target) {
		Preconditions.checkNotNull(target);
		Solver solver = new Solver(target);
		List<Row> result = solver.solve();
		// final one is target
		assertThat(result.get(result.size()-1), is(target));
	}
}
