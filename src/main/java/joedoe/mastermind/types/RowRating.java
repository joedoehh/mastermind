package joedoe.mastermind.types;

import com.google.common.base.Preconditions;

/**
 * Captures rating of a row compared to a template (# of right color in position
 * and # of right color in wrong position).
 */
public class RowRating {

	private int nrExactMatch = 0;
	private int nrOnlyColorMatch = 0;

	public RowRating(int nrExactMatch, int nrOnlyColorMatch) {
		Preconditions.checkArgument(nrExactMatch >= 0,
				"nrExactMatch needs to have positive value, but was"
						+ nrExactMatch);
		Preconditions.checkArgument(nrOnlyColorMatch >= 0,
				"nrOnlyColorMatch needs to have positive value, but was"
						+ nrOnlyColorMatch);
		this.nrExactMatch = nrExactMatch;
		this.nrOnlyColorMatch = nrOnlyColorMatch;
	}

	public int getNrExactMatch() {
		return nrExactMatch;
	}

	public int getNrOnlyColorMatch() {
		return nrOnlyColorMatch;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nrExactMatch;
		result = prime * result + nrOnlyColorMatch;
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
		RowRating other = (RowRating) obj;
		if (nrExactMatch != other.nrExactMatch)
			return false;
		if (nrOnlyColorMatch != other.nrOnlyColorMatch)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RowRating [nrExactMatch=" + nrExactMatch
				+ ", nrOnlyColorMatch=" + nrOnlyColorMatch + "]";
	}

}
