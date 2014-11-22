package joedoe.mastermind.types;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import joedoe.mastermind.types.RowRating;

import org.junit.Test;

public class RowRatingTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void ratingNegativeExactMatching() {
		new RowRating(-1,0);
	}	

	@Test(expected = IllegalArgumentException.class)
	public void ratingNegativeColorMatching() {
		new RowRating(0,-1);
	}	

	@Test
	public void ratingOkMatching() {
		RowRating rating = new RowRating(0,1);
		assertThat(rating.getNrExactMatch(), is(0));
		assertThat(rating.getNrOnlyColorMatch(), is(1));
	}	
}
