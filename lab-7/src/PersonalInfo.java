public class PersonalInfo implements Format {
	private String passportNumber;
	private String registry;
	private String snilsNumber;

	public PersonalInfo(String passportNumber, String registry, String snilsNumber) {
		this.passportNumber = passportNumber;
		this.registry = registry;
		this.snilsNumber = snilsNumber;
	}

	public boolean passportNumberIsCorrect() {
		return passportNumber.matches("^\\d{10}$|^\\d{4} \\d{6}$");
	}

	public boolean registryIsCorrect() {
		return false;
	}

	public boolean snilsNumberIsCorrect() {
		return snilsNumber.matches("^\\d{11}$|^\\d{3}-\\d{3}-\\d{3}[ -]\\d{2}$");
	}

	@Override
	public boolean isCorrect() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
