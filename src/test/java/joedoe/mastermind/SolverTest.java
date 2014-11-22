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
		Solver solver = new Solver(target);
		List<Row> result = solver.solve();
		// last element in result is expected to be target
		assertThat(result.get(result.size()-1), is(target));
	}
}
