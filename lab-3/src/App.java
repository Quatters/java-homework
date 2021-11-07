public class App {
    public static int sum(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[10];
        for (int i = 0; i < 5; i++) {
            array[i] = i + 1;
        }

        System.out.println(sum(array));
    }
}
