package joedoe.mastermind;

public enum Color {

	WHITE,
	BLUE,
	RED,
	GREY,
	PINK,
	ORANGE,
	YELLOW,
	GREEN;

	@Override
	public String toString() {
		return "Color(" + name() + ")";
	}

}
