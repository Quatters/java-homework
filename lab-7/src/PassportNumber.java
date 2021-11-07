public class PassportNumber implements Format {
	private String passportNumber;

	public PassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	@Override
	public boolean isCorrect() {
		return passportNumber.matches("^\\d{10}$|^\\d{4} \\d{6}$");
	}
}
