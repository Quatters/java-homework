public class Sound {
	private int[] samples;

	public int[] getSamples() {
		return samples;
	}

	public Sound(int[] samples) {
		this.samples = samples;
	}

	public int limitAmplitude(int limit) {
		int changed = 0;
		for (int i = 0; i < samples.length; i++) {
			if (Math.abs(samples[i]) > limit) {
				if (samples[i] > 0)
					samples[i] = limit;
				else
					samples[i] = -limit;

				changed++;
			}
		}

		return changed;
	}
}
