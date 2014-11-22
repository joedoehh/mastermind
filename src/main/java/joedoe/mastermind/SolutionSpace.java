package joedoe.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.common.base.Preconditions;

public class SolutionSpace {

	private int length;
	private List<Row> space;

	public SolutionSpace(int length) {
		Preconditions.checkArgument(length > 0,
				"a dimension needs to have a positive dimension but value was "
						+ length);
		this.length = length;
		this.space = new ArrayList<Row>();
		initializeSpace(this.space, this.length, new ArrayList<Color>());
	}

	public SolutionSpace(Collection<Row> rows) {
		Preconditions.checkNotNull(rows);
		initializeWithRows(rows.toArray(new Row[rows.size()]));
	}
	
	public SolutionSpace(Row... rows) {
		initializeWithRows(rows);
	}

	public Row[] getSolutionSpaceElements() {
		return space.toArray(new Row[space.size()]);
	}

	public SolutionSpace filter(Row rowTemplate) {
		Preconditions.checkNotNull(rowTemplate);
		Preconditions.checkArgument(rowTemplate.isRated());
		List<Row> filteredElements = new ArrayList<Row>();
		for (Row nextRowToMatch : getSolutionSpaceElements()) {
			nextRowToMatch.rate(rowTemplate);
			if (nextRowToMatch.getRating().equals(rowTemplate.getRating())) filteredElements.add(nextRowToMatch);
		}
		return new SolutionSpace(filteredElements);
	}
	
	public boolean containsRow(Row row) {
		return space.contains(row);
	}
	
	public SolutionSpace intersect(SolutionSpace intersectSpace) {
		Preconditions.checkNotNull(intersectSpace);
		List<Row> intersectedRows= new ArrayList<Row>();
		for (Row row : space) {
			if (intersectSpace.containsRow(row)) intersectedRows.add(row);
		}
		return new SolutionSpace(intersectedRows);
	}

	public Row randomPick() {
		int randomIndex = new Random().nextInt(space.size());
		return space.get(randomIndex);
	}
		
	@Override
	public String toString() {
		return "SolutionSpace [length=" + length + ", space=" + space + "]";
	}

	private void initializeSpace(List<Row> spaceToInit, int lengthLeft,
			List<Color> rowPrefix) {
		if (lengthLeft == 0) {
			Row row = new Row(rowPrefix.toArray(new Color[rowPrefix.size()]));
			spaceToInit.add(row);
			return;
		}
		for (Color nextColor : Color.values()) {
			// colors may only appear at most once in a row
			if (rowPrefix.contains(nextColor))
				continue;
			List<Color> nextPrefix = new ArrayList<Color>(rowPrefix);
			nextPrefix.add(nextColor);
			initializeSpace(spaceToInit, lengthLeft - 1, nextPrefix);
		}
	}
	
	private void initializeWithRows(Row... rows) {
		Preconditions.checkNotNull(rows);
		Preconditions
				.checkArgument(
						rows.length >= 0,
						"an array dimension needs to have a positive dimension greater or equal to zero but value was "
								+ rows.length
								+ " when called with "
								+ Arrays.toString(rows));
		this.length = rows.length;
		this.space = new ArrayList<Row>();
		for (Row row : rows) {
			Preconditions.checkNotNull(
					row,
					"no undefined rows allowed when called with "
							+ Arrays.toString(rows));
			space.add(row);
		}
	}

}
