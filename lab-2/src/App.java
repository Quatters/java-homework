public class App {
    public static void main(String[] args) throws Exception {
        int[] array = { 1, 300, -843, -234, 90, 111 };

        Sound sound = new Sound(array);

        System.out.println(sound.limitAmplitude(100));

        array = sound.getSamples();
        for (int i : array) {
            System.out.println(i);
        }
    }
}
