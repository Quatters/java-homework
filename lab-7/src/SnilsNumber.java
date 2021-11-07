public class SnilsNumber implements Format {
	private String snilsNumber;

	public SnilsNumber(String snilsNumber) {
		this.snilsNumber = snilsNumber;
	}

	@Override
	public boolean isCorrect() {
		return snilsNumber.matches("^\\d{11}$|^\\d{3}-\\d{3}-\\d{3}[ -]\\d{2}$");
	}
}
