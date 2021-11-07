import java.util.concurrent.ThreadLocalRandom;

public class RandomStringChooser {
	String[] words = {};

	public RandomStringChooser(String[] words) {
		this.words = words;
	}

	public String getNext() {
		if (words.length == 0)
			return "";
		var randomNum = ThreadLocalRandom
				.current().nextInt(0, words.length);
		return words[randomNum];
	}
}
