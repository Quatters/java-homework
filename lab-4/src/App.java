public class App {
    public static void main(String[] args) throws Exception {
        // String[] words = { "wheels", "on", "the", "bus" };
        String[] words = { "one", "word" };

        RandomStringChooser chooser = new RandomStringChooser(words);

        for (int i = 0; i < 6; i++) {
            System.out.println(chooser.getNext());
        }
    }
}
