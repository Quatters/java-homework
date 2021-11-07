public class Postcode implements Format {
	private String postcode;

	public Postcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public boolean isCorrect() {
		return postcode.matches("^\\d{6}$");
	}	
}
