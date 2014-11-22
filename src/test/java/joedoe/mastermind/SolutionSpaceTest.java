package joedoe.mastermind;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SolutionSpaceTest {

	@Test(expected = IllegalArgumentException.class)
	public void solutionSpaceCreationSizeNegativeTest() {
		new SolutionSpace(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void solutionSpaceCreationSizeEmptyTest() {
		new SolutionSpace(0);
	}

	@Test(expected = NullPointerException.class)
	public void createNull() {
		Row[] rows = null;
		new SolutionSpace(rows);
	}

	@Test(expected = NullPointerException.class)
	public void createWithNullRow() {
		new SolutionSpace(new Row(Color.GREEN), new Row(Color.BLUE), null, new Row(Color.GREEN));
	}

	public void createEmptyAllowed() {
		Row[] rows = new Row[0];
		SolutionSpace solSpace = new SolutionSpace(rows);
		assertThat(solSpace.getSolutionSpaceElements().length, is(0));
	}
	
	@Test
	public void solutionSpaceCreationSize1Test() {
		SolutionSpace solSpace = new SolutionSpace(1);
		assertThat(solSpace.getSolutionSpaceElements().length, is(8));
		for (Color c : Color.values())
			assertThat(solSpace.containsRow(new Row(c)), is(true));			
	}

	
	@Test
	public void solutionSpaceCreationSize2Test() {
		SolutionSpace solSpace = new SolutionSpace(2);		
		assertThat(solSpace.getSolutionSpaceElements().length, is(8*7));
		// not explicitly enumerating and checking solution space
	}	
	
	@Test
	public void solutionSpaceCreationSize4Test() {
		SolutionSpace solSpace = new SolutionSpace(4);		
		assertThat(solSpace.getSolutionSpaceElements().length, is(8*7*6*5));
		// not explicitly enumerating and checking solution space
	}	

	@Test(expected = NullPointerException.class)
	public void filterNoRow() {
		new SolutionSpace(1).filterByRating(null);
	}	

	@Test(expected = IllegalArgumentException.class)
	public void filterNoRating() {
		new SolutionSpace(1).filterByRating(new Row(Color.BLUE));
	}	
	
	
	@Test
	public void filterBlueExact() {
		SolutionSpace solSpace = new SolutionSpace(1);
		Row row = new Row(Color.BLUE);
		row.setRating(new RowRating(1, 0));
		SolutionSpace filtered = solSpace.filterByRating(row);
		assertThat(filtered.getSolutionSpaceElements(), arrayContainingInAnyOrder(new Row(Color.BLUE)));
	}	
	
	@Test
	public void filterBlueWhiteExactly() {
		SolutionSpace solSpace = new SolutionSpace(2);
		Row row = new Row(Color.BLUE, Color.WHITE);
		row.setRating(new RowRating(2, 0));
		SolutionSpace filtered = solSpace.filterByRating(row);
		assertThat(filtered.getSolutionSpaceElements(), arrayContainingInAnyOrder(new Row(Color.BLUE, Color.WHITE)));
	}		
	
	@Test
	public void filterBlueWhiteWithColor() {
		SolutionSpace solSpace = new SolutionSpace(2);
		Row row = new Row(Color.BLUE, Color.WHITE);
		row.setRating(new RowRating(0, 2));
		SolutionSpace filtered = solSpace.filterByRating(row);
		assertThat(filtered.getSolutionSpaceElements(), arrayContainingInAnyOrder(new Row(Color.WHITE, Color.BLUE)));
	}		
	
	@Test
	public void filterStartingWithBlueOrWhite() {
		SolutionSpace solSpace = new SolutionSpace(2);
		Row row = new Row(Color.BLUE, Color.WHITE);
		row.setRating(new RowRating(0, 2));
		SolutionSpace filtered = solSpace.filterByRating(row);
		assertThat(filtered.getSolutionSpaceElements(), arrayContainingInAnyOrder(new Row(Color.WHITE, Color.BLUE)));
	}
	
	@Test(expected = NullPointerException.class)
	public void intersectNullPointer() {
		SolutionSpace solSpace = new SolutionSpace(1);
		solSpace.intersect(null);
	}
	@Test
	public void intersect1() {
		SolutionSpace solSpace = new SolutionSpace(1);
		Row rowBlue = new Row(Color.BLUE);
		rowBlue.setRating(new RowRating(1, 0));
		Row rowGreen = new Row(Color.GREEN);
		rowGreen.setRating(new RowRating(1, 0));		
		SolutionSpace blueFiltered = solSpace.filterByRating(rowBlue);
		SolutionSpace greenFiltered = solSpace.filterByRating(rowGreen);
		SolutionSpace intersect = blueFiltered.intersect(greenFiltered);
		assertThat(intersect.getSolutionSpaceElements().length, is(0));
	}	

	@Test
	public void intersect2() {
		SolutionSpace solSpace = new SolutionSpace(1);
		Row rowBlue = new Row(Color.BLUE);
		rowBlue.setRating(new RowRating(1, 0));		
		SolutionSpace blueFiltered = solSpace.filterByRating(rowBlue);
		SolutionSpace intersect = blueFiltered.intersect(blueFiltered);
		assertThat(intersect.getSolutionSpaceElements(), arrayContainingInAnyOrder(new Row(Color.BLUE)));
	}	

	@Test
	public void intersect3() {
		SolutionSpace solSpace = new SolutionSpace(1);
		Row rowBlue = new Row(Color.BLUE);
		rowBlue.setRating(new RowRating(1, 0));		
		SolutionSpace blueFiltered = solSpace.filterByRating(rowBlue);
		SolutionSpace intersect = new SolutionSpace(1).intersect(blueFiltered);
		assertThat(intersect.getSolutionSpaceElements(), arrayContainingInAnyOrder(new Row(Color.BLUE)));
	}		
}
