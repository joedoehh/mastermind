package joedoe.mastermind.types;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Preconditions;

/**
 * Representing a row with n colors chosen.
 */
public class Row {

	private Color[] row;
	private Set<Color> colors;
	private RowRating rating;

	public Row(Color... rowColors) {
		Preconditions.checkNotNull(rowColors);
		Preconditions.checkArgument(rowColors.length > 0,
				"a row needs to have a positive dimension (rowColors was "
						+ Arrays.toString(rowColors) + ")");
		colors = new HashSet<Color>();
		for (Color c : rowColors) {
			Preconditions.checkNotNull(c,
					"row needs to be defined in any position, null not allowed (row was "
							+ Arrays.toString(rowColors) + ")");
			Preconditions.checkArgument(
					!colors.contains(c),
					"row needs to have distinctive colors, but color " + c
							+ " occurs more then once in "
							+ Arrays.toString(rowColors));
			colors.add(c);
		}
		this.row = rowColors;
		this.rating = null;
	}

	public void rate(Row rowToCompareWith) {
		setRating(getRating(rowToCompareWith));
	}

	public RowRating getRating(Row rowToCompareWith) {
		Preconditions.checkNotNull(rowToCompareWith);
		Preconditions.checkArgument(row.length == rowToCompareWith.row.length,
				"to compare both rows need to have same dimension, this row "
						+ toString()
						+ " has different one then the one to compare with "
						+ rowToCompareWith.toString());
		int nrOfExactMatches = 0;
		int nrOfColorMatches = 0;
		for (int position = 0; position < row.length; position++) {
			Color colorAtPosition = rowToCompareWith.getColor(position);
			if (exactMatch(position, colorAtPosition))
				nrOfExactMatches++;
			else if (colorMatch(colorAtPosition))
				nrOfColorMatches++;
		}
		return new RowRating(nrOfExactMatches, nrOfColorMatches);
	}
	
	
	public Color getColor(int position) {
		Preconditions.checkArgument(
				(position >= 0 && position < getDimension()),
				"a row position for row " + Arrays.toString(row)
						+ " needs to have an index in [0,"
						+ (getDimension() - 1) + "], but called with position "
						+ position);
		return row[position];
	}

	public boolean isRated() {
		return rating != null;
	}

	public RowRating getRating() {
		return rating;
	}

	public void setRating(RowRating rowRating) {
		Preconditions.checkNotNull(rowRating);
		this.rating = rowRating;
	}

	public int getDimension() {
		return row.length;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(row);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Row other = (Row) obj;
		if (!Arrays.equals(row, other.row))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Row [row=" + Arrays.toString(row) + ", rating=" + rating + "]";
	}

	private boolean colorMatch(Color color) {
		return colors.contains(color);
	}

	private boolean exactMatch(int position, Color color) {
		return row[position] == color;
	}

}