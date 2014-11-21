package joedoe.mastermind;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class RowTest {

	@Test(expected = NullPointerException.class)
	public void rowNotWithNull() {
		Color[] colorRow = null;
		new Row(colorRow);
	}
		
	@Test(expected = NullPointerException.class)
	public void rowWithUndefinedColor1() {
		Color[] colorRow = new Color[] {null};
		new Row(colorRow);
	}

	@Test(expected = NullPointerException.class)
	public void rowWithUndefinedColor2() {
		new Row(Color.BLUE, null);
	}

	@Test(expected = NullPointerException.class)
	public void rowWithUndefinedColor3() {
		new Row(Color.BLUE, null, Color.GREEN);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void rowNotEmpty() {
		Color[] colorRow = new Color[] {};
		new Row(colorRow);
	}	

	@Test(expected = IllegalArgumentException.class)
	public void rowWithDistinctiveColors1() {
		new Row(Color.BLUE, Color.BLUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void rowWithDistinctiveColors2() {
		new Row(Color.BLUE, Color.WHITE, Color.BLUE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void rowGetIndexBelow() {
		new Row(Color.BLUE).getColor(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void rowGetIndexToBig() {
		new Row(Color.BLUE).getColor(1);
	}
	
	@Test
	public void rowConstructionFine() {
		Row row = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		assertThat(row.getColor(0), is(Color.BLUE));
		assertThat(row.getColor(1), is(Color.WHITE));
		assertThat(row.getColor(2), is(Color.GREEN));
	}
	
	@Test
	public void rateCompleteExact() {
		Row row = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		Row rowToCompareWith = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		RowRating rating = row.rate(rowToCompareWith);
		assertThat(rating.getNrExactMatch(), is(3));
		assertThat(rating.getNrOnlyColorMatch(), is(0));
	}

	@Test
	public void rateColorMatches() {
		Row row = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		Row rowToCompareWith = new Row(Color.WHITE, Color.GREEN, Color.BLUE);
		RowRating rating = row.rate(rowToCompareWith);
		assertThat(rating.getNrExactMatch(), is(0));
		assertThat(rating.getNrOnlyColorMatch(), is(3));
	}	
	
	@Test
	public void rateMixedMatches() {
		Row row = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		Row rowToCompareWith = new Row(Color.BLUE, Color.GREEN, Color.ORANGE);
		RowRating rating = row.rate(rowToCompareWith);
		assertThat(rating.getNrExactMatch(), is(1));
		assertThat(rating.getNrOnlyColorMatch(), is(1));
	}		
	
	@Test
	public void rateNothingMatches() {
		Row row = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		Row rowToCompareWith = new Row(Color.ORANGE, Color.PINK, Color.RED);
		RowRating rating = row.rate(rowToCompareWith);
		assertThat(rating.getNrExactMatch(), is(0));
		assertThat(rating.getNrOnlyColorMatch(), is(0));
	}		
	
	@Test
	public void equal() {
		Row row1 = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		Row row2 = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		assertEquals(row1, row2);
	}	

	@Test
	public void notequal1() {
		Row row1 = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		Row row2 = new Row(Color.WHITE, Color.BLUE, Color.GREEN);
		assertNotEquals(row1, row2);
	}	

	@Test
	public void notequal2() {
		Row row1 = new Row(Color.BLUE, Color.WHITE, Color.GREEN);
		Row row2 = new Row(Color.BLUE, Color.WHITE);
		assertNotEquals(row1, row2);
	}	
	
}